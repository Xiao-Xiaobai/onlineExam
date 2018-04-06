/**
 * Created by lenovo on 2018/3/8.
 */
var addsubModule = angular.module("app.addSub", []);
addsubModule.controller('addSubController',['$scope', '$http', '$location',
		function($scope, $http, $location) {
	
			$scope.add = function(){
				var user = {
						subNo: $scope.subNo,
						subName: $scope.subName,
						tNo: $scope.tNo,						
				};

				var transFn = function(user) {  
	                return $.param(user);  
	            };  
	           
				$http({
					method: "POST",
					url: "AddSubjectServlet",
					data: user,
					headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},  
	                transformRequest: transFn 
				}).then(function successCallBack(response){
					$scope.showMess = true;
					$scope.mess = response.data.mess;
					if(response.data.mess == 'success'){
						$location.path('/a_subMess');
					}
					
				});
	            

			}
			
		} ]);