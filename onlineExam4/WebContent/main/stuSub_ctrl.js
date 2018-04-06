/**
 * Created by lenovo on 2018/3/8.
 */
var stuSubModule = angular.module("app.stuSub", []);
stuSubModule.controller('stuSubController',['$scope', '$routeParams', '$http', '$location', '$route',
    function ($scope, $routeParams, $http, $location, $route) {
		var user = {sNo: $routeParams.sno, flag: "findBySno"};
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
        
        $scope.inpts = [''];
		$scope.sub = [];
	    $scope.newSub = function () {
	        var newin = [''];
	        $scope.inpts.push(newin);
	    };
	    
	    $scope.delSub = function ($index) {
	        $scope.inpts.splice($index, 1);
	        $scope.sub.splice($index, 1);
	        //从$index位置开始删除一个元素，即删除当前元素
	    }
	    
	    $scope.addStuSub = function(){
			var subs = $scope.sub;
			var user = {
					sNo: $routeParams.sno,
					subjects: subs,
					flag: "addStuSub"
			};

			var transFn = function(user) {  
                return $.param(user, true);  
            };  
           
			$http({
				method: "POST",
				url: "AddStudentServlet",
				data: user,
				headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},  
                transformRequest: transFn 
			}).then(function successCallBack(response){
				
				if(response.data.mess == 'success'){
					$route.reload();
				}else{
					$scope.mess2 = response.data.mess;
				}
				
			}, function errorCallBack(){
				console.log("失败");
			});           
		}
	    
	    $scope.delStuSub = function(sno, subName){
	    	var isDel = confirm("确定删除该课程吗？相关考试也将被删除");
	    	if(isDel){
	    		var info = {
	    	    	sno: sno,
	    	    	subName: subName,
	    	    	flag: "deleteStuSub"
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
	    $scope.updateClick = function(id, sno, sName, subName, eName, status, grade){
	    	$scope.updating = true;
	    	$scope.upId = id;
	    	$scope.upSno = sno;
	    	$scope.upSname = sName;
	    	$scope.upSub = subName;
	    	$scope.upEname = eName;
	    	$scope.upStatus = status;
	    	$scope.upGrade = grade;
	    }
	    $scope.updateStuSub = function(){	    	
	    	var info = {
	    		eId: $scope.upId,
	    		sno: $scope.upSno,
	    		subName: $scope.upSub,
	    		status: $scope.upStatus,
	    		grade: $scope.upGrade,
	    		flag: 'updateStuSub'
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
	    
	  //添加课程时 获取已有课程
		$http({
			method: 'POST',
			url: 'FindSubMessServlet'
		}).then(function successCallBack(subMess){
			$scope.subjects = subMess.data;
		}, function errorCallBack(){
			console.log("失败");
		});

}]);