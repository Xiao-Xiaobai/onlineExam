/**
 * Created by lenovo on 2018/3/8.
 */
var teaClassModule = angular.module("app.teaClass", []);
teaClassModule.controller('teaClassController',['$scope', '$routeParams', '$http', '$location', '$route',
    function ($scope, $routeParams, $http, $location, $route) {
		var user = {tNo: $routeParams.tno};
		var transFn = function(user) {  
			return $.param(user);  
		};     
        $http({
			method: "POST",
			url: "FindTeaClassServlet",
			data: user,
			headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},  
	        transformRequest: transFn 
		}).then(function successCallBack(response){
			if(response.data.mess != null){
				$scope.mess = response.data.mess;
			}else{
				$scope.teaClass = response.data;
			}
			
		}, function errorCallBack(){
			console.log("失败");
		});
        
        //添加班级
        $scope.selects = [''];
		$scope.major = [];
		$scope.year = [];
		$scope.clas = [];
		
		//继续添加专业年级班级 信息时调用
		$scope.newCls = function(){
			$scope.selects.push(['']);
		}
	    
	    $scope.addTeaClass = function(){
			var user = {
					tNo: $routeParams.tno,
					major: $scope.major,
					year: $scope.year,
					clas: $scope.clas,
					flag: "addTeaClass"
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
	    
	    $scope.delTeaClass = function(id){
	    	var isDel = confirm("确定删除该授课对象吗？");
	    	if(isDel){
	    		var info = {
	    	    	id: id
	    	    };	    	    
	    	    $http({
	    	    	method: 'POST',
	    	    	url: 'DeleteTeacherServlet',
	    	    	params: info,	    	    
	    	    }).then(function successCallBack(response){
	    	    	if(response.data.mess == 'success'){
	    	    		$route.reload();
	    	    	}
	    	    }, function errorCallBack(response){
	    	    	console.log(response.data.mess);
	    	    });
	    	}	    	
	    }
	    $scope.updateClick = function(id, tno, tName, major, year, clas){
	    	$scope.upId = id;
	    	$scope.updating = true;
	    	$scope.upTno = tno;
	    	$scope.upTname = tName;
	    	$scope.upMajor = major;
	    	$scope.upYear = year;
	    	$scope.upClass = clas;
	    }
	    $scope.updateTeaSub = function(){	    	
	    	var info = {
	    		id: $scope.upId,
	    		major: $scope.upMajor,
	    		year: $scope.upYear,
	    		clas: $scope.upClass,
	    		flag: 'updateTeaClass'
	    	};
	    	var transFn = function(info){
	    		return $.param(info);
	    	}
	    	$http({
	    		method: 'POST',
	    		url: "UpdateTeacherServlet",
	    		data: info,
	    		headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'},
	    		transformRequest: transFn
	    	}).then(function successCallBack(response){
	    		if(response.data.mess == 'success'){
	    			$route.reload();
	    		}
	    	});
	    	//蒙层消失
	    	$scope.updating = false;
	    }
	    

}]);