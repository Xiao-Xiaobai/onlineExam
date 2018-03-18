/**
 * Created by lenovo on 2018/3/8.
 */
var stuSubModule = angular.module("app.stuSub", []);
stuSubModule.controller('stuSubController',['$scope', '$routeParams', '$http', '$location',
    function ($scope, $routeParams, $http, $location) {
       // $scope.$route = $route;
		var user = {sNo: $routeParams.sno};
		var transFn = function(user) {  
			return $.param(user);  
		};  
   
        $http({
			method: "POST",
			url: "FindStuSubServlet",
			data: user,
			headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},  
	        transformRequest: transFn 
		}).then(function successCallBack(response){
			if(response.data.mess != null){
				$scope.mess = response.data.mess;
			}else{
				$scope.stuSubs = response.data;
			}
			
		}, function errorCallBack(){
			console.log("失败");
		});

}]);