
var teaSubModule = angular.module("app.teacherSub", []);
teaSubModule.controller('teacherSubController',['$scope', '$routeParams', '$http', '$location', '$route',
    function ($scope, $routeParams, $http, $location, $route) {
		var user = {tNo: $routeParams.tno};
		var transFn = function(user) {  
			return $.param(user);  
		};     
        $http({
			method: "POST",
			url: "FindTeaSubServlet",
			data: user,
			headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},  
	        transformRequest: transFn 
		}).then(function successCallBack(response){
			if(response.data.mess != null){
				$scope.mess = response.data.mess;
			}else{
				$scope.teaSubs = response.data;
			}
			
		}, function errorCallBack(){
			console.log("失败");
		});
        
      //“添加到班级”被点击
		$scope.addToClick = function(subNo, subName){
			$scope.toClass = true;
			$scope.toClassSubNo = subNo;
			$scope.toClassSubName = subName;			
		}
		//添加到班级
		$scope.addToClass = function(){
			var info = {
					subNo: $scope.toClassSubNo,
					subName: $scope.toClassSubName,
					major: $scope.major,
					year: $scope.year,
					clas: $scope.clas
				}
				var transFn = function(data) {  
		            return $.param(data);  
		        };  
		       
				$http({
					method: "POST",
					url: "AddSubToClassServlet",
					data: info,
					headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},  
		            transformRequest: transFn 
				}).then(function successCallBack(response){
					if(response.data.mess == "success"){
						$route.reload();
					}else{
						$scope.upMess2 = response.data.mess;
					}
				}, function errorCallBack(){
					console.log("失败");
				});
		}

}]);