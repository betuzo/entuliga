require.config({
    shim: {
        underscore: {
            exports: '_'
        },
        backbone: {
            deps: [
                'underscore',
                'jquery'
            ],
            exports: 'Backbone'
        },

        backboneValidation: {
            deps: [
                'jquery',
                'underscore',
                'backbone'
            ],
            exports: 'Backbone.Validation'
        },

        marionette:{
            deps : ['jquery', 'underscore', 'backbone'],
            exports : 'Mn'
        },

        bootstrap: {
            deps: [
                'jquery'
            ]
        },
        selecter: {
            deps: [
                'jquery'
            ]
        },
        jquerycookie:{
            deps: [
                'jquery'
            ]        },
        jquerySerializeObject: {
            deps: [
                'jquery'
            ]
        },
        datetimepicker: {
            deps: [
                'jquery'
            ]
        },
        bloodhound: {
            deps: [
                'jquery'
            ]
        },
        typeahead: {
            deps: [
                'jquery'
            ]
        },
        fabric: {
            deps: [
                'jquery'
            ]
        },

    },

    paths: {
        'backbone': 'vendor/backbone/backbone-min',
        'jquery': 'vendor/jquery/jquery',
        'underscore': 'vendor/underscore/underscore-min',
        'marionette': 'vendor/backbone/backbone-marionette/backbone.marionette',
        'backbone.radio': 'vendor/backbone/backbone-radio/backbone.radio.min',
        'subroute': 'vendor/backbone/backbone-subroute/backbone.subroute.min',
        'jquerycookie': 'vendor/jquery/cookie/jquery.cookie',
        'jscookie': 'vendor/js-cookie/js.cookie',
        'jquerySerializeObject': 'vendor/jquery/serializeObject/jquery.serializeObject.min',
        'backboneValidation': 'vendor/backbone/backbone-validation/backbone-validation-amd-min',
        'text' : 'vendor/requirejs-text/text',
        'bootstrap': 'vendor/bootstrap/bootstrap',
        'selecter': 'vendor/bootstrap/select/bootstrap-select.min',
        'datetimepicker': 'vendor/bootstrap/datetimepicker/bootstrap-datetimepicker',
        'dateformat': 'vendor/date-format/date.format',
        'bloodhound': 'vendor/typeahead/bloodhound',
        'typeahead': 'vendor/typeahead/typeahead.jquery',
        'fabric': 'vendor/fabric/fabric.min'

    }
});


require([
    'app',
    'session'
], function (App, Session) {
  console.log("**Main**");
    var app = new App();
    app.ADN = "ADN";
    app.start();
});
