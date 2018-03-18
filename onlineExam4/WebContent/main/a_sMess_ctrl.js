/**
 * Created by lenovo on 2018/3/8.
 */
var asModule = angular.module("app.asMess", []);
//console.log(asModule);
asModule.controller('asMessController',['$scope', '$http', '$location',
    function ($scope, $http, $location) {
		$http({
			method: 'POST',
			url: 'a_sBaseMessServlet'
		}).then(function successCallBack(stuMess){
			$scope.students = stuMess.data;
		}, function errorCallBack(){
			console.log("失败");
		});
		$scope.delStu = function(sno){
			var isDel = confirm("关于该学生的所有信息将被删除，确定继续？");
			if(isDel){
				$location.path("/deleteStu/"+sno);
			}
		}
		$scope.bySno = "";
		$scope.byName = "";
		$scope.findBySno = function(){
			$scope.byName = "";
	        var user = {sNo: $scope.bySno, sName: $scope.byName };
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
				if(response.data.mess != null){
					alert(response.data.mess);
				}else{
					$scope.students = response.data;
				}				
			}, function errorCallBack(){
				console.log("失败");
			});
		}
		$scope.findByName = function(){
			$scope.bySno = "";
			var user = {sNo: $scope.bySno, sName: $scope.byName };
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
				if(response.data.mess != null){
					alert(response.data.mess);
				}else{
					$scope.students = response.data;
				}
			}, function errorCallBack(){
				console.log("失败");
			});
		}
	}]
);