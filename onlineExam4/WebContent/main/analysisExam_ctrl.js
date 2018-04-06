
var teaStuModule = angular.module("app.analysisExam", []);
teaStuModule.controller('analysisExamController',['$scope', '$http', '$location', '$route', '$routeParams',
    function ($scope, $http, $location, $route, $routeParams) {
		$http({
			method: 'POST',
			url: 'FindExamMessServlet',
			params: {tNo: $routeParams.tno}
		}).then(function successCallBack(response){
			$scope.eMess = response.data;
		}, function errorCallBack(){
			console.log("失败");
		});
		
		$scope.tno = $routeParams.tno;
		
		$scope.findAnasisly = function(){
			if($scope.eName == ''){
				alert("请先选择考试名");
			}else if($scope.major == '暂无'){
				alert("请先选择专业");
			}else if($scope.year == '暂无'){
				alert("请先选择年级");
			}else if($scope.clas == '暂无'){
				alert("请先选择班级");
			}else{
				$scope.selected = true;
				var info = {
					tNo: $routeParams.tno,
					eName: $scope.eName,
					major: $scope.major,
					year: $scope.year,
					clas: $scope.clas
				}
				var transFn = function(info){
					return $.param(info, true);
				}
				$http({
					method: "POST",
					url: "TeacherFindScoreServlet",
					data: info,
					headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},
					transformRequest: transFn
				}).then(function successCallBack(response){
					if(response.data.mess != null){
						alert(response.data.mess);
					}else{
						$scope.stuMess = response.data.stuMess;
						$scope.totalNum = response.data.totalNum;
						$scope.testedNum = response.data.testedNum;
						$scope.totalScore = response.data.totalScore;
						$scope.passNum = response.data.passNum;
						$scope.avgScore = response.data.avgScore;
						
					}										
				});
			}
		}
	

	}]
);