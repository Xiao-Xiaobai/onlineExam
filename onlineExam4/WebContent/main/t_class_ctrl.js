
var teaClassModule = angular.module("app.teacherClass", []);
teaClassModule.controller('teacherClassController',['$scope', '$routeParams', '$http', '$location', '$route',
    function ($scope, $routeParams, $http, $location, $route) {
		$scope.tno = $routeParams.tno;
		var user = {tNo: $routeParams.tno};
		var transFn = function(user) {  
			return $.param(user);  
		};     
        $http({
			method: "POST",
			url: "FindTeaClassServlet",
			data: user,
			headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},  
	        transformRequest: transFn 
		}).then(function successCallBack(response){
			if(response.data.mess != null){
				$scope.mess = response.data.mess;
			}else{
				$scope.teaClass = response.data;
			}
			
		}, function errorCallBack(){
			console.log("失败");
		});
              

}]);