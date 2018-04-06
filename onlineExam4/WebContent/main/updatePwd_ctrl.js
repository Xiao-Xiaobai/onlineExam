
var updateModule = angular.module("app.updatePwd", []);
updateModule.controller('updatePwdController',['$scope', '$http', '$routeParams', '$route', '$location',
    function ($scope, $http, $routeParams, $route, $location) {
		if($routeParams.sno){
			$scope.studentInfo = true;
			$scope.sNo = $routeParams.sno;
			$scope.updateStuPwd = function(){
				if($scope.oldPwd == undefined || $scope.oldPwd == ""){
					$scope.mess = "请输入旧密码";
				}else if($scope.newPwd == undefined || $scope.newPwd == ""){
					$scope.mess = "请输入新密码";
				}else if($scope.newPwd2 == undefined || $scope.newPwd2 == ""){
					$scope.mess = "请再次输入新密码";
				}else if($scope.newPwd != $scope.newPwd2){
					$scope.mess = "新密码输入不一致";
				}else{
					var info = {
						sNo: $routeParams.sno,
						oldPwd: $scope.oldPwd,
						newPwd: $scope.newPwd,
						flag: "updateStuPwd"
					}
					var transFn = function(info){
						return $.param(info);
					}
				
					$http({
						method: 'POST',
						url: 'UpdateStudentServlet',
						data: info,
						headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},  
				        transformRequest: transFn 
					}).then(function successCallBack(response){
						if(response.data.mess == "success"){
							$location.path("/s_info/"+$routeParams.sno);
						}else{
							$scope.mess = response.data.mess;
						}
					}, function errorCallBack(){
						console.log("失败");
					});
				}
			}
		}
		
		if($routeParams.tno){
			$scope.teacherInfo = true;
			$scope.tNo = $routeParams.tno;
			$scope.updateTeaPwd = function(){
				if($scope.oldPwd == undefined || $scope.oldPwd == ""){
					$scope.mess = "请输入旧密码";
				}else if($scope.newPwd == undefined || $scope.newPwd == ""){
					$scope.mess = "请输入新密码";
				}else if($scope.newPwd2 == undefined || $scope.newPwd2 == ""){
					$scope.mess = "请再次输入新密码";
				}else if($scope.newPwd != $scope.newPwd2){
					$scope.mess = "新密码输入不一致";
				}else{
					var info = {
						tNo: $routeParams.tno,
						oldPwd: $scope.oldPwd,
						newPwd: $scope.newPwd,
						flag: "updateTeaPwd"
					}
					var transFn = function(info){
						return $.param(info);
					}
				
					$http({
						method: 'POST',
						url: 'UpdateTeacherServlet',
						data: info,
						headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},  
				        transformRequest: transFn 
					}).then(function successCallBack(response){
						if(response.data.mess == "success"){
							$location.path("/t_info/"+$routeParams.tno);
						}else{
							$scope.mess = response.data.mess;
						}
					}, function errorCallBack(){
						console.log("失败");
					});
				}
			}
		}
		
	}]
);