/**
 * Created by lenovo on 2018/3/8.
 */
var addsModule = angular.module("app.addStu", []);
addsModule.controller('addStuController',['$scope', '$http', '$location',
		function($scope, $http, $location) {
			$scope.show2 = false;
			$scope.show3 = false;
			$scope.sub1 = '';
			$scope.sub2 = '';
			$scope.sub3 = '';
			$scope.newSub = function() {
				if ($scope.sub1 != '' && $scope.show2 == '') {
					$scope.show2 = true;
				} else if ($scope.sub1 != '' && $scope.sub2 != '') {
					$scope.show3 = true;
				} else {

				}
			};
			$scope.showMess = false;
			$scope.add = function(){
				var subs = [];
				if($scope.sub1 != ''){
					subs.push($scope.sub1);
				}
				if($scope.sub2 != ''){
					subs.push($scope.sub2);
				}
				if($scope.sub3 != ''){
					subs.push($scope.sub3);
				}
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
						$location.path('/a_sMess');
					}
					
				});
	            

			}
			
		} ]);