'use strict';

/**
 * Clinic Treatments controller.
 * @Constructor
 */

var TreatmentsController = function($scope, Treatments) {
    Treatments.query(function(data) {
        $scope.treatments = data;
    });

    $scope.getTreatmentCost = function (patientOralExamination) {
        var totalTreatmentCost = 0;
        angular.forEach(patientOralExamination, function(examination) {
            totalTreatmentCost += examination.cost;
        });

        return totalTreatmentCost;
    };

    $scope.getPaidAmount = function (payments) {
        var paidAmount = 0;
        angular.forEach(payments, function(payment) {
            paidAmount += payment.paymentAmount;
        });

        return paidAmount;
    }
};
