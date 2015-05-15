
define([
	'jquery',
	'backbone',
	'backboneValidation',
	'jquerySerializeObject',
	'router',
	'Session'
], function($, Backbone, backboneValidation, jquerySerializeObject, Router, Session){

	var ApplicationModel = Backbone.Model.extend({

	    start : function(){
	    	Backbone.history.start();
            var router = new Router();

			$.ajaxSetup({
				statusCode: {
					401: function(){
						// Redirec the to the login page.
						Backbone.history.navigate('login', { trigger : true });

					},
					403: function() {
						// 403 -- Access denied
						Backbone.history.navigate('login', { trigger : true });
					}
				}
			});

			// Extend the callbacks to work with Bootstrap, as used in this example
            // See: http://thedersen.com/projects/backbone-validation/#configuration/callbacks
            _.extend(Backbone.Validation.callbacks, {
                valid: function (view, attr, selector) {
                    var $el = view.$('[name=' + attr + ']'),
                        $group = $el.closest('.form-group');

                    $group.removeClass('has-error');
                    $group.find('.help-block').html('').addClass('hidden');
                },
                invalid: function (view, attr, error, selector) {
                    var $el = view.$('[name=' + attr + ']'),
                        $group = $el.closest('.form-group');

                    $group.addClass('has-error');
                    $group.find('.help-block').html(error).removeClass('hidden');
                }
            });

            $.fn.serializeObject = function () {
                "use strict";
                var a = {}, b = function (b, c) {
                    var d = a[c.name];
                    "undefined" != typeof d && d !== null ? $.isArray(d) ? d.push(c.value) : a[c.name] = [d, c.value] : a[c.name] = c.value
                };
                return $.each(this.serializeArray(), b), a
            };
		}
	});

	return ApplicationModel;
});