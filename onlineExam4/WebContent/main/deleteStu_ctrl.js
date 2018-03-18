/**
 * Created by lenovo on 2018/3/8.
 */
var delModule = angular.module("app.delStu", []);
delModule.controller('delStuController', ['$scope', '$routeParams', '$http', '$location',
    function ($scope, $routeParams, $http, $location) {
        var sno = $routeParams.sno;
        var user = {sNo: sno};
        var transFn = function(user) {  
            return $.param(user);  
        };  
       
		$http({
			method: "POST",
			url: "DeleteStudentServlet",
			data: user,
			headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},  
            transformRequest: transFn 
		}).then(function successCallBack(response){
			if(response.data.mess == 'success'){
				$location.path('/a_sMess');
			}
			
		});
}]);