'use strict';

var app = angular.module('DentoCareApp.factory', ['ngResource']);

app.factory("Post", function($resource) {
    return $resource("/patients/all");
});

app.value('version', '0.1');
