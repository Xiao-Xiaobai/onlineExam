/**
 * Created by lenovo on 2018/3/8.
 */
var asModule = angular.module("app.asMess", []);
//console.log(asModule);
asModule.controller('asMessController',['$scope', '$http', '$location', '$route',
    function ($scope, $http, $location, $route) {
		//获取学生基本信息
		$http({
			method: 'POST',
			url: 'a_sBaseMessServlet'
		}).then(function successCallBack(stuMess){
			$scope.students = stuMess.data;
		}, function errorCallBack(){
			console.log("失败");
		});
		
		//删除学生数据
		$scope.delStu = function(sno){
			var isDel = confirm("关于该学生的所有信息将被删除，确定继续？");
			if(isDel){
				var user = {
					sno: sno,
					flag: "deleteAll"
				};
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
						$route.reload();
					}
					
				});
			}
		}
		
		//根据学号或姓名查找学生
		$scope.bySno = "";
		$scope.byName = "";
		$scope.findBySno = function(){
			$scope.byName = "";
			if($scope.bySno == ""){
				alert("请输入查询关键字");
			}else{
				var info = {findSno: $scope.bySno, flag: "findBySno" };
		        var transFn = function(info) {  
		            return $.param(info);  
		        };  
		       
				$http({
					method: "POST",
					url: "FindStudentServlet",
					data: info,
					headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},  
		            transformRequest: transFn 
				}).then(function successCallBack(response){
					if(response.data.mess != null){
						alert(response.data.mess);
					}else{
						$scope.students = response.data;
					}				
				}, function errorCallBack(){
					console.log("失败");
				});
			}
	        
		}
		$scope.findByName = function(){
			$scope.bySno = "";
			if($scope.byName == ""){
				alert("请输入查询关键字");
			}else{
				var info = {findSname: $scope.byName, flag: "findBySname" };
		        var transFn = function(info) {  
		            return $.param(info);  
		        };  
		       
				$http({
					method: "POST",
					url: "FindStudentServlet",
					data: info,
					headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},  
		            transformRequest: transFn 
				}).then(function successCallBack(response){
					if(response.data.mess != null){
						alert(response.data.mess);
					}else{
						$scope.students = response.data;
					}
				}, function errorCallBack(){
					console.log("失败");
				});
			}			
		}
		
		//根据专业查找学生
		$scope.findByMajor = function(){
			if($scope.byMajor == "暂无"){
				alert("请选择专业");
			}else{
				var info = {
					findMajor: $scope.byMajor, 
					flag: "findByMajor" 
				};
		        var transFn = function(info) {  
		            return $.param(info);  
		        };  
		       
				$http({
					method: "POST",
					url: "FindStudentServlet",
					data: info,
					headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},  
		            transformRequest: transFn 
				}).then(function successCallBack(response){
					if(response.data.mess != null){
						alert(response.data.mess);
					}else{
						$scope.students = response.data;
					}
				}, function errorCallBack(){
					console.log("失败");
				});
			}			
		}
		
		//根据年级查找
		$scope.findByYear = function(){
			if($scope.byYear == "暂无"){
				alert("请选择年级");
			}else{
				var info = {
					findMajor: $scope.byMajor,
					findYear: $scope.byYear, 
					flag: "findByYear" 
				};
		        var transFn = function(info) {  
		            return $.param(info);  
		        };  
		       
				$http({
					method: "POST",
					url: "FindStudentServlet",
					data: info,
					headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},  
		            transformRequest: transFn 
				}).then(function successCallBack(response){
					if(response.data.mess != null){
						alert(response.data.mess);
					}else{
						$scope.students = response.data;
					}
				}, function errorCallBack(){
					console.log("失败");
				});
			}			
		}
		
		//根据班级查找
		$scope.findByClass = function(){
			if($scope.byClass == "暂无"){
				alert("请选择班级");
			}else{
				var info = {
					findMajor: $scope.byMajor,
					findYear: $scope.byYear, 
					findClass: $scope.byClass,
					flag: "findByClass" 
				};
		        var transFn = function(info) {  
		            return $.param(info);  
		        };  
		       
				$http({
					method: "POST",
					url: "FindStudentServlet",
					data: info,
					headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},  
		            transformRequest: transFn 
				}).then(function successCallBack(response){
					if(response.data.mess != null){
						alert(response.data.mess);
					}else{
						$scope.students = response.data;
					}
				}, function errorCallBack(){
					console.log("失败");
				});
			}			
		}
		
		$scope.reload = function(){
			$route.reload();
		}
	}]
);