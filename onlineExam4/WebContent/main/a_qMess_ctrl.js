
var aqModule = angular.module("app.aqMess", []);
aqModule.controller('aqMessController',['$scope', '$http', '$location', '$route',
    function ($scope, $http, $location, $route) {
		//获取考试基本信息
		$http({
			method: 'POST',
			url: 'FindQuestionMessServlet'
		}).then(function successCallBack(response){
			$scope.questions = response.data;
		}, function errorCallBack(){
			console.log("失败");
		});
		
		//根据考试名或题目匹配查找
		$scope.byEname = "";
		$scope.byQtitle = "";
		$scope.findByEname = function(){
			$scope.byQtitle = "";
			if($scope.byEname == ""){
				alert("请输入关键字");
			}else{
				var info = {
			        	eName: $scope.byEname, 
			        	qTitle: $scope.byQtitle,
			        	flag: "findByEname"
			        };
			        var transFn = function(info) {  
			            return $.param(info);  
			        };  
			       
					$http({
						method: "POST",
						url: "FindQuestionMessServlet",
						data: info,
						headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},  
			            transformRequest: transFn 
					}).then(function successCallBack(response){
						if(response.data.mess != null){
							alert(response.data.mess);
						}else{
							$scope.questions = response.data;
						}				
					}, function errorCallBack(){
						console.log("失败");
					});
			}			
	        
		}
		$scope.findByQtitle = function(){
			$scope.byEname = "";
			if($scope.byQtitle == ""){
				alert("请输入关键字");
			}else{
				var info = {
			        	eName: $scope.byEname, 
			        	qTitle: $scope.byQtitle,
			        	flag: "findByQtitle"
			    };
		        var transFn = function(info) {  
		            return $.param(info);  
		        };  
		       
				$http({
					method: "POST",
					url: "FindQuestionMessServlet",
					data: info,
					headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},  
		            transformRequest: transFn 
				}).then(function successCallBack(response){
					if(response.data.mess != null){
						alert(response.data.mess);
					}else{
						$scope.questions = response.data;
					}
				}, function errorCallBack(){
					console.log("失败");
				});
			}
			
		}
		
		$scope.reload = function(){
			$route.reload();
		}
		
		//删除题目
		$scope.delQuestion = function(id){
			var isDel = confirm("该题目将被删除，确定继续？");
			if(isDel){			
				$http({
					method: "POST",
					url: "DeletePaperServlet",
					params: {id: id},
				}).then(function successCallBack(response){
					if(response.data.mess == 'success'){
						$route.reload();
					}
					
				});
			}
		}
		
		//修改按钮被点击 传值并赋值
		$scope.updateClick = function(id, qNo, qType, qScore, qTitle, qSelection, qAnswer){
			$scope.updating = true;
			$scope.upId = id;
			$scope.upQno = parseInt(qNo);
			$scope.upQtype = qType;
			$scope.upQtitle = qTitle;			
			$scope.upQscore = parseFloat(qScore);
			$scope.upSelects = qSelection;
			$scope.upQanswer = qAnswer;
			$scope.upMess = "";
		}
		
		//修改执行
		$scope.update = function(){
			if($scope.upQno == undefined || $scope.upQno == ''){
				$scope.upMess = "请输入题号";
			}else if(isNaN($scope.upQno)){
				$scope.upMess = "题号请输入数字";
			}else if($scope.upQscore == undefined || $scope.upQscore == ''){
				$scope.upMess = "请输入题目分值";
			}else if(isNaN($scope.upQscore)){
				$scope.upMess = "题目分值请输入数字";
			}else if($scope.upQtitle == undefined || $scope.upQtitle == ''){
				$scope.upMess = "请输入题干内容";
			}else if($scope.upQanswer == undefined || $scope.upQanswer == ''){
				$scope.upMess = "请设置答案";
			}else{
				if($scope.upQtype == '单选题' || $scope.upQtype == '多选题'){				
					var noSelection = 0;
					for(var i in $scope.upSelects){
						if($scope.upSelects[i].selection == undefined || $scope.upSelects[i].selection == ""){
							noSelection++;
						}
					}
					if(noSelection == $scope.upSelects.length){
						$scope.upMess = "请至少设置一个选项内容";
					}else{
						$scope.upMess = "";
					}					
				}else{
					$scope.upMess = "";
				}
			}
			if($scope.upMess == ""){
				var qSelections = '';
				if($scope.upQtype == '单选题' || $scope.upQtype == '多选题'){
					for(var i in $scope.upSelects){
						if($scope.upSelects[i].selectNo != "" && $scope.upSelects[i].selection != ""){
							qSelections = qSelections + $scope.upSelects[i].selectNo + "." + $scope.upSelects[i].selection + ";";
						}												
					}
					qSelections = qSelections.slice(0, qSelections.length-1); //去掉最后一个分号
				}
				var info = {
				    id: $scope.upId,
					qNo: $scope.upQno,
					qType: $scope.upQtype,
					qScore: $scope.upQscore,
					qTitle: $scope.upQtitle,
					qSelection: qSelections,
					qAnswer: $scope.upQanswer
				}
				var transFn = function(data) {  
		            return $.param(data);  
		        };  
		       
				$http({
					method: "POST",
					url: "UpdatePaperServlet",
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
		//修改时临时增加选项
		$scope.addSelect = function(){	
			if($scope.upSelects == null){
				$scope.upSelects = [
					{"selectNo": "A", "selection": ""}
				];
			}else{
				//获取当前最后一个选项的编码
				var lastSelectNo = $scope.upSelects[$scope.upSelects.length-1].selectNo.charCodeAt();
				//形成新的末尾选项
				var newLastNo = String.fromCharCode(lastSelectNo + 1);
				var newin = {"selectNo": ""+ newLastNo +"", "selection": ""};
				$scope.upSelects.push(newin);
			}
			
		}		
		//修改时临时删除选项
		$scope.delSelect = function($index){
			$scope.upSelects.splice($index, 1);
		}
		
		//查看详情被点击
		$scope.detail = function(qNo, qType, qScore, qTitle, qSelection, qAnswer){
			$scope.seeDetail = true;
			$scope.detailQno = parseInt(qNo);
			$scope.detailQtype = qType;
			$scope.detailQtitle = qTitle;			
			$scope.detailQscore = parseFloat(qScore);
			$scope.detailQselection = qSelection;
			$scope.detailQanswer = qAnswer;
		}
	}]
);