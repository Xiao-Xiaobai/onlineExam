
var addpModule = angular.module("app.addPaper", []);
addpModule.controller('addPaperController',['$scope', '$http', '$routeParams', '$location',
		function($scope, $http, $routeParams, $location) {
			if($routeParams.tno){
				$scope.isTeacher = true;
				$scope.tno = $routeParams.tno;
			}
			$http({
				method: 'POST',
				url: 'FindExamMessServlet',
				params: {tNo: $routeParams.tno}
			}).then(function successCallBack(response){
				$scope.eMess = response.data;
			}, function errorCallBack(){
				console.log("失败");
			});
			
			if($routeParams.eName != null){
				$scope.eMes = $routeParams.eName;
			}else{
				$scope.eMes = "";
			}
			
			if($routeParams.qNo != null){
				$scope.qNo = parseInt($routeParams.qNo) + 1;
			}else{
				$scope.qNo = 1;
			}
			
			//显示的数据初始化
			function initialize(){
				$scope.qType =  '单选题';
				$scope.qScore = '';
				$scope.qTitle = '';
				$scope.selects = [
					{"selectNo": "A", "selection": ""},
					{"selectNo": "B", "selection": ""},
					{"selectNo": "C", "selection": ""},
					{"selectNo": "D", "selection": ""}
				];
				$scope.multiSelects = [
				  	{"selectNo": "A", "selection": "", "isChecked": false},
				  	{"selectNo": "B", "selection": "", "isChecked": false},
				  	{"selectNo": "C", "selection": "", "isChecked": false},
				  	{"selectNo": "D", "selection": "", "isChecked": false}
				];
				$scope.qAnswer = '';
				$scope.mess = '';
			}
			initialize();
			
			//重新选择考试时 数据初始化
			$scope.reCreate = function(){
				initialize();
				$scope.qNo = 1;
			}
						
			
			//临时增加选项
			$scope.addSelect = function(){				
				//获取当前最后一个选项的编码
				var lastSelectNo = $scope.selects[$scope.selects.length-1].selectNo.charCodeAt();
				//形成新的末尾选项
				var newLastNo = String.fromCharCode(lastSelectNo + 1);
				var newin = {"selectNo": ""+ newLastNo +"", "selection": ""};
				$scope.selects.push(newin);
			}
			$scope.addMultiSelect = function(){				
				//获取当前最后一个选项的编码
				var lastSelectNo = $scope.multiSelects[$scope.multiSelects.length-1].selectNo.charCodeAt();
				//形成新的末尾选项
				var newLastNo = String.fromCharCode(lastSelectNo + 1);
				var newin = {"selectNo": ""+ newLastNo +"", "selection": "", "isChecked": false};
				$scope.multiSelects.push(newin);
			}
			
			//临时删除选项
			$scope.delSelect = function($index){
				$scope.selects.splice($index, 1);
			}
			$scope.delMultiSelect = function($index){
				$scope.multiSelects.splice($index, 1);
			}
			
			//验证创建题目时的数据
			function verify(){
				if($scope.qScore == undefined || $scope.qScore == ''){
					$scope.mess = "请输入题目分值";
					return 0;
				}else if(isNaN($scope.qScore)){
					$scope.mess = "题目分值请输入数字";
					return 0;
				}else if($scope.qTitle == undefined || $scope.qTitle == ''){
					$scope.mess = "请输入题干内容";
					return 0;
				}else if($scope.qType == '单选题'){
					if($scope.qAnswer == undefined || $scope.qAnswer == ''){
						$scope.mess = "请在选项设置区选择答案";
						return 0;
					}else{
						var noSelection = 0;
						for(var i in $scope.selects){
							if($scope.selects[i].selection == ""){
								noSelection++;
							}
						}
						if(noSelection == $scope.selects.length){
							$scope.mess = "请至少设置一个选项内容";
							return 0;
						}else{
							return 1;
						}
					}					
				}else if($scope.qType == '多选题'){
					var noChecked = 0;
					var noSelection = 0;
					for(var i in $scope.multiSelects){
						if($scope.multiSelects[i].isChecked == false){
							noChecked++;
						}
						if($scope.multiSelects[i].selection == ""){
							noSelection++;
						}
					}
					if(noChecked == $scope.multiSelects.length){
						$scope.mess = "请在选项设置区勾选答案";
						return 0;
					}else if(noSelection == $scope.multiSelects.length){
						$scope.mess = "请至少设置一个选项内容";
						return 0;
					}else{
						return 1;
					}
				}else if($scope.qType == '判断题'){
					if($scope.qAnswer == undefined || $scope.qAnswer == ''){
						$scope.mess = "请在答案设置区选择答案";
						return 0;
					}else{
						return 1;
					}
				}else{
					return 1;
				}
			}
			
			//发送至servlet
			function addToServlet(){
				var qSelections = '';
				if($scope.qType == '单选题'){
					for(var i in $scope.selects){
						if($scope.selects[i].selectNo != "" && $scope.selects[i].selection != ""){
							qSelections = qSelections + $scope.selects[i].selectNo + "." + $scope.selects[i].selection + ";";
						}													
					}
					qSelections = qSelections.slice(0, qSelections.length-1); //去掉最后一个分号
				}
				if($scope.qType == '多选题'){
					var multiAnswer = '';
					for(var i in $scope.multiSelects){				
						if($scope.multiSelects[i].isChecked == true){
							multiAnswer += $scope.multiSelects[i].selectNo;
						}					
					}
					$scope.qAnswer = multiAnswer;
					for(var i in $scope.multiSelects){
						if($scope.multiSelects[i].selectNo != "" && $scope.multiSelects[i].selection != ""){
							qSelections = qSelections + $scope.multiSelects[i].selectNo + "." + $scope.multiSelects[i].selection + ";";						
						}
					}
					qSelections = qSelections.slice(0, qSelections.length-1);
				}
				var info = {
					eName: $scope.eMes,
					qNo: $scope.qNo,
					qType: $scope.qType,
					qScore: $scope.qScore,
					qTitle: $scope.qTitle,
					qSelection: qSelections,
					qAnswer: $scope.qAnswer
				};
				var transFn = function(info){
					return $.param(info, true);
				}
				$http({
					method: "POST",
					url: "AddPaperServlet",
					data: info,
					headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},
					transformRequest: transFn
				}).then(function successCallBack(response){
					if(response.data.mess != "success"){
						$scope.mess = response.data.mess;
					}						
				});
			}
			
			//保存
			$scope.add = function(){
				if(verify()){
					addToServlet();
					$location.path("/a_qMess");
				}
			}
			
			//保存并新增下一题
			$scope.addAndNext = function(){
				if(verify()){
					addToServlet();
					$scope.qNo++;
					$scope.qType =  '单选题';
					$scope.qScore = '';
					$scope.qTitle = '';
					$scope.selects = [
					  	{"selectNo": "A", "selection": ""},
					  	{"selectNo": "B", "selection": ""},
					  	{"selectNo": "C", "selection": ""},
					  	{"selectNo": "D", "selection": ""}
					];
					$scope.multiSelects = [
					    {"selectNo": "A", "selection": "", "isChecked": false},
					    {"selectNo": "B", "selection": "", "isChecked": false},
					    {"selectNo": "C", "selection": "", "isChecked": false},
					    {"selectNo": "D", "selection": "", "isChecked": false}
					];
					$scope.qAnswer = '';
					$scope.mess = '';
				}
				
			}
			
			//预览
			$scope.preview = function(){
				$scope.previewing = true;
				if($scope.qType == '多选题'){
					var multiAnswer = '';
					for(var i in $scope.multiSelects){				
						if($scope.multiSelects[i].isChecked == true){
							multiAnswer += $scope.multiSelects[i].selectNo;
						}					
					}
					$scope.qAnswer = multiAnswer;
				}
				
			}
			
		} ]);