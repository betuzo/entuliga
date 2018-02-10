define([
  'jquery',
  'backbone',
  'marionette',
  'subroute',
  'core/Transition'
], function($, Backbone, Mn, Subroute, Transition) {

  var currentView;
  // var transitionType = $('#container-body').data('transition');

  var ViewManager = Mn.Object.extend({
    initialize: function() {
      console.log("initialize View Manager");
      this.transitionType = $('#container-body').data('transition');
    },

    showView: function(view) {
      this.transitionType = $('#container-body').data('transition');

      var that = this;
      this.disposeView(currentView, function() {
        that.renderView(view);
      });
    },


    disposeView: function(view, callback) {
      console.log("disposeView");
      if(!view) {
        return callback();
      }

      return applyTransition(view.$el, this.transitionType, function() {
        _disposeView(view);
        return callback();
      });

      function applyTransition(el, name, callback) {
        if(!name) {
          return callback();
        }
        var trans = new Transition();
        return trans.apply(el, name, callback);
      }

      function _disposeView(view) {
        view.subviews && view.subviews.forEach(function(subview) {
          _disposeView(subview);
        });
        view.remove();
      }
    },

    renderView: function(view) {
      currentView = view;
      $("#container-body").html(currentView.render().$el);
    }

  });

  return ViewManager;
});
