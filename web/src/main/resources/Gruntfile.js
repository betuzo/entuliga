"use strict";

module.exports = function(grunt) {

	grunt.initConfig({
        // -- stylus Config ---------------------------------------------------------
        stylus: {
            dev: {
                files: {'./static/css/main.css': ['./static/css/main.styl'] },
                options: { compress: false, linenos: false }
            }
        },

        exec: {
            list_files: {
                command: 'node r.js -o build.js',
                stdout: true
            }
        },


        // -- Watch Config/ rename observe -------------------------------------------------
        observe:{
            options:{
                livereload:true
            },
            scripts:{
                files:[
                    './static/css/main.styl',
                    './static/js/templates/**/*.html',
                    './static/js/views/**/*.js',
                    './static/js/index.html'

                ],
                tasks:[
                    'taskStylus',
                    'taskbuild'
                ]
            }
        },

        // -- jshint Config -----------------------------------------------------------------



    });



    grunt.loadNpmTasks('grunt-contrib-watch');
    grunt.loadNpmTasks('grunt-contrib-stylus');
    grunt.loadNpmTasks('grunt-run');
    grunt.loadNpmTasks('grunt-newer');
    grunt.loadNpmTasks('grunt-exec');
    grunt.loadNpmTasks("grunt-regarde");
    grunt.registerTask('taskStylus', 'stylus:dev');
    grunt.registerTask('taskbuild', 'exec');



    //task default
    grunt.registerTask('default',
        [
            'taskStylus',
            'taskbuild'
        ]
    );

    grunt.renameTask('watch', 'observe');
    grunt.registerTask('watch', ['default', 'observe']);


}