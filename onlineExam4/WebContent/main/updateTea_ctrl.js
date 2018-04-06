/**
 * Created by lenovo on 2018/3/8.
 */
var updateModule = angular.module("app.updateTea", []);
updateModule.controller('updateTeaController',['$scope', '$http', '$location', '$routeParams',
		function($scope, $http, $location, $routeParams) {
			var user = {tNo: $routeParams.tno};
		    var transFn = function(user) {  
		        return $.param(user);  
		    };  
		   
			$http({
				method: "POST",
				url: "FindTeacherServlet",
				data: user,
				headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},  
		        transformRequest: transFn 
			}).then(function successCallBack(response){
				var mess = response.data[0];
				$scope.tno = mess.tno;
				$scope.pwd = mess.password;
				$scope.name = mess.name;
				$scope.sex = mess.sex;				
			}, function errorCallBack(){
				console.log("失败");
			});
			
			$scope.showMess = false;
			$scope.update = function(){
				var user = {
						tNo: $scope.tno,
						tPassword: $scope.pwd,
						tName: $scope.name,
						tSex: $scope.sex,						
				};

				var transFn = function(user) {  
	                return $.param(user);  
	            };  
	           
				$http({
					method: "POST",
					url: "UpdateTeacherServlet",
					data: user,
					headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},  
	                transformRequest: transFn 
				}).then(function successCallBack(response){
					$scope.showMess = true;
					$scope.mess = response.data.mess;
					if(response.data.mess == 'success'){
						$location.path('/a_tMess');
					}					
				});
	            

			}
			
		} ]);