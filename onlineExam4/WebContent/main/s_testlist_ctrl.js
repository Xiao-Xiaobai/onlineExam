
var examModule = angular.module("app.studentExam", []);
examModule.controller('studentExamController',['$scope', '$http', '$routeParams', '$route',
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
			url: 'FindStuExamServlet',
			data: info,
			headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},  
	        transformRequest: transFn 
		}).then(function successCallBack(response){
			if(response.data.mess != null){
				$scope.mess = response.data.mess;
			}else{
				$scope.exams = response.data;
			}
		}, function errorCallBack(){
			console.log("失败");
		});
		
	}]
);