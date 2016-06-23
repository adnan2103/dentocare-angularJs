'use strict';

/**
 * TreatmentImageController
 * @constructor
 */
var TreatmentImageController = function($scope, $http, $routeParams) {

    $scope.fetchTreatmentImagePaths = function() {

        $http.get('treatment/' + $routeParams.id + '/' + $routeParams.type + '/images' )
            .success(function(treatmentImages){
                $scope.treatmentImages = treatmentImages;
            });

        $scope.type = $routeParams.type;
    };

    $scope.fetchTreatmentImagePaths();

};