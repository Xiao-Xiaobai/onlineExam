
var teaStuModule = angular.module("app.teacherStu", []);
teaStuModule.controller('teacherStuController',['$scope', '$http', '$location', '$route', '$routeParams',
    function ($scope, $http, $location, $route, $routeParams) {
		$scope.findMethod = false;
		$scope.findMethodSelected = false;
		var major = $routeParams.major;
		var year = $routeParams.year;
		var clas = $routeParams.clas;
		$scope.byMajor = '暂无';
		$scope.byYear = '暂无';
		$scope.byClass = '暂无';
		if(major && year && clas){			
			$scope.findMethodSelected = true;
			$scope.findMethod = false;
			$scope.byMajor = major;
			$scope.byYear = year;
			$scope.byClass = clas;
			findClassStu();
		}else{
			$scope.findMethod = true;
			$scope.findMethodSelected = false;
			$http({
				method: 'POST',
				url: 'FindTeaSubServlet',
				params: {tNo: $routeParams.tno}
			}).then(function successCallBack(response){
				$scope.teaSubs = response.data;
			}, function errorCallBack(){
				console.log("失败");
			});		
		
			//按课程名查找学生
			$scope.findBySub = function(){
				if($scope.bySub == ""){
					alert("请先选择课程");
				}else{
					$scope.findMethodSelected = true;
					var info = {
							tNo: $routeParams.tno,
							findSub: $scope.bySub, 
							flag: "findBySub" 
					};
			        var transFn = function(info) {  
			            return $.param(info);  
			        }; 
					$http({
						method: 'POST',
						url: 'TeacherFindStuServlet',
						data: info,
						headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},  
			            transformRequest: transFn 
					}).then(function successCallBack(stuMess){
						$scope.students = stuMess.data;
					}, function errorCallBack(){
						console.log("失败");
					});
				}
			}
			
			$scope.findAll = function(){
				$scope.findMethodSelected = true;
				var info = {
						tNo: $routeParams.tno, 
						flag: "findAll" 
				};
		        var transFn = function(info) {  
		            return $.param(info);  
		        }; 
				$http({
					method: 'POST',
					url: 'TeacherFindStuServlet',
					data: info,
					headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},  
		            transformRequest: transFn 
				}).then(function successCallBack(stuMess){
					$scope.students = stuMess.data;
				}, function errorCallBack(){
					console.log("失败");
				});
			}
			
			
			//根据专业查找学生
			$scope.findByMajor = function(){
				if($scope.byMajor == "暂无"){
					alert("请选择专业");
				}else{
					var info = {
						tNo: $routeParams.tno,
						findMajor: $scope.byMajor, 
						findSub: $scope.bySub, 
						flag: "findByMajor" 
					};
			        var transFn = function(info) {  
			            return $.param(info);  
			        };  
			       
					$http({
						method: "POST",
						url: "TeacherFindStuServlet",
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
						tNo: $routeParams.tno,
						findMajor: $scope.byMajor,
						findYear: $scope.byYear,
						findSub: $scope.bySub, 
						flag: "findByYear" 
					};
			        var transFn = function(info) {  
			            return $.param(info);  
			        };  
			       
					$http({
						method: "POST",
						url: "TeacherFindStuServlet",
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
				findClassStu();	
			}
		}
		
		function findClassStu(){
			if($scope.byClass == "暂无"){
				alert("请选择班级");
			}else if($scope.byMajor == "暂无"){
				alert("请选择专业");
			}else if($scope.byYear == "暂无"){
				alert("请选择年级");
			}else{
				var info = {
					tNo: $routeParams.tno,
					findMajor: $scope.byMajor,
					findYear: $scope.byYear, 
					findClass: $scope.byClass,
					findSub: $scope.bySub, 
					flag: "findByClass" 
				};
		        var transFn = function(info) {  
		            return $.param(info);  
		        };  
		       
				$http({
					method: "POST",
					url: "TeacherFindStuServlet",
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
	}]
);