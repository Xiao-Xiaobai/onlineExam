
var updateModule = angular.module("app.updateStu", []);
updateModule.controller('updateStuController',['$scope', '$http', '$location', '$routeParams',
		function($scope, $http, $location, $routeParams) {
			var info = {findSno: $routeParams.sno, flag: "findBySno"};
		    var transFn = function(info) {  
		        return $.param(info);  
		    };  
		   
			$http({
				method: "POST",
				url: "FindStudentServlet",
				data: info,
				headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},  
		        transformRequest: transFn 
			}).then(function successCallBack(response){
				var mess = response.data[0];
				$scope.sno = mess.sno;
				$scope.pwd = mess.password;
				$scope.name = mess.name;
				$scope.sex = mess.sex;
				$scope.major = mess.major;
				$scope.year = mess.year;
				$scope.clas = mess.clas;
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
						flag: "updateBase"
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