/**
 * Created by lenovo on 2018/3/8.
 */
var atModule = angular.module("app.atMess", []);
atModule.controller('atMessController',['$scope', '$http', '$location', '$route',
        function ($scope, $http, $location, $route)  {
			//获取教师信息
			$http({
				method: 'POST',
				url: 'a_tBaseMessServlet'
			}).then(function successCallBack(teaMess){
				$scope.teachers = teaMess.data;
			}, function errorCallBack(){
				console.log("失败");
			});			
			
			//根据工号或姓名查找教师
			$scope.byTno = "";
			$scope.byName = "";
			$scope.findByTno = function(){
				$scope.byName = "";
				if($scope.byTno == ""){
					alert("请输入查询关键字");
				}else{
					var user = {tNo: $scope.byTno, tName: $scope.byName };
			        var transFn = function(user) {  
			            return $.param(user);  
			        };  
			       
					$http({
						method: "POST",
						url: "FindTeacherServlet",
						data: user,
						headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},  
			            transformRequest: transFn 
					}).then(function successCallBack(response){
						if(response.data.mess != null){
							alert(response.data.mess);
						}else{
							$scope.teachers = response.data;
						}				
					}, function errorCallBack(){
						console.log("失败");
					});
				}
		        
			}
			$scope.findByName = function(){
				$scope.byTno = "";
				if($scope.byName == ""){
					alert("请输入查询关键字");
				}else{
					var user = {tNo: $scope.byTno, tName: $scope.byName };
			        var transFn = function(user) {  
			            return $.param(user);  
			        };  
			       
					$http({
						method: "POST",
						url: "FindTeacherServlet",
						data: user,
						headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},  
			            transformRequest: transFn 
					}).then(function successCallBack(response){
						if(response.data.mess != null){
							alert(response.data.mess);
						}else{
							$scope.teachers = response.data;
						}
					}, function errorCallBack(){
						console.log("失败");
					});
				}
				
			}
			
			//删除教师
			$scope.delTea = function(tno){
				var isDel = confirm("关于该教师的所有信息将被删除，确定继续？");
				if(isDel){
					var user = {tno: tno};
			        var transFn = function(user) {  
			            return $.param(user);  
			        };  
			       
					$http({
						method: "POST",
						url: "DeleteTeacherServlet",
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
			
			$scope.reload = function(){
				$route.reload();
			}
}]);