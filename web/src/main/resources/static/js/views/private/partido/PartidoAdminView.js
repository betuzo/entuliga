define([
	'jquery',
	'backbone',
	'bootstrap',
	'selecter',
	'fabric',
	'core/BaseView',
	'models/TorneoPartidoModel',
	'views/private/partido/PartidoLocalView',
	'views/private/partido/PartidoVisitaView',
	'views/private/partido/PartidoArbitrosView',
	'views/private/partido/PartidoEditView',
	'views/private/estadistica/EstadisticaResumenView',
	'views/private/estadistica/EstadisticaPuntosView',
	'views/private/estadistica/EstadisticaFaltasView',
	'views/private/estadistica/EstadisticaMovimientosView',
	'views/private/estadistica/EstadisticaAsistenciasView',
	'views/private/estadistica/EstadisticaBloqueosView',
	'views/private/estadistica/EstadisticaRebotesView',
	'views/private/estadistica/EstadisticaRobosView',
	'views/private/estadistica/PuntoCreateView',
	'views/private/estadistica/FaltaCreateView',
	'views/private/estadistica/MovimientoCreateView',
	'views/private/estadistica/AsistenciaCreateView',
	'views/private/estadistica/BloqueoCreateView',
	'views/private/estadistica/ReboteCreateView',
	'views/private/estadistica/RoboCreateView',
	'views/private/util/ModalGenericView',
	'text!templates/private/partido/tplPartidoAdmin.html'
], function($, Backbone, bootstrap, selecter, fabriclib, BaseView, TorneoPartidoModel,
            PartidoLocalView, PartidoVisitaView, PartidoArbitrosView,
            PartidoEditView, EstadisticaResumenView, EstadisticaPuntosView,
            EstadisticaFaltasView, EstadisticaMovimientosView, EstadisticaAsistenciasView,
            EstadisticaBloqueosView, EstadisticaRebotesView, EstadisticaRobosView,
            PuntoCreateView, FaltaCreateView, MovimientoCreateView, AsistenciaCreateView,
            BloqueoCreateView, ReboteCreateView, RoboCreateView, ModalGenericView,
            tplPartidoAdmin){

	var PartidoAdminView = BaseView.extend({
        template: _.template(tplPartidoAdmin),

        events: {
            'click #partido-editar'             : 'partidoEditar',
            'click #agregar-arbitro'            : 'agregarArbitro',
            'click #partido-comenzar'           : 'partidoComenzar',
            'click #partido-cancelar'           : 'partidoCancelar',
            'click #partido-finalizar'          : 'partidoFinalizar',

            'click #partido-puntos-local'       : 'partidoPuntosLocal',
            'click #partido-faltas-local'       : 'partidoFaltasLocal',
            'click #partido-cambios-local'      : 'partidoCambiosLocal',
            'click #partido-asistencias-local'  : 'partidoAsistenciasLocal',
            'click #partido-bloqueos-local'     : 'partidoBloqueosLocal',
            'click #partido-rebotes-local'      : 'partidoRebotesLocal',
            'click #partido-robos-local'        : 'partidoRobosLocal',

            'click #partido-puntos-visita'      : 'partidoPuntosVisita',
            'click #partido-faltas-visita'      : 'partidoFaltasVisita',
            'click #partido-cambios-visita'     : 'partidoCambiosVisita',
            'click #partido-asistencias-visita' : 'partidoAsistenciasVisita',
            'click #partido-bloqueos-visita'    : 'partidoBloqueosVisita',
            'click #partido-rebotes-visita'     : 'partidoRebotesVisita',
            'click #partido-robos-visita'       : 'partidoRobosVisita'
        },

        initialize: function(idPartido) {
            this.model = new TorneoPartidoModel();
            this.listenTo(this.model, 'sync', this.syncPartido);
        },

        render: function() {
            return this;
        },

        syncPartido: function() {
            this.$el.html(this.template(this.model.toJSON()));
            $('#estadistica-tab a').click(function (e) {
              e.preventDefault()
              $(this).tab('show')
            })

            new PartidoLocalView(this.model);
            new PartidoVisitaView(this.model);
            new PartidoArbitrosView(this.model);
            new EstadisticaPuntosView({modelo: this.model, parent: this});
            new EstadisticaFaltasView({modelo: this.model, parent: this});
            new EstadisticaMovimientosView({modelo: this.model, parent: this});
            new EstadisticaAsistenciasView({modelo: this.model, parent: this});
            new EstadisticaBloqueosView({modelo: this.model, parent: this});
            new EstadisticaRebotesView({modelo: this.model, parent: this});
            new EstadisticaRobosView({modelo: this.model, parent: this});
            new EstadisticaResumenView(this.model);
            this.setUpCourt();
        },

        setIdPartido: function(idPartido) {
            this.model.set('id', idPartido);
            this.model.fetch();
        },

        partidoEditar: function() {
            new PartidoEditView({modelo: this.model, callbackAceptar: this.successSavePartido});
        },

        successSavePartido: function(partido) {

        },

        agregarArbitro: function() {

        },

        partidoPuntosLocal: function() {
            new PuntoCreateView({modelo: this.model, callbackAceptar: this.successAddPunto,
                                 origen: 'LOCAL', parent: this});
        },

        partidoPuntosVisita: function() {
            new PuntoCreateView({modelo: this.model, callbackAceptar: this.successAddPunto,
                                 origen: 'VISITA', parent: this});
        },

        successAddPunto: function(punto) {
            if (punto.get('origen') == 'LOCAL') {
                var puntoLocal = this.parent.model.get('localPuntos') + punto.get('tipoValor');
                this.parent.model.set({localPuntos: puntoLocal});
            } else {
                var puntoVisita = this.parent.model.get('visitaPuntos') + punto.get('tipoValor');
                this.parent.model.set({visitaPuntos: puntoVisita});
            }
            $('#section-estadisticas-resumen').html('');
            new EstadisticaResumenView(this.parent.model);
            app.puntosPartido.add(punto);
            this.destroyView();
        },

        successRemovePunto: function(punto) {
            if (punto.get('origen') == 'LOCAL') {
                var puntoLocal = this.model.get('localPuntos') - punto.get('tipoValor');
                this.model.set({localPuntos: puntoLocal});
            } else {
                var puntoVisita = this.model.get('visitaPuntos') - punto.get('tipoValor');
                this.model.set({visitaPuntos: puntoVisita});
            }
            $('#section-estadisticas-resumen').html('');
            new EstadisticaResumenView(this.model);
            app.puntosPartido.remove(punto);
        },

        partidoFaltasLocal: function() {
            new FaltaCreateView({modelo: this.model, callbackAceptar: this.successAddFalta,
                                             origen: 'LOCAL', parent: this});
        },

        partidoFaltasVisita: function() {
            new FaltaCreateView({modelo: this.model, callbackAceptar: this.successAddFalta,
                                             origen: 'VISITA', parent: this});
        },

        successAddFalta: function(falta) {
            app.faltasPartido.add(falta);
            this.destroyView();
        },

        successRemoveFalta: function(falta) {
            app.faltasPartido.remove(falta);
        },

        partidoCambiosLocal: function() {
            new MovimientoCreateView({modelo: this.model, callbackAceptar: this.successAddMovimiento,
                                              origen: 'LOCAL', parent: this});
        },

        partidoCambiosVisita: function() {
            new MovimientoCreateView({modelo: this.model, callbackAceptar: this.successAddMovimiento,
                                              origen: 'VISITA', parent: this});
        },

        successAddMovimiento: function(movimiento) {
            app.movimientosPartido.add(movimiento);
            this.destroyView();
        },

        successRemoveMovimiento: function(movimiento) {
            app.movimientosPartido.remove(movimiento);
        },

        partidoAsistenciasLocal: function() {
            new AsistenciaCreateView({modelo: this.model, callbackAceptar: this.successAddAsistencia,
                                        origen: 'LOCAL', parent: this});
        },

        partidoAsistenciasVisita: function() {
            new AsistenciaCreateView({modelo: this.model, callbackAceptar: this.successAddAsistencia,
                                        origen: 'VISITA', parent: this});
        },

        successAddAsistencia: function(asistencia) {
            app.asistenciasPartido.add(asistencia);
            this.destroyView();
        },

        successRemoveAsistencia: function(asistencia) {
            app.asistenciasPartido.remove(asistencia);
        },

        partidoBloqueosLocal: function() {
            new BloqueoCreateView({modelo: this.model, callbackAceptar: this.successAddBloqueo,
                                        origen: 'LOCAL', parent: this});

        },

        partidoBloqueosVisita: function() {
            new BloqueoCreateView({modelo: this.model, callbackAceptar: this.successAddBloqueo,
                                        origen: 'VISITA', parent: this});
        },

        successAddBloqueo: function(bloqueo) {
            app.bloqueosPartido.add(bloqueo);
            this.destroyView();
        },

        successRemoveBloqueo: function(bloqueo) {
            app.bloqueosPartido.remove(bloqueo);
        },

        partidoRebotesLocal: function() {
            new ReboteCreateView({modelo: this.model, callbackAceptar: this.successAddRebote,
                                        origen: 'LOCAL', parent: this});
        },

        partidoRebotesVisita: function() {
            new ReboteCreateView({modelo: this.model, callbackAceptar: this.successAddRebote,
                                        origen: 'VISITA', parent: this});
        },

        successAddRebote: function(rebote) {
            app.rebotesPartido.add(rebote);
            this.destroyView();
        },

        successRemoveRebote: function(rebote) {
            app.rebotesPartido.remove(rebote);
        },

        partidoRobosLocal: function() {
            new RoboCreateView({modelo: this.model, callbackAceptar: this.successAddRobo,
                                        origen: 'LOCAL', parent: this});
        },

        partidoRobosVisita: function() {
            new RoboCreateView({modelo: this.model, callbackAceptar: this.successAddRobo,
                                        origen: 'VISITA', parent: this});
        },

        successAddRobo: function(robo) {
            app.robosPartido.add(robo);
            this.destroyView();
        },

        successRemoveRobo: function(robo) {
            app.robosPartido.remove(robo);
        },

        partidoComenzar: function() {
            this.model.set({statusPartido: 'ENPROCESO'});
            this.saveModel();
        },

        partidoCancelar: function() {
            this.model.set({statusPartido: 'CANCELADO'});
            this.saveModel();
        },

        partidoFinalizar: function() {
            this.model.set({statusPartido: 'FINALIZADO'});
            this.saveModel();
        },

        saveModel: function() {
            that = this;
            if(this.model.isValid(true)){
                this.model.save({}, {
                    wait:true,
                    success:function(model, response) {
                        new ModalGenericView({message: 'Partido registrado correctamente'});
                    },
                    error: function(model, error) {
                        new ModalGenericView({message: 'Se presento un error al registrar el partido'});
                    }
                });
            }
        },

        setUpCourt: function() {
            var canvas = new fabric.Canvas('canvas');
            var position = {left: 0, top:0};
            var isOver = false;
            var strPath = 'M 192.3004,118.04878 H 196.63056 V 139.6996 C 196.63056,140.89553 197.59971,141.86468 198.79564,141.86468 H 216.1163 C 217.31223,141.86468 218.28137,140.89553 218.28137,139.6996 V 118.04878 H 222.61154 C 223.80747,118.04878 224.77662,117.07964 224.77662,115.88371 V 109.38846 C 224.77662,108.19252 223.80747,107.22338 222.61154,107.22338 H 213.95121 C 213.95121,110.81065 211.04324,113.71862 207.45597,113.71862 203.8687,113.71862 200.96072,110.81065 200.96072,107.22338 H 192.3004 C 191.10446,107.22338 190.13532,108.19252 190.13532,109.38846 V 115.88371 C 190.13532,117.07964 191.10446,118.04878 192.3004,118.04878 z';
            var tolerancia = 20;

            canvas.setBackgroundImage('img/courtbasket.png', canvas.renderAll.bind(canvas), {
                backgroundImageOpacity: 0.5,
                backgroundImageStretch: false
            });

            var path1 = new fabric.Path(strPath, {
                fill: 'blue',
                left: 50,
                top: 50,
                hasRotatingPoint: false,
                hasBorders: false,
                hasControls: false
            });
            canvas.add(path1);

            var path2 = new fabric.Path(strPath, {
                fill: 'red',
                left: 150,
                top: 150,
                hasRotatingPoint: false,
                hasBorders: false,
                hasControls: false
            });
            canvas.add(path2);
            canvas.renderAll();

            canvas.on('object:moving', function (e) {
                if ((e.target.left > path1.left - tolerancia && e.target.left < path1.left + tolerancia) &&
                (e.target.top > path1.top - tolerancia && e.target.top < path1.top + tolerancia) ) {
                    path1.setOpacity(0.5);
                    path1.scale(1.2);
                    isOver = true;
                } else {
                    path1.setOpacity(1);
                    path1.scale(1);
                    isOver = false;
                }
            });

            canvas.on('mouse:down', function (e) {
                if (e.target !== undefined) {
                    position.left = e.target.left;
                    position.top = e.target.top;
                }
            });

            canvas.on('mouse:up', function (e) {
                if (e.target === undefined) {
                    return;
                }
                console.log('up: ' + e.target.fill);
                if (isOver) {
                    e.target.set('left', path1.left);
                    e.target.set('top', path1.top);
                    path1.set('left', position.left);
                    path1.set('top', position.top);
                    path1.setOpacity(1);
                    path1.scale(1);
                } else {
                    e.target.set('left', position.left);
                    e.target.set('top', position.top);
                }
                path1.setCoords();
                e.target.setCoords();
                canvas.renderAll();
            });
        }
	});

	return PartidoAdminView;

});