/**
 * Created by lenovo on 2018/3/8.
 */
var addtModule = angular.module("app.addTea", []);
addtModule.controller('addTeaController',['$scope', '$http', '$location',
		function($scope, $http, $location) {
			$scope.selects = [''];
			$scope.major = [];
			$scope.year = [];
			$scope.clas = [];
			$scope.inpts = [''];
			$scope.sub = [];
			
			//继续添加专业年级班级 信息时调用
			$scope.newCls = function(){
				$scope.selects.push(['']);
			}
			
			//继续添加新课程时调用
		    $scope.newSub = function () {
		        var newin = [''];
		        $scope.inpts.push(newin);		       
		    };
		    //临时删除新课程时调用
		    $scope.delSub = function ($index) {
		        $scope.inpts.splice($index, 1);
		        $scope.sub.splice($index, 1);
		        //从$index位置开始删除一个元素，即删除当前元素
		    }
		    
		    //确认提交
			$scope.add = function(){
				var user = {
						tNo: $scope.tno,
						tPassword: $scope.pwd,
						tName: $scope.name,
						tSex: $scope.sex,
						major: $scope.major,
						year: $scope.year,
						clas: $scope.clas,
						subjects: $scope.sub
				};

				var transFn = function(user) {  
	                return $.param(user, true);  
	            };  
	           
				$http({
					method: "POST",
					url: "AddTeacherServlet",
					data: user,
					headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},  
	                transformRequest: transFn 
				}).then(function successCallBack(response){
					$scope.showMess = true;
					$scope.mess = response.data.mess;
					if(response.data.mess == 'success'){
						$location.path('/a_tMess');
					}					
				});	            
			}
			
			//添加课程时 获取已有课程
			$http({
				method: 'POST',
				url: 'FindSubMessServlet'
			}).then(function successCallBack(subMess){
				$scope.subjects = subMess.data;
			}, function errorCallBack(){
				console.log("失败");
			});
		} ]);