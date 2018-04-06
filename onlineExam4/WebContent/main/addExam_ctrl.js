
var addeModule = angular.module("app.addExam", []);
addeModule.controller('addExamController',['$scope', '$http', '$location', 'dateFilter', '$routeParams',
		function($scope, $http, $location, dateFilter, $routeParams) {		
			$scope.add = function(){
				if(isNaN($scope.totalTime)){
					$scope.mess = "考试时间请输入数字";
				}else if(isNaN($scope.totalScore)){
					$scope.mess = "卷面总分请输入数字";
				}else{
					var currentTime = dateFilter(new Date(), "yyyy-MM-dd hh:mm");
					var info = {
						eName: $scope.eName,
						eSub: $scope.eSub,
						totalTime: $scope.totalTime,
						totalScore: $scope.totalScore,
						createTime: currentTime
					};
	
					var transFn = function(info) {  
		                return $.param(info);  
		            };  
		           
					$http({
						method: "POST",
						url: "AddExamServlet",
						data: info,
						headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},  
		                transformRequest: transFn 
					}).then(function successCallBack(response){
						$scope.showMess = true;
						$scope.mess = response.data.mess;
						if(response.data.mess == 'success'){
							if($routeParams.tno){
								$location.path('/t_exam/'+$routeParams.tno);
							}else{
								$location.path('/a_eMess');
							}							
						}						
					});
				}
			}
			
			$scope.addAndNext = function(){
				if(isNaN($scope.totalTime)){
					$scope.mess = "考试时间请输入数字";
				}else if(isNaN($scope.totalScore)){
					$scope.mess = "卷面总分请输入数字";
				}else{
					var currentTime = dateFilter(new Date(), "yyyy-MM-dd hh:mm")
					var info = {
						eName: $scope.eName,
						eSub: $scope.eSub,
						totalTime: $scope.totalTime,
						totalScore: $scope.totalScore,
						createTime: currentTime
					};
	
					var transFn = function(info) {  
		                return $.param(info);  
		            };  
		           
					$http({
						method: "POST",
						url: "AddExamServlet",
						data: info,
						headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},  
		                transformRequest: transFn 
					}).then(function successCallBack(response){
						$scope.showMess = true;
						$scope.mess = response.data.mess;
						if(response.data.mess == 'success'){
							if($routeParams.tno){
								$location.path('/addPaper/'+$routeParams.tno);
							}else{
								$location.path('/addPaper');
							}
							
						}						
					});
				}
			}
			if($routeParams.tno){
				$http({
					method: "POST",
					url: "FindTeaSubServlet",
					params: {tNo: $routeParams.tno}
				}).then(function successCallBack(response){					
					$scope.subjects = response.data;					
				});
			}else{
				$http({
					method: 'POST',
					url: 'FindSubMessServlet'
				}).then(function successCallBack(subMess){
					$scope.subjects = subMess.data;
				});
			}
			
		} ]);