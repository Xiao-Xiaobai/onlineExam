
var subModule = angular.module("app.studentSub", []);
subModule.controller('studentSubController',['$scope', '$http', '$routeParams', '$route',
    function ($scope, $http, $routeParams, $route) {
		var info = {
			sNo: $routeParams.sno,
			flag: "studentQuery"
		}
		var transFn = function(info){
			return $.param(info);
		}
		//获取课程列表
		$http({
			method: 'POST',
			url: 'FindStuSubServlet',
			data: info,
			headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},  
	        transformRequest: transFn 
		}).then(function successCallBack(subMess){
			$scope.subjects = subMess.data;
		}, function errorCallBack(){
			console.log("失败");
		});
		
	}]
);