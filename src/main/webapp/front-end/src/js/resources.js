'use strict';

var app = angular.module('DentoCareApp.factory', ['ngResource']);

app.factory("Post", function($resource) {
    return $resource("/patients");
});

app.factory("Treatments", function($resource) {
    return $resource("/treatments");
});

app.value('version', '0.1');
