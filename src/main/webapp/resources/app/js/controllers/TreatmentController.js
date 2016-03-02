'use strict';

/**
 * TreatmentController
 * @constructor
 */
var TreatmentController = function($scope, $http) {
    $scope.fetchTreatment = function() {
        $http.get('treatment').success(function(treatments){
            $scope.treatments = treatments;
        });
    };

    $scope.fetchTreatment();
};