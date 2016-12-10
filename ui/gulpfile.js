'use strict';

var gulp = require('gulp'),
uglify = require('gulp-uglify'),
del = require('del'),
concat = require('gulp-concat'),
rename = require('gulp-rename');


gulp.task('script', function() {
	gulp.src('src/js/**/*.js')
	//.pipe(uglify())
	//.pipe(concat('dento-care-main.min.js'))
	.pipe(gulp.dest('dist/js'))
	
});

gulp.task('html', function() {
	gulp.src('src/**/*.+(html|ico)')
	.pipe(gulp.dest('dist'))	
});

gulp.task('css', function() {	
	gulp.src('src/css/*.css')
	.pipe(gulp.dest('dist/css'))
	
});

gulp.task('image', function() {
	gulp.src('src/images/**/*')
	.pipe(gulp.dest('dist/images'))
});

gulp.task('bower', function() {
	gulp.src('bower_components/**/*')
	.pipe(gulp.dest('dist/bower_components'))
});

gulp.task('build:clean', function() {
	del(['dist/**']);
	del(['../src/main/webapp/**'],{force: true});
});

gulp.task('build', ['script','html','css','image','bower']);

gulp.task('deploy', function() {
	gulp.src('dist/**')
	.pipe(gulp.dest('../src/main/webapp/front-end/src'))	
});

/**
 *  Default task clean temporaries directories and launch the
 *  main optimization build task
 */
gulp.task('default', ['build'], function () {
});