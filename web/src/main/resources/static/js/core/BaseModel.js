define([
    'underscore',
    'backbone'
], function(_, Backbone){

    var BaseModel = Backbone.Model.extend({

        defaults: {
        },

        initialize: function() {
          _.each(this.attributes, function (val, key) {
            this.set(key, _.escape(val));
          }, this);
        }


    });
	return BaseModel;
});
