var app = angular.module('timeTracker', []);
app.controller('activitiesTracker', function($scope) {
    $scope.activities = [];
    $scope.addActivity = function() {
        newActivity = {name: $scope.newActivityName, time: $scope.newActivityTime};
        $scope.activities.push(newActivity);
        $scope.newActivityName = '';
        $scope.newActivityTime = '';
    }
    $scope.removeActivity = function(x) {
        $scope.activities.splice(x, 1);
    }
});