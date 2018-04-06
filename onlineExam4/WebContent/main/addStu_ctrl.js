
var addsModule = angular.module("app.addStu", []);
addsModule.controller('addStuController',['$scope', '$http', '$location', '$routeParams',
		function($scope, $http, $location, $routeParams) {
			$scope.inpts = [''];
			$scope.sub = [];
		    $scope.newSub = function () {
		        var newin = [''];
		        $scope.inpts.push(newin);
		    };
		    
		    $scope.delSub = function ($index) {
		        $scope.inpts.splice($index, 1);
		        $scope.sub.splice($index, 1);
		        //从$index位置开始删除一个元素，即删除当前元素
		    }
			$scope.add = function(){
				var subs = $scope.sub;
				var user = {
						sNo: $scope.sno,
						sPassword: $scope.pwd,
						sName: $scope.name,
						sSex: $scope.sex,
						major: $scope.major,
						year: $scope.year,
						clas: $scope.clas,
						subjects: subs
				};

				var transFn = function(user) {  
	                return $.param(user, true);  
	            };  
	           
				$http({
					method: "POST",
					url: "AddStudentServlet",
					data: user,
					headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},  
	                transformRequest: transFn 
				}).then(function successCallBack(response){
					$scope.showMess = true;
					$scope.mess = response.data.mess;
					if(response.data.mess == 'success'){	
						if($routeParams.tno){
							$location.path('/t_stu/'+$routeParams.tno);
						}else{
							$location.path('/a_sMess');
						}						
					}
					
				});	            
			}
			if($routeParams.tno){
				$http({
					method: 'POST',
					url: 'FindTeaSubServlet',
					params: {tNo: $routeParams.tno}
				}).then(function successCallBack(response){
					$scope.subjects = response.data;
				}, function errorCallBack(){
					console.log("失败");
				});	
			}else{
				$http({
					method: 'POST',
					url: 'FindSubMessServlet'
				}).then(function successCallBack(subMess){
					$scope.subjects = subMess.data;
				}, function errorCallBack(){
					console.log("失败");
				});
			}
			
		} ]);