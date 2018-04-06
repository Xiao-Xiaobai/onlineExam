/**
 * Created by lenovo on 2018/3/8.
 */
var teaSubModule = angular.module("app.teaSub", []);
teaSubModule.controller('teaSubController',['$scope', '$routeParams', '$http', '$location', '$route',
    function ($scope, $routeParams, $http, $location, $route) {
		var user = {tNo: $routeParams.tno};
		var transFn = function(user) {  
			return $.param(user);  
		};     
        $http({
			method: "POST",
			url: "FindTeaSubServlet",
			data: user,
			headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},  
	        transformRequest: transFn 
		}).then(function successCallBack(response){
			if(response.data.mess != null){
				$scope.mess = response.data.mess;
			}else{
				$scope.teaSubs = response.data;
			}
			
		}, function errorCallBack(){
			console.log("失败");
		});
        
        //添加课程
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
	    
	    $scope.addTeaSub = function(){
			var subs = $scope.sub;
			var user = {
					tNo: $routeParams.tno,
					subjects: subs,
					flag: "addTeaSub"
			};

			var transFn = function(user) {  
                return $.param(user, true);  
            };  
           
			$http({
				method: "POST",
				url: "AddTeacherServlet",
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
	    
	    $scope.delTeaSub = function(tno, subName){
	    	var isDel = confirm("确定删除该课程吗？");
	    	if(isDel){
	    		var info = {
	    	    	tno: tno,
	    	    	subName: subName
	    	    };
	    	    var transFn = function(info){
	    	    	return $.param(info);
	    	    }
	    	    $http({
	    	    	method: 'POST',
	    	    	url: 'DeleteTeacherServlet',
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