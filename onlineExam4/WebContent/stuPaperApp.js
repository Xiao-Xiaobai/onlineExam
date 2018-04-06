
var stuPaperApp = angular.module('stuPaperApp', []);
stuPaperApp.controller('stuPaperController', [ '$scope', '$http', '$interval', '$location',
    function($scope, $http, $timeout, $location){		
			angular.element(document).ready(function(){
				$scope.right = [];
				$scope.wrong = [];
				if($scope.tNo != null && $scope.tNo != ''){
					var info = {
						sNo: $scope.sNo,
						flag: "studentQuery"
					}
					var transFn = function(info){
			    		return $.param(info);
			    	}
					$http({
						method: 'POST',
						url: 'a_sBaseMessServlet',
						data: info,
			    		headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'},
			    		transformRequest: transFn
					}).then(function successCallBack(response){
						$scope.sName = response.data[0].name;
					});
				}
				//获取试卷信息
				var info = {stuEid: $scope.eId, flag: "stuPaper"};
				var transFn = function(data) {  
		            return $.param(data);  
		        }; 
				$http({
					method: 'POST',
					url: 'FindPaperMessServlet',
					data: info,
					headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},  
		            transformRequest: transFn 
				}).then(function successCallBack(response){
					if(response.data.mess != ""){
						$scope.mess = response.data.mess;
					}
					$scope.eName = response.data.eName;
					$scope.eSub = response.data.eSub;
					$scope.totalTime = response.data.totalTime;
					$scope.totalScore = parseInt(response.data.totalScore);
					$scope.questions = response.data.paperContent;
					var stuAnswer = response.data.stuAnswer;
					stuAnswer = stuAnswer.replace("[", "");
					stuAnswer = stuAnswer.replace("]", "");
					$scope.myAnswer = stuAnswer.split(",");
					$scope.usedTime = response.data.usedTime;					
					$scope.myScore = parseInt(response.data.grade);
					
					for(var i = 0; i < $scope.questions.length; i++){					
						if($scope.questions[i].qAnswer == $scope.myAnswer[i]){
							$scope.right[i] = true;
							$scope.wrong[i] = false;
						}else{
							$scope.wrong[i] = true;
							$scope.right[i] = false;
						}
					}
				}, function errorCallBack(){
					console.log("失败");
				});
				
				$scope.returnPage = function(sno){
					window.location.href = "s_index.jsp#/s_test/"+sno;
				}
			})
			
			
			
		
		 

}                                         
]);