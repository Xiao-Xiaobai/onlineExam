/**
 * Created by lenovo on 2018/3/8.
 */
var defModule = angular.module("app.index_default", []);

defModule.controller('defaultController',['$scope', '$routeParams', '$route',
    function ($scope, $routeParams, $route) {
    	if($route.current.originalPath == "/student"){
    		$scope.studentHelp = true;
    		$scope.teachertHelp = false;
    	}else if($route.current.originalPath == "/teacher"){
    		$scope.studentHelp = false;
    		$scope.teacherHelp = true;
    	}else{
    		$scope.studentHelp = true;
    		$scope.teacherHelp = true;
    	}
    }]);