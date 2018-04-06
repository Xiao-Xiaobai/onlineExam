
var stuModule = angular.module("app.studentInfo", []);
stuModule.controller('studentInfoController',['$scope', '$http', '$routeParams', '$route',
    function ($scope, $http, $routeParams, $route) {
		var info = {
			sNo: $routeParams.sno,
			flag: "studentQuery"
		}
		var transFn = function(info){
			return $.param(info);
		}
		//获取个人信息
		$http({
			method: 'POST',
			url: 'a_sBaseMessServlet',
			data: info,
			headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},  
	        transformRequest: transFn 
		}).then(function successCallBack(response){
			var message = response.data[0];
			$scope.sno = message.sno;
			$scope.password = message.password;
			$scope.name = message.name;
			$scope.sex = message.sex;
			$scope.major = message.major;
			$scope.year = message.year;
			$scope.clas = message.clas;
		}, function errorCallBack(){
			console.log("失败");
		});
		
	}]
);