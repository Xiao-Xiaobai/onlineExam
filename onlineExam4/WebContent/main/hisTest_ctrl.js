/**
 * Created by lenovo on 2018/3/8.
 */
var htModule = angular.module("app.hisTest", []);
//console.log(asModule);
htModule.controller('hisTestController',['$scope', '$routeParams', '$http', '$route',
       function ($scope, $routeParams, $http, $route) {
			var info = {
				sNo: $routeParams.sno,
				flag: "hisTest"
			};
			var transFn = function(info) {  
				return $.param(info);  
			};     
		    $http({
				method: "POST",
				url: "FindStuExamServlet",
				data: info,
				headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},  
		        transformRequest: transFn 
			}).then(function successCallBack(response){
				if(response.data.mess != null){
					$scope.mess = response.data.mess;
				}else{
					$scope.hisTests = response.data;
				}
				
			}, function errorCallBack(){
				console.log("失败");
			});
		    
		    
		    $scope.delStuExam = function(eId){
		    	var isDel = confirm("确定删除该考试吗？");
		    	if(isDel){
		    		var info = {
		    	    	eId: eId,
		    	    	flag: "deleteStuExam"
		    	    };
		    	    var transFn = function(info){
		    	    	return $.param(info);
		    	    }
		    	    $http({
		    	    	method: 'POST',
		    	    	url: 'DeleteStudentServlet',
		    	    	data: info,
		    	    	headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8' },
		    	    	transformRequest: transFn
		    	    }).then(function successCallBack(response){
		    	    	if(response.data.mess == 'success'){
		    	    		$route.reload();
		    	    	}
		    	    }, function errorCallBack(response){
		    	    	console.log(response.data.mess);
		    	    });
		    	}	    	
		    }
    
		    $scope.updateClick = function(id, sNo, sName, eName, eSub, grade){
		    	$scope.updating = true;
		    	$scope.upId = id;
		    	$scope.upSno = sNo;
		    	$scope.upSname = sName;
		    	$scope.upSub = eSub;
		    	$scope.upEname = eName;
		    	$scope.upGrade = grade;
		    }
		    $scope.updateStuExam = function(){	    	
		    	var info = {
		    		eId: $scope.upId,		    		
		    		grade: $scope.upGrade,
		    		flag: 'updateStuExam'
		    	};
		    	var transFn = function(info){
		    		return $.param(info);
		    	}
		    	$http({
		    		method: 'POST',
		    		url: "UpdateStudentServlet",
		    		data: info,
		    		headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'},
		    		transformRequest: transFn
		    	}).then(function successCallBack(response){
		    		if(response.data.mess == 'success'){
		    			$route.reload();
		    		}
		    	});
		    }
}]);