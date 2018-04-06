
var teModule = angular.module("app.teacherExam", []);
teModule.controller('teacherExamController',['$scope', '$http', '$location', '$route', '$routeParams',
    function ($scope, $http, $location, $route, $routeParams) {
		//获取考试基本信息
		if($routeParams.sub != null){
			$scope.routeParam = $routeParams.sub;
			var info = {
		        	eSub: $routeParams.sub,
		        	flag: "findBySubName"
		    };
	        var transFn = function(info) {  
	            return $.param(info);  
	        };  
	       
			$http({
				method: "POST",
				url: "FindExamMessServlet",
				data: info,
				headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},  
	            transformRequest: transFn 
			}).then(function successCallBack(response){
				if(response.data.mess != null){
					$scope.queryMess = response.data.mess;
				}else{
					$scope.exams = response.data;
				}
			}, function errorCallBack(){
				console.log("失败");
			});
		}else{
			$http({
				method: 'POST',
				url: 'FindExamMessServlet'
			}).then(function successCallBack(examMess){
				$scope.exams = examMess.data;
			}, function errorCallBack(){
				console.log("失败");
			});
		}
		
		//根据考试名或科目名匹配查找		
		$scope.byEname = "";
		$scope.bySubName = "";
		$scope.findByEname = function(){
			$scope.bySubName = "";
			if($scope.byEname == ""){
				alert("请输入关键字");
			}else{
				var info = {
			        	eName: $scope.byEname, 
			        	eSub: $scope.bySubName,
			        	flag: "findByEname"
			        };
			        var transFn = function(info) {  
			            return $.param(info);  
			        };  
			       
					$http({
						method: "POST",
						url: "FindExamMessServlet",
						data: info,
						headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},  
			            transformRequest: transFn 
					}).then(function successCallBack(response){
						if(response.data.mess != null){
							alert(response.data.mess);
						}else{
							$scope.exams = response.data;
						}				
					}, function errorCallBack(){
						console.log("失败");
					});
			}
	        
		}
		$scope.findBySubName = function(){
			$scope.byEname = "";
			if($scope.bySubName == ""){
				alert("请输入关键字");
			}else{
				var info = {
			        	eName: $scope.byEname, 
			        	eSub: $scope.bySubName,
			        	flag: "findBySubName"
			    };
		        var transFn = function(info) {  
		            return $.param(info);  
		        };  
		       
				$http({
					method: "POST",
					url: "FindExamMessServlet",
					data: info,
					headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},  
		            transformRequest: transFn 
				}).then(function successCallBack(response){
					if(response.data.mess != null){
						alert(response.data.mess);
					}else{
						$scope.exams = response.data;
					}
				}, function errorCallBack(){
					console.log("失败");
				});
			}			
		}
		
		$scope.reload = function(){
			$route.reload();
		}
		
		//修改按钮被点击 传值并赋值
		$scope.updateClick = function(id, eName, eSub, totalTime, totalScore){
			$scope.updating = true;
			$scope.upId = id;
			$scope.upEname = eName;
			$scope.upEsub = eSub;
			$scope.upTotalTime = parseInt(totalTime);
			$scope.upTotalScore = parseFloat(totalScore);			
		}
		
		//修改
		$scope.update = function(){
			if(isNaN($scope.upTotalTime)){
				$scope.upMess = "考试时间请输入数字";
			}else if(isNaN($scope.upTotalScore)){
				$scope.upMess = "卷面总分请输入数字";
			}else{
				var info = {
					id: $scope.upId,
					eName: $scope.upEname,
					eSub: $scope.upEsub,
					totalTime: $scope.upTotalTime,
					totalScore: $scope.upTotalScore
				}
				var transFn = function(data) {  
		            return $.param(data);  
		        };  
		       
				$http({
					method: "POST",
					url: "UpdateExamServlet",
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
		}
		
		//删除考试数据
		$scope.delExam = function(id){
			var isDel = confirm("关于该考试的所有信息将被删除，确定继续？");
			if(isDel){
				var info = {id: id};
		        var transFn = function(info) {  
		            return $.param(info);  
		        };  
		       
				$http({
					method: "POST",
					url: "DeleteExamServlet",
					data: info,
					headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},  
		            transformRequest: transFn 
				}).then(function successCallBack(response){
					if(response.data.mess == 'success'){
						$route.reload();
					}
					
				});
			}
		}
		
		//“添加到班级”被点击
		$scope.addToClick = function(id, eName, eSub){
			$scope.toClass = true;
			$scope.eId = id;
			$scope.toClassEname = eName;
			$scope.toClassEsub = eSub;
		}
		//添加到班级
		$scope.addToClass = function(){
			var info = {
					eName: $scope.toClassEname,
					eSub: $scope.toClassEsub,
					major: $scope.major,
					year: $scope.year,
					clas: $scope.clas
				}
				var transFn = function(data) {  
		            return $.param(data);  
		        };  
		       
				$http({
					method: "POST",
					url: "AddExamToClassServlet",
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