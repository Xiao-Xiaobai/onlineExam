/**
 * Created by lenovo on 2018/3/8.
 */
var subModule = angular.module("app.asubMess", []);
subModule.controller('asubMessController',['$scope', '$http', '$location', '$route',
    function ($scope, $http, $location, $route) {
		//获取课程列表
		$http({
			method: 'POST',
			url: 'FindSubMessServlet'
		}).then(function successCallBack(subMess){
			$scope.subjects = subMess.data;
		}, function errorCallBack(){
			console.log("失败");
		});
		//删除当前课程
		$scope.delSub = function(subNo){
			var isDel = confirm("关于该课程的所有信息将被删除，确定继续？");
			if(isDel){
				var info = {subNo: subNo};
				var transFn = function(info){
					return $.param(info);
				}
				$http({
					method: "POST",
					url: "DeleteSubjectServlet",
					data: info,
					headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'},
					transformRequest: transFn
				}).then(function successCallBack(response){
					if(response.data.mess == "success"){
						$route.reload();
					}
				})
			}
		}
		$scope.bySubNo = "";
		$scope.byName = "";
		//通过课程号查找
		$scope.findBySubNo = function(){
			$scope.byName = "";
			if($scope.bySubNo == ""){
				alert("请输入查询关键字");
			}else{
				var info = {
		        		subNo: $scope.bySubNo, 
		        		flag: "findBySubNo"
		        };
		        var transFn = function(data) {  
		            return $.param(data);  
		        };  
		       
				$http({
					method: "POST",
					url: "FindSubMessServlet",
					data: info,
					headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},  
		            transformRequest: transFn 
				}).then(function successCallBack(response){
					if(response.data.mess != null){
						alert(response.data.mess);
					}else{
						$scope.subjects = response.data;
					}			
				}, function errorCallBack(){
					console.log("失败");
				});
			}
	        
		}
		//通过课程名查找
		$scope.findByName = function(){
			$scope.bySubNo = "";
			if($scope.byName == ""){
				alert("请输入查询关键字");
			}else{
				var info = {
						subName: $scope.byName,
						flag: "findBySubName"
					};
			        var transFn = function(data) {  
			            return $.param(data);  
			        };  
			       
					$http({
						method: "POST",
						url: "FindSubMessServlet",
						data: info,
						headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},  
			            transformRequest: transFn 
					}).then(function successCallBack(response){
						if(response.data.mess != null){
							alert(response.data.mess);
						}else{
							$scope.subjects = response.data;
						}
					}, function errorCallBack(){
						console.log("失败");
					});
			}
			
		}
		
		//“全部课程”被点击，刷新页面
		$scope.reload = function(){
			$route.reload();
		}
		
		//“修改”被点击
		$scope.updateClick = function(subNo, subName, tNo, tName){
			$scope.updating = true;
			$scope.upSubNo = subNo;
			$scope.upSubName = subName;
			$scope.upTno = tNo;
			$scope.upTname = tName;
		}
		//提交修改
		$scope.updateSub = function(){
			var info = {
				subNo: $scope.upSubNo,
				subName: $scope.upSubName,
				tNo: $scope.upTno,
			}
			var transFn = function(data) {  
	            return $.param(data);  
	        };  
	       
			$http({
				method: "POST",
				url: "UpdateSubjectServlet",
				data: info,
				headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},  
	            transformRequest: transFn 
			}).then(function successCallBack(response){
				if(response.data.mess == "success"){
					$route.reload();
				}else{
					$scope.upMess = response.data.mess;
				}
			}, function errorCallBack(){
				console.log("失败");
			});
			
		}
		
		//“添加到班级”被点击
		$scope.addToClick = function(subNo, subName){
			$scope.toClass = true;
			$scope.toClassSubNo = subNo;
			$scope.toClassSubName = subName;			
		}
		//添加到班级
		$scope.addToClass = function(){
			var info = {
					subNo: $scope.toClassSubNo,
					subName: $scope.toClassSubName,
					major: $scope.major,
					year: $scope.year,
					clas: $scope.clas
				}
				var transFn = function(data) {  
		            return $.param(data);  
		        };  
		       
				$http({
					method: "POST",
					url: "AddSubToClassServlet",
					data: info,
					headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},  
		            transformRequest: transFn 
				}).then(function successCallBack(response){
					if(response.data.mess == "success"){
						$route.reload();
					}else{
						$scope.upMess2 = response.data.mess;
					}
				}, function errorCallBack(){
					console.log("失败");
				});
		}
	}]
);