/**
 * Created by lenovo on 2018/3/8.
 */
var updateModule = angular.module("app.updateStu", []);
updateModule.controller('updateStuController',['$scope', '$http', '$location', '$routeParams',
		function($scope, $http, $location, $routeParams) {
			var user = {sNo: $routeParams.sno};
		    var transFn = function(user) {  
		        return $.param(user);  
		    };  
		   
			$http({
				method: "POST",
				url: "FindStudentServlet",
				data: user,
				headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},  
		        transformRequest: transFn 
			}).then(function successCallBack(response){
				var mess = response.data[0];
				//trim()去掉多余的空格
				$scope.sno = mess.sno.trim();
				$scope.pwd = mess.password.trim();
				$scope.name = mess.name.trim();
				$scope.sex = mess.sex.trim();
				$scope.major = mess.major.trim();
				$scope.year = mess.year.trim();
				$scope.clas = mess.clas.trim();
			}, function errorCallBack(){
				console.log("失败");
			});
			
			$scope.showMess = false;
			$scope.update = function(){
				var user = {
						sNo: $scope.sno,
						sPassword: $scope.pwd,
						sName: $scope.name,
						sSex: $scope.sex,
						major: $scope.major,
						year: $scope.year,
						clas: $scope.clas,
				};

				var transFn = function(user) {  
	                return $.param(user);  
	            };  
	           
				$http({
					method: "POST",
					url: "UpdateStudentServlet",
					data: user,
					headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},  
	                transformRequest: transFn 
				}).then(function successCallBack(response){
					$scope.showMess = true;
					$scope.mess = response.data.mess;
					if(response.data.mess == 'success'){
						$location.path('/a_sMess');
					}					
				});
	            

			}
			
		} ]);