
var testingApp = angular.module('testingApp', []);
testingApp.controller('testingController', [ '$scope', '$http', '$interval', '$location',
    function($scope, $http, $interval, $location){
		$scope.started = false;
		$scope.start = function(sNo, eId){
			$scope.started = true;
			
			$scope.submited = false;
			$scope.right = [];
			$scope.wrong = [];
			$scope.myAnswer = []; //保存学生的答案
			var totalTime, hour, minute, second;
			var usedTime = 0;
			
			//获取试卷信息
			$http({
				method: 'POST',
				url: 'FindPaperMessServlet',
				params: {stuEid: eId}
			}).then(function successCallBack(response){
				if(response.data.mess != ""){
					$scope.mess = response.data.mess;
				}
				$scope.eName = response.data.eName;
				$scope.eSub = response.data.eSub;
				$scope.totalTime = response.data.totalTime;
				$scope.totalScore = response.data.totalScore;
				$scope.questions = response.data.paperContent;
				totalTime = parseInt($scope.totalTime);
				hour = parseInt(totalTime / 60);
				minute = parseInt(totalTime % 60);
				second = 0;
				$scope.leftTime = (Array(2).join(0) + hour).slice(-2) + ":" + (Array(2).join(0) + minute).slice(-2) + ":" + (Array(2).join(0) + second).slice(-2);
			}, function errorCallBack(){
				console.log("失败");
			});
			
			
			//倒计时
			var timer = $interval(function(){													
				if(second == 0){
					if(minute > 0){
						minute--;
						second = 59;
					}else{
						if(hour > 0){
							hour--;
							minute = 59;
							second = 59;
						}else{
							$interval.cancel(timer);
							submitAnswer();
						}
					}
				}else{
					second--;
				}
				$scope.leftTime = (Array(2).join(0) + hour).slice(-2) + ":" + (Array(2).join(0) + minute).slice(-2) + ":" + (Array(2).join(0) + second).slice(-2);
				usedTime++; 
			}, 1000);
			
			function submitAnswer(){
				//获取多选题的答案 并保存至myAnswer
				for(var i = 0; i < $scope.questions.length; i++){
					var multiAnswer = '';
					if($scope.questions[i].qType == "多选题"){
						for(var j in $scope.myAnswer[i]){
							if($scope.myAnswer[i][j] == true){
								var code = parseInt(j) + 65;								
								multiAnswer += String.fromCharCode(code);
							}
						}
						$scope.myAnswer[i] = multiAnswer;
					}
					
				}
				//检查答案
			    $scope.myScore = 0;
				for(var i = 0; i < $scope.questions.length; i++){					
					if($scope.questions[i].qAnswer == $scope.myAnswer[i]){
						$scope.myScore += parseInt($scope.questions[i].qScore);
						$scope.right[i] = true;
						$scope.wrong[i] = false;
					}else{
						$scope.wrong[i] = true;
						$scope.right[i] = false;
					}
				}
				//显示对应信息
				$scope.submited = true;
				$scope.usedTime = parseInt(usedTime / 60);
				//保存至数据库
				var info = {
					eId: eId,
					score: $scope.myScore,
					stuAnswer: $scope.myAnswer,
					usedTime: $scope.usedTime,
					flag: "stuExamSubmit"
				};
				console.log(info);
				var transFn = function(data) {  
		            return $.param(data, true);  
		        };  		       
				$http({
					method: "POST",
					url: "UpdateStudentServlet",
					data: info,
					headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},  
		            transformRequest: transFn 
				}).then(function successCallBack(response){
					if(response.data.mess != null && response.data.mess != "success"){											
						alert(response.data.mess);
						window.location.href = "s_index.jsp#/s_test/"+sNo;
					}
				});
			}
			
			$scope.submit = function(){
				submitAnswer();	
				$interval.cancel(timer);				
			}
		
			$scope.returnPage = function(sno){
				window.location.href = "s_index.jsp#/s_test/"+sNo;
			}
		}
		
		 

}                                         
]);