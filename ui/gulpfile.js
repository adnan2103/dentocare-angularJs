'use strict';

var gulp = require('gulp');
var uglify = require('gulp-uglify');


gulp.task('build', function() {
	console.log('In side Build Function.');
	gulp.src('src/js/**/*.js')
	.pipe(gulp.dest('../src/main/webapp/public/js'))
	
	gulp.src('src/view/**/*.html')
	.pipe(gulp.dest('../src/main/webapp/public/view'))
	
	gulp.src('src/css/*.css')
	.pipe(gulp.dest('../src/main/webapp/public/css'))
	
	gulp.src('src/images/**/*')
	.pipe(gulp.dest('../src/main/webapp/public/images'))
	
	gulp.src('bower_components/**/*')
	.pipe(gulp.dest('../src/main/webapp/public/bower_components'))
});


gulp.task('clean', function() {
	console.log('In side clean Function.');
});

/**
 *  Default task clean temporaries directories and launch the
 *  main optimization build task
 */
gulp.task('default', ['clean'], function () {
  gulp.start('build');
});