
var teaModule = angular.module("app.teacherInfo", []);
teaModule.controller('teacherInfoController',['$scope', '$http', '$routeParams', '$route',
    function ($scope, $http, $routeParams, $route) {
		var info = {
			tNo: $routeParams.tno,
			flag: "teacherQuery"
		}
		var transFn = function(info){
			return $.param(info);
		}
		//获取个人信息
		$http({
			method: 'POST',
			url: 'a_tBaseMessServlet',
			data: info,
			headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},  
	        transformRequest: transFn 
		}).then(function successCallBack(response){
			var message = response.data[0];
			$scope.tno = message.tno;
			$scope.password = message.password;
			$scope.name = message.name;
			$scope.sex = message.sex;			
		}, function errorCallBack(){
			console.log("失败");
		});
		
	}]
);