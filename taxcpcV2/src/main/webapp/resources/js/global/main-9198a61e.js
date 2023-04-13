/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/*!
 * Bootstrap v3.1.1 (http://getbootstrap.com)
 * Copyright 2011-2014 Twitter, Inc.
 * Licensed under MIT (https://github.com/twbs/bootstrap/blob/master/LICENSE)
 */
if (!function(t, e) {
    "object" == typeof module && "object" == typeof module.exports ? module.exports = t.document ? e(t, !0) : function(t) {
        if (!t.document)
            throw new Error("jQuery requires a window with a document");
        return e(t)
    } : e(t)
}("undefined" != typeof window ? window : this, function(t, e) {
    function n(t) {
        var e = t.length, n = oe.type(t);
        return"function" === n || oe.isWindow(t) ? !1 : 1 === t.nodeType && e ? !0 : "array" === n || 0 === e || "number" == typeof e && e > 0 && e - 1 in t
    }
    function i(t, e, n) {
        if (oe.isFunction(e))
            return oe.grep(t, function(t, i) {
                return!!e.call(t, i, t) !== n
            });
        if (e.nodeType)
            return oe.grep(t, function(t) {
                return t === e !== n
            });
        if ("string" == typeof e) {
            if (pe.test(e))
                return oe.filter(e, t, n);
            e = oe.filter(e, t)
        }
        return oe.grep(t, function(t) {
            return oe.inArray(t, e) >= 0 !== n
        })
    }
    function s(t, e) {
        do
            t = t[e];
        while (t && 1 !== t.nodeType);
        return t
    }
    function o(t) {
        var e = xe[t] = {};
        return oe.each(t.match(we) || [], function(t, n) {
            e[n] = !0
        }), e
    }
    function r() {
        me.addEventListener ? (me.removeEventListener("DOMContentLoaded", a, !1), t.removeEventListener("load", a, !1)) : (me.detachEvent("onreadystatechange", a), t.detachEvent("onload", a))
    }
    function a() {
        (me.addEventListener || "load" === event.type || "complete" === me.readyState) && (r(), oe.ready())
    }
    function l(t, e, n) {
        if (void 0 === n && 1 === t.nodeType) {
            var i = "data-" + e.replace(je, "-$1").toLowerCase();
            if (n = t.getAttribute(i), "string" == typeof n) {
                try {
                    n = "true" === n ? !0 : "false" === n ? !1 : "null" === n ? null : +n + "" === n ? +n : Ce.test(n) ? oe.parseJSON(n) : n
                } catch (s) {
                }
                oe.data(t, e, n)
            } else
                n = void 0
        }
        return n
    }
    function u(t) {
        var e;
        for (e in t)
            if (("data" !== e || !oe.isEmptyObject(t[e])) && "toJSON" !== e)
                return!1;
        return!0
    }
    function c(t, e, n, i) {
        if (oe.acceptData(t)) {
            var s, o, r = oe.expando, a = t.nodeType, l = a ? oe.cache : t, u = a ? t[r] : t[r] && r;
            if (u && l[u] && (i || l[u].data) || void 0 !== n || "string" != typeof e)
                return u || (u = a ? t[r] = Y.pop() || oe.guid++ : r), l[u] || (l[u] = a ? {} : {toJSON: oe.noop}), ("object" == typeof e || "function" == typeof e) && (i ? l[u] = oe.extend(l[u], e) : l[u].data = oe.extend(l[u].data, e)), o = l[u], i || (o.data || (o.data = {}), o = o.data), void 0 !== n && (o[oe.camelCase(e)] = n), "string" == typeof e ? (s = o[e], null == s && (s = o[oe.camelCase(e)])) : s = o, s
        }
    }
    function h(t, e, n) {
        if (oe.acceptData(t)) {
            var i, s, o = t.nodeType, r = o ? oe.cache : t, a = o ? t[oe.expando] : oe.expando;
            if (r[a]) {
                if (e && (i = n ? r[a] : r[a].data)) {
                    oe.isArray(e) ? e = e.concat(oe.map(e, oe.camelCase)) : e in i ? e = [e] : (e = oe.camelCase(e), e = e in i ? [e] : e.split(" ")), s = e.length;
                    for (; s--; )
                        delete i[e[s]];
                    if (n ? !u(i) : !oe.isEmptyObject(i))
                        return
                }
                (n || (delete r[a].data, u(r[a]))) && (o ? oe.cleanData([t], !0) : ie.deleteExpando || r != r.window ? delete r[a] : r[a] = null)
            }
        }
    }
    function d() {
        return!0
    }
    function p() {
        return!1
    }
    function f() {
        try {
            return me.activeElement
        } catch (t) {
        }
    }
    function m(t) {
        var e = qe.split("|"), n = t.createDocumentFragment();
        if (n.createElement)
            for (; e.length; )
                n.createElement(e.pop());
        return n
    }
    function v(t, e) {
        var n, i, s = 0, o = typeof t.getElementsByTagName !== ke ? t.getElementsByTagName(e || "*") : typeof t.querySelectorAll !== ke ? t.querySelectorAll(e || "*") : void 0;
        if (!o)
            for (o = [], n = t.childNodes || t; null != (i = n[s]); s++)
                !e || oe.nodeName(i, e) ? o.push(i) : oe.merge(o, v(i, e));
        return void 0 === e || e && oe.nodeName(t, e) ? oe.merge([t], o) : o
    }
    function g(t) {
        Ne.test(t.type) && (t.defaultChecked = t.checked)
    }
    function y(t, e) {
        return oe.nodeName(t, "table") && oe.nodeName(11 !== e.nodeType ? e : e.firstChild, "tr") ? t.getElementsByTagName("tbody")[0] || t.appendChild(t.ownerDocument.createElement("tbody")) : t
    }
    function b(t) {
        return t.type = (null !== oe.find.attr(t, "type")) + "/" + t.type, t
    }
    function w(t) {
        var e = Ye.exec(t.type);
        return e ? t.type = e[1] : t.removeAttribute("type"), t
    }
    function x(t, e) {
        for (var n, i = 0; null != (n = t[i]); i++)
            oe._data(n, "globalEval", !e || oe._data(e[i], "globalEval"))
    }
    function _(t, e) {
        if (1 === e.nodeType && oe.hasData(t)) {
            var n, i, s, o = oe._data(t), r = oe._data(e, o), a = o.events;
            if (a) {
                delete r.handle, r.events = {};
                for (n in a)
                    for (i = 0, s = a[n].length; s > i; i++)
                        oe.event.add(e, n, a[n][i])
            }
            r.data && (r.data = oe.extend({}, r.data))
        }
    }
    function S(t, e) {
        var n, i, s;
        if (1 === e.nodeType) {
            if (n = e.nodeName.toLowerCase(), !ie.noCloneEvent && e[oe.expando]) {
                s = oe._data(e);
                for (i in s.events)
                    oe.removeEvent(e, i, s.handle);
                e.removeAttribute(oe.expando)
            }
            "script" === n && e.text !== t.text ? (b(e).text = t.text, w(e)) : "object" === n ? (e.parentNode && (e.outerHTML = t.outerHTML), ie.html5Clone && t.innerHTML && !oe.trim(e.innerHTML) && (e.innerHTML = t.innerHTML)) : "input" === n && Ne.test(t.type) ? (e.defaultChecked = e.checked = t.checked, e.value !== t.value && (e.value = t.value)) : "option" === n ? e.defaultSelected = e.selected = t.defaultSelected : ("input" === n || "textarea" === n) && (e.defaultValue = t.defaultValue)
        }
    }
    function k(e, n) {
        var i = oe(n.createElement(e)).appendTo(n.body), s = t.getDefaultComputedStyle ? t.getDefaultComputedStyle(i[0]).display : oe.css(i[0], "display");
        return i.detach(), s
    }
    function C(t) {
        var e = me, n = tn[t];
        return n || (n = k(t, e), "none" !== n && n || (Ze = (Ze || oe("<iframe frameborder='0' width='0' height='0'/>")).appendTo(e.documentElement), e = (Ze[0].contentWindow || Ze[0].contentDocument).document, e.write(), e.close(), n = k(t, e), Ze.detach()), tn[t] = n), n
    }
    function j(t, e) {
        return{get: function() {
                var n = t();
                return null != n ? n ? void delete this.get : (this.get = e).apply(this, arguments) : void 0
            }}
    }
    function T(t, e) {
        if (e in t)
            return e;
        for (var n = e.charAt(0).toUpperCase() + e.slice(1), i = e, s = fn.length; s--; )
            if (e = fn[s] + n, e in t)
                return e;
        return i
    }
    function E(t, e) {
        for (var n, i, s, o = [], r = 0, a = t.length; a > r; r++)
            i = t[r], i.style && (o[r] = oe._data(i, "olddisplay"), n = i.style.display, e ? (o[r] || "none" !== n || (i.style.display = ""), "" === i.style.display && Pe(i) && (o[r] = oe._data(i, "olddisplay", C(i.nodeName)))) : o[r] || (s = Pe(i), (n && "none" !== n || !s) && oe._data(i, "olddisplay", s ? n : oe.css(i, "display"))));
        for (r = 0; a > r; r++)
            i = t[r], i.style && (e && "none" !== i.style.display && "" !== i.style.display || (i.style.display = e ? o[r] || "" : "none"));
        return t
    }
    function P(t, e, n) {
        var i = cn.exec(e);
        return i ? Math.max(0, i[1] - (n || 0)) + (i[2] || "px") : e
    }
    function A(t, e, n, i, s) {
        for (var o = n === (i ? "border" : "content") ? 4 : "width" === e ? 1 : 0, r = 0; 4 > o; o += 2)
            "margin" === n && (r += oe.css(t, n + Ee[o], !0, s)), i ? ("content" === n && (r -= oe.css(t, "padding" + Ee[o], !0, s)), "margin" !== n && (r -= oe.css(t, "border" + Ee[o] + "Width", !0, s))) : (r += oe.css(t, "padding" + Ee[o], !0, s), "padding" !== n && (r += oe.css(t, "border" + Ee[o] + "Width", !0, s)));
        return r
    }
    function N(t, e, n) {
        var i = !0, s = "width" === e ? t.offsetWidth : t.offsetHeight, o = en(t), r = ie.boxSizing() && "border-box" === oe.css(t, "boxSizing", !1, o);
        if (0 >= s || null == s) {
            if (s = nn(t, e, o), (0 > s || null == s) && (s = t.style[e]), on.test(s))
                return s;
            i = r && (ie.boxSizingReliable() || s === t.style[e]), s = parseFloat(s) || 0
        }
        return s + A(t, e, n || (r ? "border" : "content"), i, o) + "px"
    }
    function L(t, e, n, i, s) {
        return new L.prototype.init(t, e, n, i, s)
    }
    function H() {
        return setTimeout(function() {
            mn = void 0
        }), mn = oe.now()
    }
    function F(t, e) {
        var n, i = {height: t}, s = 0;
        for (e = e?1:0; 4 > s; s += 2 - e)
            n = Ee[s], i["margin" + n] = i["padding" + n] = t;
        return e && (i.opacity = i.width = t), i
    }
    function M(t, e, n) {
        for (var i, s = (xn[e] || []).concat(xn["*"]), o = 0, r = s.length; r > o; o++)
            if (i = s[o].call(n, e, t))
                return i
    }
    function D(t, e, n) {
        var i, s, o, r, a, l, u, c, h = this, d = {}, p = t.style, f = t.nodeType && Pe(t), m = oe._data(t, "fxshow");
        n.queue || (a = oe._queueHooks(t, "fx"), null == a.unqueued && (a.unqueued = 0, l = a.empty.fire, a.empty.fire = function() {
            a.unqueued || l()
        }), a.unqueued++, h.always(function() {
            h.always(function() {
                a.unqueued--, oe.queue(t, "fx").length || a.empty.fire()
            })
        })), 1 === t.nodeType && ("height"in e || "width"in e) && (n.overflow = [p.overflow, p.overflowX, p.overflowY], u = oe.css(t, "display"), c = C(t.nodeName), "none" === u && (u = c), "inline" === u && "none" === oe.css(t, "float") && (ie.inlineBlockNeedsLayout && "inline" !== c ? p.zoom = 1 : p.display = "inline-block")), n.overflow && (p.overflow = "hidden", ie.shrinkWrapBlocks() || h.always(function() {
            p.overflow = n.overflow[0], p.overflowX = n.overflow[1], p.overflowY = n.overflow[2]
        }));
        for (i in e)
            if (s = e[i], gn.exec(s)) {
                if (delete e[i], o = o || "toggle" === s, s === (f ? "hide" : "show")) {
                    if ("show" !== s || !m || void 0 === m[i])
                        continue;
                    f = !0
                }
                d[i] = m && m[i] || oe.style(t, i)
            }
        if (!oe.isEmptyObject(d)) {
            m ? "hidden"in m && (f = m.hidden) : m = oe._data(t, "fxshow", {}), o && (m.hidden = !f), f ? oe(t).show() : h.done(function() {
                oe(t).hide()
            }), h.done(function() {
                var e;
                oe._removeData(t, "fxshow");
                for (e in d)
                    oe.style(t, e, d[e])
            });
            for (i in d)
                r = M(f ? m[i] : 0, i, h), i in m || (m[i] = r.start, f && (r.end = r.start, r.start = "width" === i || "height" === i ? 1 : 0))
        }
    }
    function q(t, e) {
        var n, i, s, o, r;
        for (n in t)
            if (i = oe.camelCase(n), s = e[i], o = t[n], oe.isArray(o) && (s = o[1], o = t[n] = o[0]), n !== i && (t[i] = o, delete t[n]), r = oe.cssHooks[i], r && "expand"in r) {
                o = r.expand(o), delete t[i];
                for (n in o)
                    n in t || (t[n] = o[n], e[n] = s)
            } else
                e[i] = s
    }
    function O(t, e, n) {
        var i, s, o = 0, r = wn.length, a = oe.Deferred().always(function() {
            delete l.elem
        }), l = function() {
            if (s)
                return!1;
            for (var e = mn || H(), n = Math.max(0, u.startTime + u.duration - e), i = n / u.duration || 0, o = 1 - i, r = 0, l = u.tweens.length; l > r; r++)
                u.tweens[r].run(o);
            return a.notifyWith(t, [u, o, n]), 1 > o && l ? n : (a.resolveWith(t, [u]), !1)
        }, u = a.promise({elem: t, props: oe.extend({}, e), opts: oe.extend(!0, {specialEasing: {}}, n), originalProperties: e, originalOptions: n, startTime: mn || H(), duration: n.duration, tweens: [], createTween: function(e, n) {
                var i = oe.Tween(t, u.opts, e, n, u.opts.specialEasing[e] || u.opts.easing);
                return u.tweens.push(i), i
            }, stop: function(e) {
                var n = 0, i = e ? u.tweens.length : 0;
                if (s)
                    return this;
                for (s = !0; i > n; n++)
                    u.tweens[n].run(1);
                return e ? a.resolveWith(t, [u, e]) : a.rejectWith(t, [u, e]), this
            }}), c = u.props;
        for (q(c, u.opts.specialEasing); r > o; o++)
            if (i = wn[o].call(u, t, c, u.opts))
                return i;
        return oe.map(c, M, u), oe.isFunction(u.opts.start) && u.opts.start.call(t, u), oe.fx.timer(oe.extend(l, {elem: t, anim: u, queue: u.opts.queue})), u.progress(u.opts.progress).done(u.opts.done, u.opts.complete).fail(u.opts.fail).always(u.opts.always)
    }
    function R(t) {
        return function(e, n) {
            "string" != typeof e && (n = e, e = "*");
            var i, s = 0, o = e.toLowerCase().match(we) || [];
            if (oe.isFunction(n))
                for (; i = o[s++]; )
                    "+" === i.charAt(0) ? (i = i.slice(1) || "*", (t[i] = t[i] || []).unshift(n)) : (t[i] = t[i] || []).push(n)
        }
    }
    function I(t, e, n, i) {
        function s(a) {
            var l;
            return o[a] = !0, oe.each(t[a] || [], function(t, a) {
                var u = a(e, n, i);
                return"string" != typeof u || r || o[u] ? r ? !(l = u) : void 0 : (e.dataTypes.unshift(u), s(u), !1)
            }), l
        }
        var o = {}, r = t === Vn;
        return s(e.dataTypes[0]) || !o["*"] && s("*")
    }
    function B(t, e) {
        var n, i, s = oe.ajaxSettings.flatOptions || {};
        for (i in e)
            void 0 !== e[i] && ((s[i] ? t : n || (n = {}))[i] = e[i]);
        return n && oe.extend(!0, t, n), t
    }
    function W(t, e, n) {
        for (var i, s, o, r, a = t.contents, l = t.dataTypes; "*" === l[0]; )
            l.shift(), void 0 === s && (s = t.mimeType || e.getResponseHeader("Content-Type"));
        if (s)
            for (r in a)
                if (a[r] && a[r].test(s)) {
                    l.unshift(r);
                    break
                }
        if (l[0]in n)
            o = l[0];
        else {
            for (r in n) {
                if (!l[0] || t.converters[r + " " + l[0]]) {
                    o = r;
                    break
                }
                i || (i = r)
            }
            o = o || i
        }
        return o ? (o !== l[0] && l.unshift(o), n[o]) : void 0
    }
    function z(t, e, n, i) {
        var s, o, r, a, l, u = {}, c = t.dataTypes.slice();
        if (c[1])
            for (r in t.converters)
                u[r.toLowerCase()] = t.converters[r];
        for (o = c.shift(); o; )
            if (t.responseFields[o] && (n[t.responseFields[o]] = e), !l && i && t.dataFilter && (e = t.dataFilter(e, t.dataType)), l = o, o = c.shift())
                if ("*" === o)
                    o = l;
                else if ("*" !== l && l !== o) {
                    if (r = u[l + " " + o] || u["* " + o], !r)
                        for (s in u)
                            if (a = s.split(" "), a[1] === o && (r = u[l + " " + a[0]] || u["* " + a[0]])) {
                                r === !0 ? r = u[s] : u[s] !== !0 && (o = a[0], c.unshift(a[1]));
                                break
                            }
                    if (r !== !0)
                        if (r && t["throws"])
                            e = r(e);
                        else
                            try {
                                e = r(e)
                            } catch (h) {
                                return{state: "parsererror", error: r ? h : "No conversion from " + l + " to " + o}
                            }
                }
        return{state: "success", data: e}
    }
    function $(t, e, n, i) {
        var s;
        if (oe.isArray(e))
            oe.each(e, function(e, s) {
                n || Gn.test(t) ? i(t, s) : $(t + "[" + ("object" == typeof s ? e : "") + "]", s, n, i)
            });
        else if (n || "object" !== oe.type(e))
            i(t, e);
        else
            for (s in e)
                $(t + "[" + s + "]", e[s], n, i)
    }
    function V() {
        try {
            return new t.XMLHttpRequest
        } catch (e) {
        }
    }
    function U() {
        try {
            return new t.ActiveXObject("Microsoft.XMLHTTP")
        } catch (e) {
        }
    }
    function X(t) {
        return oe.isWindow(t) ? t : 9 === t.nodeType ? t.defaultView || t.parentWindow : !1
    }
    var Y = [], G = Y.slice, Q = Y.concat, J = Y.push, K = Y.indexOf, Z = {}, te = Z.toString, ee = Z.hasOwnProperty, ne = "".trim, ie = {}, se = "1.11.0", oe = function(t, e) {
        return new oe.fn.init(t, e)
    }, re = /^[\s\uFEFF\xA0]+|[\s\uFEFF\xA0]+$/g, ae = /^-ms-/, le = /-([\da-z])/gi, ue = function(t, e) {
        return e.toUpperCase()
    };
    oe.fn = oe.prototype = {jquery: se, constructor: oe, selector: "", length: 0, toArray: function() {
            return G.call(this)
        }, get: function(t) {
            return null != t ? 0 > t ? this[t + this.length] : this[t] : G.call(this)
        }, pushStack: function(t) {
            var e = oe.merge(this.constructor(), t);
            return e.prevObject = this, e.context = this.context, e
        }, each: function(t, e) {
            return oe.each(this, t, e)
        }, map: function(t) {
            return this.pushStack(oe.map(this, function(e, n) {
                return t.call(e, n, e)
            }))
        }, slice: function() {
            return this.pushStack(G.apply(this, arguments))
        }, first: function() {
            return this.eq(0)
        }, last: function() {
            return this.eq(-1)
        }, eq: function(t) {
            var e = this.length, n = +t + (0 > t ? e : 0);
            return this.pushStack(n >= 0 && e > n ? [this[n]] : [])
        }, end: function() {
            return this.prevObject || this.constructor(null)
        }, push: J, sort: Y.sort, splice: Y.splice}, oe.extend = oe.fn.extend = function() {
        var t, e, n, i, s, o, r = arguments[0] || {}, a = 1, l = arguments.length, u = !1;
        for ("boolean" == typeof r && (u = r, r = arguments[a] || {}, a++), "object" == typeof r || oe.isFunction(r) || (r = {}), a === l && (r = this, a--); l > a; a++)
            if (null != (s = arguments[a]))
                for (i in s)
                    t = r[i], n = s[i], r !== n && (u && n && (oe.isPlainObject(n) || (e = oe.isArray(n))) ? (e ? (e = !1, o = t && oe.isArray(t) ? t : []) : o = t && oe.isPlainObject(t) ? t : {}, r[i] = oe.extend(u, o, n)) : void 0 !== n && (r[i] = n));
        return r
    }, oe.extend({expando: "jQuery" + (se + Math.random()).replace(/\D/g, ""), isReady: !0, error: function(t) {
            throw new Error(t)
        }, noop: function() {
        }, isFunction: function(t) {
            return"function" === oe.type(t)
        }, isArray: Array.isArray || function(t) {
            return"array" === oe.type(t)
        }, isWindow: function(t) {
            return null != t && t == t.window
        }, isNumeric: function(t) {
            return t - parseFloat(t) >= 0
        }, isEmptyObject: function(t) {
            var e;
            for (e in t)
                return!1;
            return!0
        }, isPlainObject: function(t) {
            var e;
            if (!t || "object" !== oe.type(t) || t.nodeType || oe.isWindow(t))
                return!1;
            try {
                if (t.constructor && !ee.call(t, "constructor") && !ee.call(t.constructor.prototype, "isPrototypeOf"))
                    return!1
            } catch (n) {
                return!1
            }
            if (ie.ownLast)
                for (e in t)
                    return ee.call(t, e);
            for (e in t)
                ;
            return void 0 === e || ee.call(t, e)
        }, type: function(t) {
            return null == t ? t + "" : "object" == typeof t || "function" == typeof t ? Z[te.call(t)] || "object" : typeof t
        }, globalEval: function(e) {
            e && oe.trim(e) && (t.execScript || function(e) {
                t.eval.call(t, e)
            })(e)
        }, camelCase: function(t) {
            return t.replace(ae, "ms-").replace(le, ue)
        }, nodeName: function(t, e) {
            return t.nodeName && t.nodeName.toLowerCase() === e.toLowerCase()
        }, each: function(t, e, i) {
            var s, o = 0, r = t.length, a = n(t);
            if (i) {
                if (a)
                    for (; r > o && (s = e.apply(t[o], i), s !== !1); o++)
                        ;
                else
                    for (o in t)
                        if (s = e.apply(t[o], i), s === !1)
                            break
            } else if (a)
                for (; r > o && (s = e.call(t[o], o, t[o]), s !== !1); o++)
                    ;
            else
                for (o in t)
                    if (s = e.call(t[o], o, t[o]), s === !1)
                        break;
            return t
        }, trim: ne && !ne.call("ï»¿Â ") ? function(t) {
            return null == t ? "" : ne.call(t)
        } : function(t) {
            return null == t ? "" : (t + "").replace(re, "")
        }, makeArray: function(t, e) {
            var i = e || [];
            return null != t && (n(Object(t)) ? oe.merge(i, "string" == typeof t ? [t] : t) : J.call(i, t)), i
        }, inArray: function(t, e, n) {
            var i;
            if (e) {
                if (K)
                    return K.call(e, t, n);
                for (i = e.length, n = n?0 > n?Math.max(0, i + n):n:0; i > n; n++)
                    if (n in e && e[n] === t)
                        return n
            }
            return-1
        }, merge: function(t, e) {
            for (var n = +e.length, i = 0, s = t.length; n > i; )
                t[s++] = e[i++];
            if (n !== n)
                for (; void 0 !== e[i]; )
                    t[s++] = e[i++];
            return t.length = s, t
        }, grep: function(t, e, n) {
            for (var i, s = [], o = 0, r = t.length, a = !n; r > o; o++)
                i = !e(t[o], o), i !== a && s.push(t[o]);
            return s
        }, map: function(t, e, i) {
            var s, o = 0, r = t.length, a = n(t), l = [];
            if (a)
                for (; r > o; o++)
                    s = e(t[o], o, i), null != s && l.push(s);
            else
                for (o in t)
                    s = e(t[o], o, i), null != s && l.push(s);
            return Q.apply([], l)
        }, guid: 1, proxy: function(t, e) {
            var n, i, s;
            return"string" == typeof e && (s = t[e], e = t, t = s), oe.isFunction(t) ? (n = G.call(arguments, 2), i = function() {
                return t.apply(e || this, n.concat(G.call(arguments)))
            }, i.guid = t.guid = t.guid || oe.guid++, i) : void 0
        }, now: function() {
            return+new Date
        }, support: ie}), oe.each("Boolean Number String Function Array Date RegExp Object Error".split(" "), function(t, e) {
        Z["[object " + e + "]"] = e.toLowerCase()
    });
    var ce = function(t) {
        function e(t, e, n, i) {
            var s, o, r, a, l, u, h, f, m, v;
            if ((e ? e.ownerDocument || e : I) !== L && N(e), e = e || L, n = n || [], !t || "string" != typeof t)
                return n;
            if (1 !== (a = e.nodeType) && 9 !== a)
                return[];
            if (F && !i) {
                if (s = ye.exec(t))
                    if (r = s[1]) {
                        if (9 === a) {
                            if (o = e.getElementById(r), !o || !o.parentNode)
                                return n;
                            if (o.id === r)
                                return n.push(o), n
                        } else if (e.ownerDocument && (o = e.ownerDocument.getElementById(r)) && O(e, o) && o.id === r)
                            return n.push(o), n
                    } else {
                        if (s[2])
                            return Z.apply(n, e.getElementsByTagName(t)), n;
                        if ((r = s[3]) && S.getElementsByClassName && e.getElementsByClassName)
                            return Z.apply(n, e.getElementsByClassName(r)), n
                    }
                if (S.qsa && (!M || !M.test(t))) {
                    if (f = h = R, m = e, v = 9 === a && t, 1 === a && "object" !== e.nodeName.toLowerCase()) {
                        for (u = d(t), (h = e.getAttribute("id"))?f = h.replace(we, "\\$&"):e.setAttribute("id", f), f = "[id='" + f + "'] ", l = u.length; l--; )
                            u[l] = f + p(u[l]);
                        m = be.test(t) && c(e.parentNode) || e, v = u.join(",")
                    }
                    if (v)
                        try {
                            return Z.apply(n, m.querySelectorAll(v)), n
                        } catch (g) {
                        } finally {
                            h || e.removeAttribute("id")
                        }
                }
            }
            return x(t.replace(le, "$1"), e, n, i)
        }
        function n() {
            function t(n, i) {
                return e.push(n + " ") > k.cacheLength && delete t[e.shift()], t[n + " "] = i
            }
            var e = [];
            return t
        }
        function i(t) {
            return t[R] = !0, t
        }
        function s(t) {
            var e = L.createElement("div");
            try {
                return!!t(e)
            } catch (n) {
                return!1
            } finally {
                e.parentNode && e.parentNode.removeChild(e), e = null
            }
        }
        function o(t, e) {
            for (var n = t.split("|"), i = t.length; i--; )
                k.attrHandle[n[i]] = e
        }
        function r(t, e) {
            var n = e && t, i = n && 1 === t.nodeType && 1 === e.nodeType && (~e.sourceIndex || Y) - (~t.sourceIndex || Y);
            if (i)
                return i;
            if (n)
                for (; n = n.nextSibling; )
                    if (n === e)
                        return-1;
            return t ? 1 : -1
        }
        function a(t) {
            return function(e) {
                var n = e.nodeName.toLowerCase();
                return"input" === n && e.type === t
            }
        }
        function l(t) {
            return function(e) {
                var n = e.nodeName.toLowerCase();
                return("input" === n || "button" === n) && e.type === t
            }
        }
        function u(t) {
            return i(function(e) {
                return e = +e, i(function(n, i) {
                    for (var s, o = t([], n.length, e), r = o.length; r--; )
                        n[s = o[r]] && (n[s] = !(i[s] = n[s]))
                })
            })
        }
        function c(t) {
            return t && typeof t.getElementsByTagName !== X && t
        }
        function h() {
        }
        function d(t, n) {
            var i, s, o, r, a, l, u, c = $[t + " "];
            if (c)
                return n ? 0 : c.slice(0);
            for (a = t, l = [], u = k.preFilter; a; ) {
                (!i || (s = ue.exec(a))) && (s && (a = a.slice(s[0].length) || a), l.push(o = [])), i = !1, (s = ce.exec(a)) && (i = s.shift(), o.push({value: i, type: s[0].replace(le, " ")}), a = a.slice(i.length));
                for (r in k.filter)
                    !(s = fe[r].exec(a)) || u[r] && !(s = u[r](s)) || (i = s.shift(), o.push({value: i, type: r, matches: s}), a = a.slice(i.length));
                if (!i)
                    break
            }
            return n ? a.length : a ? e.error(t) : $(t, l).slice(0)
        }
        function p(t) {
            for (var e = 0, n = t.length, i = ""; n > e; e++)
                i += t[e].value;
            return i
        }
        function f(t, e, n) {
            var i = e.dir, s = n && "parentNode" === i, o = W++;
            return e.first ? function(e, n, o) {
                for (; e = e[i]; )
                    if (1 === e.nodeType || s)
                        return t(e, n, o)
            } : function(e, n, r) {
                var a, l, u = [B, o];
                if (r) {
                    for (; e = e[i]; )
                        if ((1 === e.nodeType || s) && t(e, n, r))
                            return!0
                } else
                    for (; e = e[i]; )
                        if (1 === e.nodeType || s) {
                            if (l = e[R] || (e[R] = {}), (a = l[i]) && a[0] === B && a[1] === o)
                                return u[2] = a[2];
                            if (l[i] = u, u[2] = t(e, n, r))
                                return!0
                        }
            }
        }
        function m(t) {
            return t.length > 1 ? function(e, n, i) {
                for (var s = t.length; s--; )
                    if (!t[s](e, n, i))
                        return!1;
                return!0
            } : t[0]
        }
        function v(t, e, n, i, s) {
            for (var o, r = [], a = 0, l = t.length, u = null != e; l > a; a++)
                (o = t[a]) && (!n || n(o, i, s)) && (r.push(o), u && e.push(a));
            return r
        }
        function g(t, e, n, s, o, r) {
            return s && !s[R] && (s = g(s)), o && !o[R] && (o = g(o, r)), i(function(i, r, a, l) {
                var u, c, h, d = [], p = [], f = r.length, m = i || w(e || "*", a.nodeType ? [a] : a, []), g = !t || !i && e ? m : v(m, d, t, a, l), y = n ? o || (i ? t : f || s) ? [] : r : g;
                if (n && n(g, y, a, l), s)
                    for (u = v(y, p), s(u, [], a, l), c = u.length; c--; )
                        (h = u[c]) && (y[p[c]] = !(g[p[c]] = h));
                if (i) {
                    if (o || t) {
                        if (o) {
                            for (u = [], c = y.length; c--; )
                                (h = y[c]) && u.push(g[c] = h);
                            o(null, y = [], u, l)
                        }
                        for (c = y.length; c--; )
                            (h = y[c]) && (u = o ? ee.call(i, h) : d[c]) > -1 && (i[u] = !(r[u] = h))
                    }
                } else
                    y = v(y === r ? y.splice(f, y.length) : y), o ? o(null, r, y, l) : Z.apply(r, y)
            })
        }
        function y(t) {
            for (var e, n, i, s = t.length, o = k.relative[t[0].type], r = o || k.relative[" "], a = o ? 1 : 0, l = f(function(t) {
                return t === e
            }, r, !0), u = f(function(t) {
                return ee.call(e, t) > -1
            }, r, !0), c = [function(t, n, i) {
                    return!o && (i || n !== E) || ((e = n).nodeType ? l(t, n, i) : u(t, n, i))
                }]; s > a; a++)
                if (n = k.relative[t[a].type])
                    c = [f(m(c), n)];
                else {
                    if (n = k.filter[t[a].type].apply(null, t[a].matches), n[R]) {
                        for (i = ++a; s > i && !k.relative[t[i].type]; i++)
                            ;
                        return g(a > 1 && m(c), a > 1 && p(t.slice(0, a - 1).concat({value: " " === t[a - 2].type ? "*" : ""})).replace(le, "$1"), n, i > a && y(t.slice(a, i)), s > i && y(t = t.slice(i)), s > i && p(t))
                    }
                    c.push(n)
                }
            return m(c)
        }
        function b(t, n) {
            var s = n.length > 0, o = t.length > 0, r = function(i, r, a, l, u) {
                var c, h, d, p = 0, f = "0", m = i && [], g = [], y = E, b = i || o && k.find.TAG("*", u), w = B += null == y ? 1 : Math.random() || .1, x = b.length;
                for (u && (E = r !== L && r); f !== x && null != (c = b[f]); f++) {
                    if (o && c) {
                        for (h = 0; d = t[h++]; )
                            if (d(c, r, a)) {
                                l.push(c);
                                break
                            }
                        u && (B = w)
                    }
                    s && ((c = !d && c) && p--, i && m.push(c))
                }
                if (p += f, s && f !== p) {
                    for (h = 0; d = n[h++]; )
                        d(m, g, r, a);
                    if (i) {
                        if (p > 0)
                            for (; f--; )
                                m[f] || g[f] || (g[f] = J.call(l));
                        g = v(g)
                    }
                    Z.apply(l, g), u && !i && g.length > 0 && p + n.length > 1 && e.uniqueSort(l)
                }
                return u && (B = w, E = y), m
            };
            return s ? i(r) : r
        }
        function w(t, n, i) {
            for (var s = 0, o = n.length; o > s; s++)
                e(t, n[s], i);
            return i
        }
        function x(t, e, n, i) {
            var s, o, r, a, l, u = d(t);
            if (!i && 1 === u.length) {
                if (o = u[0] = u[0].slice(0), o.length > 2 && "ID" === (r = o[0]).type && S.getById && 9 === e.nodeType && F && k.relative[o[1].type]) {
                    if (e = (k.find.ID(r.matches[0].replace(xe, _e), e) || [])[0], !e)
                        return n;
                    t = t.slice(o.shift().value.length)
                }
                for (s = fe.needsContext.test(t)?0:o.length; s-- && (r = o[s], !k.relative[a = r.type]); )
                    if ((l = k.find[a]) && (i = l(r.matches[0].replace(xe, _e), be.test(o[0].type) && c(e.parentNode) || e))) {
                        if (o.splice(s, 1), t = i.length && p(o), !t)
                            return Z.apply(n, i), n;
                        break
                    }
            }
            return T(t, u)(i, e, !F, n, be.test(t) && c(e.parentNode) || e), n
        }
        var _, S, k, C, j, T, E, P, A, N, L, H, F, M, D, q, O, R = "sizzle" + -new Date, I = t.document, B = 0, W = 0, z = n(), $ = n(), V = n(), U = function(t, e) {
            return t === e && (A = !0), 0
        }, X = "undefined", Y = 1 << 31, G = {}.hasOwnProperty, Q = [], J = Q.pop, K = Q.push, Z = Q.push, te = Q.slice, ee = Q.indexOf || function(t) {
            for (var e = 0, n = this.length; n > e; e++)
                if (this[e] === t)
                    return e;
            return-1
        }, ne = "checked|selected|async|autofocus|autoplay|controls|defer|disabled|hidden|ismap|loop|multiple|open|readonly|required|scoped", ie = "[\\x20\\t\\r\\n\\f]", se = "(?:\\\\.|[\\w-]|[^\\x00-\\xa0])+", oe = se.replace("w", "w#"), re = "\\[" + ie + "*(" + se + ")" + ie + "*(?:([*^$|!~]?=)" + ie + "*(?:(['\"])((?:\\\\.|[^\\\\])*?)\\3|(" + oe + ")|)|)" + ie + "*\\]", ae = ":(" + se + ")(?:\\(((['\"])((?:\\\\.|[^\\\\])*?)\\3|((?:\\\\.|[^\\\\()[\\]]|" + re.replace(3, 8) + ")*)|.*)\\)|)", le = new RegExp("^" + ie + "+|((?:^|[^\\\\])(?:\\\\.)*)" + ie + "+$", "g"), ue = new RegExp("^" + ie + "*," + ie + "*"), ce = new RegExp("^" + ie + "*([>+~]|" + ie + ")" + ie + "*"), he = new RegExp("=" + ie + "*([^\\]'\"]*?)" + ie + "*\\]", "g"), de = new RegExp(ae), pe = new RegExp("^" + oe + "$"), fe = {ID: new RegExp("^#(" + se + ")"), CLASS: new RegExp("^\\.(" + se + ")"), TAG: new RegExp("^(" + se.replace("w", "w*") + ")"), ATTR: new RegExp("^" + re), PSEUDO: new RegExp("^" + ae), CHILD: new RegExp("^:(only|first|last|nth|nth-last)-(child|of-type)(?:\\(" + ie + "*(even|odd|(([+-]|)(\\d*)n|)" + ie + "*(?:([+-]|)" + ie + "*(\\d+)|))" + ie + "*\\)|)", "i"), bool: new RegExp("^(?:" + ne + ")$", "i"), needsContext: new RegExp("^" + ie + "*[>+~]|:(even|odd|eq|gt|lt|nth|first|last)(?:\\(" + ie + "*((?:-\\d)?\\d*)" + ie + "*\\)|)(?=[^-]|$)", "i")}, me = /^(?:input|select|textarea|button)$/i, ve = /^h\d$/i, ge = /^[^{]+\{\s*\[native \w/, ye = /^(?:#([\w-]+)|(\w+)|\.([\w-]+))$/, be = /[+~]/, we = /'|\\/g, xe = new RegExp("\\\\([\\da-f]{1,6}" + ie + "?|(" + ie + ")|.)", "ig"), _e = function(t, e, n) {
            var i = "0x" + e - 65536;
            return i !== i || n ? e : 0 > i ? String.fromCharCode(i + 65536) : String.fromCharCode(i >> 10 | 55296, 1023 & i | 56320)
        };
        try {
            Z.apply(Q = te.call(I.childNodes), I.childNodes), Q[I.childNodes.length].nodeType
        } catch (Se) {
            Z = {apply: Q.length ? function(t, e) {
                    K.apply(t, te.call(e))
                } : function(t, e) {
                    for (var n = t.length, i = 0; t[n++] = e[i++]; )
                        ;
                    t.length = n - 1
                }}
        }
        S = e.support = {}, j = e.isXML = function(t) {
            var e = t && (t.ownerDocument || t).documentElement;
            return e ? "HTML" !== e.nodeName : !1
        }, N = e.setDocument = function(t) {
            var e, n = t ? t.ownerDocument || t : I, i = n.defaultView;
            return n !== L && 9 === n.nodeType && n.documentElement ? (L = n, H = n.documentElement, F = !j(n), i && i !== i.top && (i.addEventListener ? i.addEventListener("unload", function() {
                N()
            }, !1) : i.attachEvent && i.attachEvent("onunload", function() {
                N()
            })), S.attributes = s(function(t) {
                return t.className = "i", !t.getAttribute("className")
            }), S.getElementsByTagName = s(function(t) {
                return t.appendChild(n.createComment("")), !t.getElementsByTagName("*").length
            }), S.getElementsByClassName = ge.test(n.getElementsByClassName) && s(function(t) {
                return t.innerHTML = "<div class='a'></div><div class='a i'></div>", t.firstChild.className = "i", 2 === t.getElementsByClassName("i").length
            }), S.getById = s(function(t) {
                return H.appendChild(t).id = R, !n.getElementsByName || !n.getElementsByName(R).length
            }), S.getById ? (k.find.ID = function(t, e) {
                if (typeof e.getElementById !== X && F) {
                    var n = e.getElementById(t);
                    return n && n.parentNode ? [n] : []
                }
            }, k.filter.ID = function(t) {
                var e = t.replace(xe, _e);
                return function(t) {
                    return t.getAttribute("id") === e
                }
            }) : (delete k.find.ID, k.filter.ID = function(t) {
                var e = t.replace(xe, _e);
                return function(t) {
                    var n = typeof t.getAttributeNode !== X && t.getAttributeNode("id");
                    return n && n.value === e
                }
            }), k.find.TAG = S.getElementsByTagName ? function(t, e) {
                return typeof e.getElementsByTagName !== X ? e.getElementsByTagName(t) : void 0
            } : function(t, e) {
                var n, i = [], s = 0, o = e.getElementsByTagName(t);
                if ("*" === t) {
                    for (; n = o[s++]; )
                        1 === n.nodeType && i.push(n);
                    return i
                }
                return o
            }, k.find.CLASS = S.getElementsByClassName && function(t, e) {
                return typeof e.getElementsByClassName !== X && F ? e.getElementsByClassName(t) : void 0
            }, D = [], M = [], (S.qsa = ge.test(n.querySelectorAll)) && (s(function(t) {
                t.innerHTML = "<select t=''><option selected=''></option></select>", t.querySelectorAll("[t^='']").length && M.push("[*^$]=" + ie + "*(?:''|\"\")"), t.querySelectorAll("[selected]").length || M.push("\\[" + ie + "*(?:value|" + ne + ")"), t.querySelectorAll(":checked").length || M.push(":checked")
            }), s(function(t) {
                var e = n.createElement("input");
                e.setAttribute("type", "hidden"), t.appendChild(e).setAttribute("name", "D"), t.querySelectorAll("[name=d]").length && M.push("name" + ie + "*[*^$|!~]?="), t.querySelectorAll(":enabled").length || M.push(":enabled", ":disabled"), t.querySelectorAll("*,:x"), M.push(",.*:")
            })), (S.matchesSelector = ge.test(q = H.webkitMatchesSelector || H.mozMatchesSelector || H.oMatchesSelector || H.msMatchesSelector)) && s(function(t) {
                S.disconnectedMatch = q.call(t, "div"), q.call(t, "[s!='']:x"), D.push("!=", ae)
            }), M = M.length && new RegExp(M.join("|")), D = D.length && new RegExp(D.join("|")), e = ge.test(H.compareDocumentPosition), O = e || ge.test(H.contains) ? function(t, e) {
                var n = 9 === t.nodeType ? t.documentElement : t, i = e && e.parentNode;
                return t === i || !(!i || 1 !== i.nodeType || !(n.contains ? n.contains(i) : t.compareDocumentPosition && 16 & t.compareDocumentPosition(i)))
            } : function(t, e) {
                if (e)
                    for (; e = e.parentNode; )
                        if (e === t)
                            return!0;
                return!1
            }, U = e ? function(t, e) {
                if (t === e)
                    return A = !0, 0;
                var i = !t.compareDocumentPosition - !e.compareDocumentPosition;
                return i ? i : (i = (t.ownerDocument || t) === (e.ownerDocument || e) ? t.compareDocumentPosition(e) : 1, 1 & i || !S.sortDetached && e.compareDocumentPosition(t) === i ? t === n || t.ownerDocument === I && O(I, t) ? -1 : e === n || e.ownerDocument === I && O(I, e) ? 1 : P ? ee.call(P, t) - ee.call(P, e) : 0 : 4 & i ? -1 : 1)
            } : function(t, e) {
                if (t === e)
                    return A = !0, 0;
                var i, s = 0, o = t.parentNode, a = e.parentNode, l = [t], u = [e];
                if (!o || !a)
                    return t === n ? -1 : e === n ? 1 : o ? -1 : a ? 1 : P ? ee.call(P, t) - ee.call(P, e) : 0;
                if (o === a)
                    return r(t, e);
                for (i = t; i = i.parentNode; )
                    l.unshift(i);
                for (i = e; i = i.parentNode; )
                    u.unshift(i);
                for (; l[s] === u[s]; )
                    s++;
                return s ? r(l[s], u[s]) : l[s] === I ? -1 : u[s] === I ? 1 : 0
            }, n) : L
        }, e.matches = function(t, n) {
            return e(t, null, null, n)
        }, e.matchesSelector = function(t, n) {
            if ((t.ownerDocument || t) !== L && N(t), n = n.replace(he, "='$1']"), !(!S.matchesSelector || !F || D && D.test(n) || M && M.test(n)))
                try {
                    var i = q.call(t, n);
                    if (i || S.disconnectedMatch || t.document && 11 !== t.document.nodeType)
                        return i
                } catch (s) {
                }
            return e(n, L, null, [t]).length > 0
        }, e.contains = function(t, e) {
            return(t.ownerDocument || t) !== L && N(t), O(t, e)
        }, e.attr = function(t, e) {
            (t.ownerDocument || t) !== L && N(t);
            var n = k.attrHandle[e.toLowerCase()], i = n && G.call(k.attrHandle, e.toLowerCase()) ? n(t, e, !F) : void 0;
            return void 0 !== i ? i : S.attributes || !F ? t.getAttribute(e) : (i = t.getAttributeNode(e)) && i.specified ? i.value : null
        }, e.error = function(t) {
            throw new Error("Syntax error, unrecognized expression: " + t)
        }, e.uniqueSort = function(t) {
            var e, n = [], i = 0, s = 0;
            if (A = !S.detectDuplicates, P = !S.sortStable && t.slice(0), t.sort(U), A) {
                for (; e = t[s++]; )
                    e === t[s] && (i = n.push(s));
                for (; i--; )
                    t.splice(n[i], 1)
            }
            return P = null, t
        }, C = e.getText = function(t) {
            var e, n = "", i = 0, s = t.nodeType;
            if (s) {
                if (1 === s || 9 === s || 11 === s) {
                    if ("string" == typeof t.textContent)
                        return t.textContent;
                    for (t = t.firstChild; t; t = t.nextSibling)
                        n += C(t)
                } else if (3 === s || 4 === s)
                    return t.nodeValue
            } else
                for (; e = t[i++]; )
                    n += C(e);
            return n
        }, k = e.selectors = {cacheLength: 50, createPseudo: i, match: fe, attrHandle: {}, find: {}, relative: {">": {dir: "parentNode", first: !0}, " ": {dir: "parentNode"}, "+": {dir: "previousSibling", first: !0}, "~": {dir: "previousSibling"}}, preFilter: {ATTR: function(t) {
                    return t[1] = t[1].replace(xe, _e), t[3] = (t[4] || t[5] || "").replace(xe, _e), "~=" === t[2] && (t[3] = " " + t[3] + " "), t.slice(0, 4)
                }, CHILD: function(t) {
                    return t[1] = t[1].toLowerCase(), "nth" === t[1].slice(0, 3) ? (t[3] || e.error(t[0]), t[4] = +(t[4] ? t[5] + (t[6] || 1) : 2 * ("even" === t[3] || "odd" === t[3])), t[5] = +(t[7] + t[8] || "odd" === t[3])) : t[3] && e.error(t[0]), t
                }, PSEUDO: function(t) {
                    var e, n = !t[5] && t[2];
                    return fe.CHILD.test(t[0]) ? null : (t[3] && void 0 !== t[4] ? t[2] = t[4] : n && de.test(n) && (e = d(n, !0)) && (e = n.indexOf(")", n.length - e) - n.length) && (t[0] = t[0].slice(0, e), t[2] = n.slice(0, e)), t.slice(0, 3))
                }}, filter: {TAG: function(t) {
                    var e = t.replace(xe, _e).toLowerCase();
                    return"*" === t ? function() {
                        return!0
                    } : function(t) {
                        return t.nodeName && t.nodeName.toLowerCase() === e
                    }
                }, CLASS: function(t) {
                    var e = z[t + " "];
                    return e || (e = new RegExp("(^|" + ie + ")" + t + "(" + ie + "|$)")) && z(t, function(t) {
                        return e.test("string" == typeof t.className && t.className || typeof t.getAttribute !== X && t.getAttribute("class") || "")
                    })
                }, ATTR: function(t, n, i) {
                    return function(s) {
                        var o = e.attr(s, t);
                        return null == o ? "!=" === n : n ? (o += "", "=" === n ? o === i : "!=" === n ? o !== i : "^=" === n ? i && 0 === o.indexOf(i) : "*=" === n ? i && o.indexOf(i) > -1 : "$=" === n ? i && o.slice(-i.length) === i : "~=" === n ? (" " + o + " ").indexOf(i) > -1 : "|=" === n ? o === i || o.slice(0, i.length + 1) === i + "-" : !1) : !0
                    }
                }, CHILD: function(t, e, n, i, s) {
                    var o = "nth" !== t.slice(0, 3), r = "last" !== t.slice(-4), a = "of-type" === e;
                    return 1 === i && 0 === s ? function(t) {
                        return!!t.parentNode
                    } : function(e, n, l) {
                        var u, c, h, d, p, f, m = o !== r ? "nextSibling" : "previousSibling", v = e.parentNode, g = a && e.nodeName.toLowerCase(), y = !l && !a;
                        if (v) {
                            if (o) {
                                for (; m; ) {
                                    for (h = e; h = h[m]; )
                                        if (a ? h.nodeName.toLowerCase() === g : 1 === h.nodeType)
                                            return!1;
                                    f = m = "only" === t && !f && "nextSibling"
                                }
                                return!0
                            }
                            if (f = [r ? v.firstChild : v.lastChild], r && y) {
                                for (c = v[R] || (v[R] = {}), u = c[t] || [], p = u[0] === B && u[1], d = u[0] === B && u[2], h = p && v.childNodes[p]; h = ++p && h && h[m] || (d = p = 0) || f.pop(); )
                                    if (1 === h.nodeType && ++d && h === e) {
                                        c[t] = [B, p, d];
                                        break
                                    }
                            } else if (y && (u = (e[R] || (e[R] = {}))[t]) && u[0] === B)
                                d = u[1];
                            else
                                for (; (h = ++p && h && h[m] || (d = p = 0) || f.pop()) && ((a?h.nodeName.toLowerCase() !== g:1 !== h.nodeType) || !++d || (y && ((h[R] || (h[R] = {}))[t] = [B, d]), h !== e)); )
                                    ;
                            return d -= s, d === i || d % i === 0 && d / i >= 0
                        }
                    }
                }, PSEUDO: function(t, n) {
                    var s, o = k.pseudos[t] || k.setFilters[t.toLowerCase()] || e.error("unsupported pseudo: " + t);
                    return o[R] ? o(n) : o.length > 1 ? (s = [t, t, "", n], k.setFilters.hasOwnProperty(t.toLowerCase()) ? i(function(t, e) {
                        for (var i, s = o(t, n), r = s.length; r--; )
                            i = ee.call(t, s[r]), t[i] = !(e[i] = s[r])
                    }) : function(t) {
                        return o(t, 0, s)
                    }) : o
                }}, pseudos: {not: i(function(t) {
                    var e = [], n = [], s = T(t.replace(le, "$1"));
                    return s[R] ? i(function(t, e, n, i) {
                        for (var o, r = s(t, null, i, []), a = t.length; a--; )
                            (o = r[a]) && (t[a] = !(e[a] = o))
                    }) : function(t, i, o) {
                        return e[0] = t, s(e, null, o, n), !n.pop()
                    }
                }), has: i(function(t) {
                    return function(n) {
                        return e(t, n).length > 0
                    }
                }), contains: i(function(t) {
                    return function(e) {
                        return(e.textContent || e.innerText || C(e)).indexOf(t) > -1
                    }
                }), lang: i(function(t) {
                    return pe.test(t || "") || e.error("unsupported lang: " + t), t = t.replace(xe, _e).toLowerCase(), function(e) {
                        var n;
                        do
                            if (n = F ? e.lang : e.getAttribute("xml:lang") || e.getAttribute("lang"))
                                return n = n.toLowerCase(), n === t || 0 === n.indexOf(t + "-");
                        while ((e = e.parentNode) && 1 === e.nodeType);
                        return!1
                    }
                }), target: function(e) {
                    var n = t.location && t.location.hash;
                    return n && n.slice(1) === e.id
                }, root: function(t) {
                    return t === H
                }, focus: function(t) {
                    return t === L.activeElement && (!L.hasFocus || L.hasFocus()) && !!(t.type || t.href || ~t.tabIndex)
                }, enabled: function(t) {
                    return t.disabled === !1
                }, disabled: function(t) {
                    return t.disabled === !0
                }, checked: function(t) {
                    var e = t.nodeName.toLowerCase();
                    return"input" === e && !!t.checked || "option" === e && !!t.selected
                }, selected: function(t) {
                    return t.parentNode && t.parentNode.selectedIndex, t.selected === !0
                }, empty: function(t) {
                    for (t = t.firstChild; t; t = t.nextSibling)
                        if (t.nodeType < 6)
                            return!1;
                    return!0
                }, parent: function(t) {
                    return!k.pseudos.empty(t)
                }, header: function(t) {
                    return ve.test(t.nodeName)
                }, input: function(t) {
                    return me.test(t.nodeName)
                }, button: function(t) {
                    var e = t.nodeName.toLowerCase();
                    return"input" === e && "button" === t.type || "button" === e
                }, text: function(t) {
                    var e;
                    return"input" === t.nodeName.toLowerCase() && "text" === t.type && (null == (e = t.getAttribute("type")) || "text" === e.toLowerCase())
                }, first: u(function() {
                    return[0]
                }), last: u(function(t, e) {
                    return[e - 1]
                }), eq: u(function(t, e, n) {
                    return[0 > n ? n + e : n]
                }), even: u(function(t, e) {
                    for (var n = 0; e > n; n += 2)
                        t.push(n);
                    return t
                }), odd: u(function(t, e) {
                    for (var n = 1; e > n; n += 2)
                        t.push(n);
                    return t
                }), lt: u(function(t, e, n) {
                    for (var i = 0 > n ? n + e : n; --i >= 0; )
                        t.push(i);
                    return t
                }), gt: u(function(t, e, n) {
                    for (var i = 0 > n ? n + e : n; ++i < e; )
                        t.push(i);
                    return t
                })}}, k.pseudos.nth = k.pseudos.eq;
        for (_ in{radio:!0, checkbox:!0, file:!0, password:!0, image:!0})
            k.pseudos[_] = a(_);
        for (_ in{submit:!0, reset:!0})
            k.pseudos[_] = l(_);
        return h.prototype = k.filters = k.pseudos, k.setFilters = new h, T = e.compile = function(t, e) {
            var n, i = [], s = [], o = V[t + " "];
            if (!o) {
                for (e || (e = d(t)), n = e.length; n--; )
                    o = y(e[n]), o[R] ? i.push(o) : s.push(o);
                o = V(t, b(s, i))
            }
            return o
        }, S.sortStable = R.split("").sort(U).join("") === R, S.detectDuplicates = !!A, N(), S.sortDetached = s(function(t) {
            return 1 & t.compareDocumentPosition(L.createElement("div"))
        }), s(function(t) {
            return t.innerHTML = "<a href='#'></a>", "#" === t.firstChild.getAttribute("href")
        }) || o("type|href|height|width", function(t, e, n) {
            return n ? void 0 : t.getAttribute(e, "type" === e.toLowerCase() ? 1 : 2)
        }), S.attributes && s(function(t) {
            return t.innerHTML = "<input/>", t.firstChild.setAttribute("value", ""), "" === t.firstChild.getAttribute("value")
        }) || o("value", function(t, e, n) {
            return n || "input" !== t.nodeName.toLowerCase() ? void 0 : t.defaultValue
        }), s(function(t) {
            return null == t.getAttribute("disabled")
        }) || o(ne, function(t, e, n) {
            var i;
            return n ? void 0 : t[e] === !0 ? e.toLowerCase() : (i = t.getAttributeNode(e)) && i.specified ? i.value : null
        }), e
    }(t);
    oe.find = ce, oe.expr = ce.selectors, oe.expr[":"] = oe.expr.pseudos, oe.unique = ce.uniqueSort, oe.text = ce.getText, oe.isXMLDoc = ce.isXML, oe.contains = ce.contains;
    var he = oe.expr.match.needsContext, de = /^<(\w+)\s*\/?>(?:<\/\1>|)$/, pe = /^.[^:#\[\.,]*$/;
    oe.filter = function(t, e, n) {
        var i = e[0];
        return n && (t = ":not(" + t + ")"), 1 === e.length && 1 === i.nodeType ? oe.find.matchesSelector(i, t) ? [i] : [] : oe.find.matches(t, oe.grep(e, function(t) {
            return 1 === t.nodeType
        }))
    }, oe.fn.extend({find: function(t) {
            var e, n = [], i = this, s = i.length;
            if ("string" != typeof t)
                return this.pushStack(oe(t).filter(function() {
                    for (e = 0; s > e; e++)
                        if (oe.contains(i[e], this))
                            return!0
                }));
            for (e = 0; s > e; e++)
                oe.find(t, i[e], n);
            return n = this.pushStack(s > 1 ? oe.unique(n) : n), n.selector = this.selector ? this.selector + " " + t : t, n
        }, filter: function(t) {
            return this.pushStack(i(this, t || [], !1))
        }, not: function(t) {
            return this.pushStack(i(this, t || [], !0))
        }, is: function(t) {
            return!!i(this, "string" == typeof t && he.test(t) ? oe(t) : t || [], !1).length
        }});
    var fe, me = t.document, ve = /^(?:\s*(<[\w\W]+>)[^>]*|#([\w-]*))$/, ge = oe.fn.init = function(t, e) {
        var n, i;
        if (!t)
            return this;
        if ("string" == typeof t) {
            if (n = "<" === t.charAt(0) && ">" === t.charAt(t.length - 1) && t.length >= 3 ? [null, t, null] : ve.exec(t), !n || !n[1] && e)
                return!e || e.jquery ? (e || fe).find(t) : this.constructor(e).find(t);
            if (n[1]) {
                if (e = e instanceof oe ? e[0] : e, oe.merge(this, oe.parseHTML(n[1], e && e.nodeType ? e.ownerDocument || e : me, !0)), de.test(n[1]) && oe.isPlainObject(e))
                    for (n in e)
                        oe.isFunction(this[n]) ? this[n](e[n]) : this.attr(n, e[n]);
                return this
            }
            if (i = me.getElementById(n[2]), i && i.parentNode) {
                if (i.id !== n[2])
                    return fe.find(t);
                this.length = 1, this[0] = i
            }
            return this.context = me, this.selector = t, this
        }
        return t.nodeType ? (this.context = this[0] = t, this.length = 1, this) : oe.isFunction(t) ? "undefined" != typeof fe.ready ? fe.ready(t) : t(oe) : (void 0 !== t.selector && (this.selector = t.selector, this.context = t.context), oe.makeArray(t, this))
    };
    ge.prototype = oe.fn, fe = oe(me);
    var ye = /^(?:parents|prev(?:Until|All))/, be = {children: !0, contents: !0, next: !0, prev: !0};
    oe.extend({dir: function(t, e, n) {
            for (var i = [], s = t[e]; s && 9 !== s.nodeType && (void 0 === n || 1 !== s.nodeType || !oe(s).is(n)); )
                1 === s.nodeType && i.push(s), s = s[e];
            return i
        }, sibling: function(t, e) {
            for (var n = []; t; t = t.nextSibling)
                1 === t.nodeType && t !== e && n.push(t);
            return n
        }}), oe.fn.extend({has: function(t) {
            var e, n = oe(t, this), i = n.length;
            return this.filter(function() {
                for (e = 0; i > e; e++)
                    if (oe.contains(this, n[e]))
                        return!0
            })
        }, closest: function(t, e) {
            for (var n, i = 0, s = this.length, o = [], r = he.test(t) || "string" != typeof t ? oe(t, e || this.context) : 0; s > i; i++)
                for (n = this[i]; n && n !== e; n = n.parentNode)
                    if (n.nodeType < 11 && (r ? r.index(n) > -1 : 1 === n.nodeType && oe.find.matchesSelector(n, t))) {
                        o.push(n);
                        break
                    }
            return this.pushStack(o.length > 1 ? oe.unique(o) : o)
        }, index: function(t) {
            return t ? "string" == typeof t ? oe.inArray(this[0], oe(t)) : oe.inArray(t.jquery ? t[0] : t, this) : this[0] && this[0].parentNode ? this.first().prevAll().length : -1
        }, add: function(t, e) {
            return this.pushStack(oe.unique(oe.merge(this.get(), oe(t, e))))
        }, addBack: function(t) {
            return this.add(null == t ? this.prevObject : this.prevObject.filter(t))
        }}), oe.each({parent: function(t) {
            var e = t.parentNode;
            return e && 11 !== e.nodeType ? e : null
        }, parents: function(t) {
            return oe.dir(t, "parentNode")
        }, parentsUntil: function(t, e, n) {
            return oe.dir(t, "parentNode", n)
        }, next: function(t) {
            return s(t, "nextSibling")
        }, prev: function(t) {
            return s(t, "previousSibling")
        }, nextAll: function(t) {
            return oe.dir(t, "nextSibling")
        }, prevAll: function(t) {
            return oe.dir(t, "previousSibling")
        }, nextUntil: function(t, e, n) {
            return oe.dir(t, "nextSibling", n)
        }, prevUntil: function(t, e, n) {
            return oe.dir(t, "previousSibling", n)
        }, siblings: function(t) {
            return oe.sibling((t.parentNode || {}).firstChild, t)
        }, children: function(t) {
            return oe.sibling(t.firstChild)
        }, contents: function(t) {
            return oe.nodeName(t, "iframe") ? t.contentDocument || t.contentWindow.document : oe.merge([], t.childNodes)
        }}, function(t, e) {
        oe.fn[t] = function(n, i) {
            var s = oe.map(this, e, n);
            return"Until" !== t.slice(-5) && (i = n), i && "string" == typeof i && (s = oe.filter(i, s)), this.length > 1 && (be[t] || (s = oe.unique(s)), ye.test(t) && (s = s.reverse())), this.pushStack(s)
        }
    });
    var we = /\S+/g, xe = {};
    oe.Callbacks = function(t) {
        t = "string" == typeof t ? xe[t] || o(t) : oe.extend({}, t);
        var e, n, i, s, r, a, l = [], u = !t.once && [], c = function(o) {
            for (n = t.memory && o, i = !0, r = a || 0, a = 0, s = l.length, e = !0; l && s > r; r++)
                if (l[r].apply(o[0], o[1]) === !1 && t.stopOnFalse) {
                    n = !1;
                    break
                }
            e = !1, l && (u ? u.length && c(u.shift()) : n ? l = [] : h.disable())
        }, h = {add: function() {
                if (l) {
                    var i = l.length;
                    !function o(e) {
                        oe.each(e, function(e, n) {
                            var i = oe.type(n);
                            "function" === i ? t.unique && h.has(n) || l.push(n) : n && n.length && "string" !== i && o(n)
                        })
                    }(arguments), e ? s = l.length : n && (a = i, c(n))
                }
                return this
            }, remove: function() {
                return l && oe.each(arguments, function(t, n) {
                    for (var i; (i = oe.inArray(n, l, i)) > - 1; )
                        l.splice(i, 1), e && (s >= i && s--, r >= i && r--)
                }), this
            }, has: function(t) {
                return t ? oe.inArray(t, l) > -1 : !(!l || !l.length)
            }, empty: function() {
                return l = [], s = 0, this
            }, disable: function() {
                return l = u = n = void 0, this
            }, disabled: function() {
                return!l
            }, lock: function() {
                return u = void 0, n || h.disable(), this
            }, locked: function() {
                return!u
            }, fireWith: function(t, n) {
                return!l || i && !u || (n = n || [], n = [t, n.slice ? n.slice() : n], e ? u.push(n) : c(n)), this
            }, fire: function() {
                return h.fireWith(this, arguments), this
            }, fired: function() {
                return!!i
            }};
        return h
    }, oe.extend({Deferred: function(t) {
            var e = [["resolve", "done", oe.Callbacks("once memory"), "resolved"], ["reject", "fail", oe.Callbacks("once memory"), "rejected"], ["notify", "progress", oe.Callbacks("memory")]], n = "pending", i = {state: function() {
                    return n
                }, always: function() {
                    return s.done(arguments).fail(arguments), this
                }, then: function() {
                    var t = arguments;
                    return oe.Deferred(function(n) {
                        oe.each(e, function(e, o) {
                            var r = oe.isFunction(t[e]) && t[e];
                            s[o[1]](function() {
                                var t = r && r.apply(this, arguments);
                                t && oe.isFunction(t.promise) ? t.promise().done(n.resolve).fail(n.reject).progress(n.notify) : n[o[0] + "With"](this === i ? n.promise() : this, r ? [t] : arguments)
                            })
                        }), t = null
                    }).promise()
                }, promise: function(t) {
                    return null != t ? oe.extend(t, i) : i
                }}, s = {};
            return i.pipe = i.then, oe.each(e, function(t, o) {
                var r = o[2], a = o[3];
                i[o[1]] = r.add, a && r.add(function() {
                    n = a
                }, e[1 ^ t][2].disable, e[2][2].lock), s[o[0]] = function() {
                    return s[o[0] + "With"](this === s ? i : this, arguments), this
                }, s[o[0] + "With"] = r.fireWith
            }), i.promise(s), t && t.call(s, s), s
        }, when: function(t) {
            var e, n, i, s = 0, o = G.call(arguments), r = o.length, a = 1 !== r || t && oe.isFunction(t.promise) ? r : 0, l = 1 === a ? t : oe.Deferred(), u = function(t, n, i) {
                return function(s) {
                    n[t] = this, i[t] = arguments.length > 1 ? G.call(arguments) : s, i === e ? l.notifyWith(n, i) : --a || l.resolveWith(n, i)
                }
            };
            if (r > 1)
                for (e = new Array(r), n = new Array(r), i = new Array(r); r > s; s++)
                    o[s] && oe.isFunction(o[s].promise) ? o[s].promise().done(u(s, i, o)).fail(l.reject).progress(u(s, n, e)) : --a;
            return a || l.resolveWith(i, o), l.promise()
        }});
    var _e;
    oe.fn.ready = function(t) {
        return oe.ready.promise().done(t), this
    }, oe.extend({isReady: !1, readyWait: 1, holdReady: function(t) {
            t ? oe.readyWait++ : oe.ready(!0)
        }, ready: function(t) {
            if (t === !0 ? !--oe.readyWait : !oe.isReady) {
                if (!me.body)
                    return setTimeout(oe.ready);
                oe.isReady = !0, t !== !0 && --oe.readyWait > 0 || (_e.resolveWith(me, [oe]), oe.fn.trigger && oe(me).trigger("ready").off("ready"))
            }
        }}), oe.ready.promise = function(e) {
        if (!_e)
            if (_e = oe.Deferred(), "complete" === me.readyState)
                setTimeout(oe.ready);
            else if (me.addEventListener)
                me.addEventListener("DOMContentLoaded", a, !1), t.addEventListener("load", a, !1);
            else {
                me.attachEvent("onreadystatechange", a), t.attachEvent("onload", a);
                var n = !1;
                try {
                    n = null == t.frameElement && me.documentElement
                } catch (i) {
                }
                n && n.doScroll && !function s() {
                    if (!oe.isReady) {
                        try {
                            n.doScroll("left")
                        } catch (t) {
                            return setTimeout(s, 50)
                        }
                        r(), oe.ready()
                    }
                }()
            }
        return _e.promise(e)
    };
    var Se, ke = "undefined";
    for (Se in oe(ie))
        break;
    ie.ownLast = "0" !== Se, ie.inlineBlockNeedsLayout = !1, oe(function() {
        var t, e, n = me.getElementsByTagName("body")[0];
        n && (t = me.createElement("div"), t.style.cssText = "border:0;width:0;height:0;position:absolute;top:0;left:-9999px;margin-top:1px", e = me.createElement("div"), n.appendChild(t).appendChild(e), typeof e.style.zoom !== ke && (e.style.cssText = "border:0;margin:0;width:1px;padding:1px;display:inline;zoom:1", (ie.inlineBlockNeedsLayout = 3 === e.offsetWidth) && (n.style.zoom = 1)), n.removeChild(t), t = e = null)
    }), function() {
        var t = me.createElement("div");
        if (null == ie.deleteExpando) {
            ie.deleteExpando = !0;
            try {
                delete t.test
            } catch (e) {
                ie.deleteExpando = !1
            }
        }
        t = null
    }(), oe.acceptData = function(t) {
        var e = oe.noData[(t.nodeName + " ").toLowerCase()], n = +t.nodeType || 1;
        return 1 !== n && 9 !== n ? !1 : !e || e !== !0 && t.getAttribute("classid") === e
    };
    var Ce = /^(?:\{[\w\W]*\}|\[[\w\W]*\])$/, je = /([A-Z])/g;
    oe.extend({cache: {}, noData: {"applet ": !0, "embed ": !0, "object ": "clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"}, hasData: function(t) {
            return t = t.nodeType ? oe.cache[t[oe.expando]] : t[oe.expando], !!t && !u(t)
        }, data: function(t, e, n) {
            return c(t, e, n)
        }, removeData: function(t, e) {
            return h(t, e)
        }, _data: function(t, e, n) {
            return c(t, e, n, !0)
        }, _removeData: function(t, e) {
            return h(t, e, !0)
        }}), oe.fn.extend({data: function(t, e) {
            var n, i, s, o = this[0], r = o && o.attributes;
            if (void 0 === t) {
                if (this.length && (s = oe.data(o), 1 === o.nodeType && !oe._data(o, "parsedAttrs"))) {
                    for (n = r.length; n--; )
                        i = r[n].name, 0 === i.indexOf("data-") && (i = oe.camelCase(i.slice(5)), l(o, i, s[i]));
                    oe._data(o, "parsedAttrs", !0)
                }
                return s
            }
            return"object" == typeof t ? this.each(function() {
                oe.data(this, t)
            }) : arguments.length > 1 ? this.each(function() {
                oe.data(this, t, e)
            }) : o ? l(o, t, oe.data(o, t)) : void 0
        }, removeData: function(t) {
            return this.each(function() {
                oe.removeData(this, t)
            })
        }}), oe.extend({queue: function(t, e, n) {
            var i;
            return t ? (e = (e || "fx") + "queue", i = oe._data(t, e), n && (!i || oe.isArray(n) ? i = oe._data(t, e, oe.makeArray(n)) : i.push(n)), i || []) : void 0
        }, dequeue: function(t, e) {
            e = e || "fx";
            var n = oe.queue(t, e), i = n.length, s = n.shift(), o = oe._queueHooks(t, e), r = function() {
                oe.dequeue(t, e)
            };
            "inprogress" === s && (s = n.shift(), i--), s && ("fx" === e && n.unshift("inprogress"), delete o.stop, s.call(t, r, o)), !i && o && o.empty.fire()
        }, _queueHooks: function(t, e) {
            var n = e + "queueHooks";
            return oe._data(t, n) || oe._data(t, n, {empty: oe.Callbacks("once memory").add(function() {
                    oe._removeData(t, e + "queue"), oe._removeData(t, n)
                })})
        }}), oe.fn.extend({queue: function(t, e) {
            var n = 2;
            return"string" != typeof t && (e = t, t = "fx", n--), arguments.length < n ? oe.queue(this[0], t) : void 0 === e ? this : this.each(function() {
                var n = oe.queue(this, t, e);
                oe._queueHooks(this, t), "fx" === t && "inprogress" !== n[0] && oe.dequeue(this, t)
            })
        }, dequeue: function(t) {
            return this.each(function() {
                oe.dequeue(this, t)
            })
        }, clearQueue: function(t) {
            return this.queue(t || "fx", [])
        }, promise: function(t, e) {
            var n, i = 1, s = oe.Deferred(), o = this, r = this.length, a = function() {
                --i || s.resolveWith(o, [o])
            };
            for ("string" != typeof t && (e = t, t = void 0), t = t || "fx"; r--; )
                n = oe._data(o[r], t + "queueHooks"), n && n.empty && (i++, n.empty.add(a));
            return a(), s.promise(e)
        }});
    var Te = /[+-]?(?:\d*\.|)\d+(?:[eE][+-]?\d+|)/.source, Ee = ["Top", "Right", "Bottom", "Left"], Pe = function(t, e) {
        return t = e || t, "none" === oe.css(t, "display") || !oe.contains(t.ownerDocument, t)
    }, Ae = oe.access = function(t, e, n, i, s, o, r) {
        var a = 0, l = t.length, u = null == n;
        if ("object" === oe.type(n)) {
            s = !0;
            for (a in n)
                oe.access(t, e, a, n[a], !0, o, r)
        } else if (void 0 !== i && (s = !0, oe.isFunction(i) || (r = !0), u && (r ? (e.call(t, i), e = null) : (u = e, e = function(t, e, n) {
            return u.call(oe(t), n)
        })), e))
            for (; l > a; a++)
                e(t[a], n, r ? i : i.call(t[a], a, e(t[a], n)));
        return s ? t : u ? e.call(t) : l ? e(t[0], n) : o
    }, Ne = /^(?:checkbox|radio)$/i;
    !function() {
        var t = me.createDocumentFragment(), e = me.createElement("div"), n = me.createElement("input");
        if (e.setAttribute("className", "t"), e.innerHTML = "  <link/><table></table><a href='/a'>a</a>", ie.leadingWhitespace = 3 === e.firstChild.nodeType, ie.tbody = !e.getElementsByTagName("tbody").length, ie.htmlSerialize = !!e.getElementsByTagName("link").length, ie.html5Clone = "<:nav></:nav>" !== me.createElement("nav").cloneNode(!0).outerHTML, n.type = "checkbox", n.checked = !0, t.appendChild(n), ie.appendChecked = n.checked, e.innerHTML = "<textarea>x</textarea>", ie.noCloneChecked = !!e.cloneNode(!0).lastChild.defaultValue, t.appendChild(e), e.innerHTML = "<input type='radio' checked='checked' name='t'/>", ie.checkClone = e.cloneNode(!0).cloneNode(!0).lastChild.checked, ie.noCloneEvent = !0, e.attachEvent && (e.attachEvent("onclick", function() {
            ie.noCloneEvent = !1
        }), e.cloneNode(!0).click()), null == ie.deleteExpando) {
            ie.deleteExpando = !0;
            try {
                delete e.test
            } catch (i) {
                ie.deleteExpando = !1
            }
        }
        t = e = n = null
    }(), function() {
        var e, n, i = me.createElement("div");
        for (e in{submit:!0, change:!0, focusin:!0})
            n = "on" + e, (ie[e + "Bubbles"] = n in t) || (i.setAttribute(n, "t"), ie[e + "Bubbles"] = i.attributes[n].expando === !1);
        i = null
    }();
    var Le = /^(?:input|select|textarea)$/i, He = /^key/, Fe = /^(?:mouse|contextmenu)|click/, Me = /^(?:focusinfocus|focusoutblur)$/, De = /^([^.]*)(?:\.(.+)|)$/;
    oe.event = {global: {}, add: function(t, e, n, i, s) {
            var o, r, a, l, u, c, h, d, p, f, m, v = oe._data(t);
            if (v) {
                for (n.handler && (l = n, n = l.handler, s = l.selector), n.guid || (n.guid = oe.guid++), (r = v.events) || (r = v.events = {}), (c = v.handle) || (c = v.handle = function(t) {
                    return typeof oe === ke || t && oe.event.triggered === t.type ? void 0 : oe.event.dispatch.apply(c.elem, arguments)
                }, c.elem = t), e = (e || "").match(we) || [""], a = e.length; a--; )
                    o = De.exec(e[a]) || [], p = m = o[1], f = (o[2] || "").split(".").sort(), p && (u = oe.event.special[p] || {}, p = (s ? u.delegateType : u.bindType) || p, u = oe.event.special[p] || {}, h = oe.extend({type: p, origType: m, data: i, handler: n, guid: n.guid, selector: s, needsContext: s && oe.expr.match.needsContext.test(s), namespace: f.join(".")}, l), (d = r[p]) || (d = r[p] = [], d.delegateCount = 0, u.setup && u.setup.call(t, i, f, c) !== !1 || (t.addEventListener ? t.addEventListener(p, c, !1) : t.attachEvent && t.attachEvent("on" + p, c))), u.add && (u.add.call(t, h), h.handler.guid || (h.handler.guid = n.guid)), s ? d.splice(d.delegateCount++, 0, h) : d.push(h), oe.event.global[p] = !0);
                t = null
            }
        }, remove: function(t, e, n, i, s) {
            var o, r, a, l, u, c, h, d, p, f, m, v = oe.hasData(t) && oe._data(t);
            if (v && (c = v.events)) {
                for (e = (e || "").match(we) || [""], u = e.length; u--; )
                    if (a = De.exec(e[u]) || [], p = m = a[1], f = (a[2] || "").split(".").sort(), p) {
                        for (h = oe.event.special[p] || {}, p = (i?h.delegateType:h.bindType) || p, d = c[p] || [], a = a[2] && new RegExp("(^|\\.)" + f.join("\\.(?:.*\\.|)") + "(\\.|$)"), l = o = d.length; o--; )
                            r = d[o], !s && m !== r.origType || n && n.guid !== r.guid || a && !a.test(r.namespace) || i && i !== r.selector && ("**" !== i || !r.selector) || (d.splice(o, 1), r.selector && d.delegateCount--, h.remove && h.remove.call(t, r));
                        l && !d.length && (h.teardown && h.teardown.call(t, f, v.handle) !== !1 || oe.removeEvent(t, p, v.handle), delete c[p])
                    } else
                        for (p in c)
                            oe.event.remove(t, p + e[u], n, i, !0);
                oe.isEmptyObject(c) && (delete v.handle, oe._removeData(t, "events"))
            }
        }, trigger: function(e, n, i, s) {
            var o, r, a, l, u, c, h, d = [i || me], p = ee.call(e, "type") ? e.type : e, f = ee.call(e, "namespace") ? e.namespace.split(".") : [];
            if (a = c = i = i || me, 3 !== i.nodeType && 8 !== i.nodeType && !Me.test(p + oe.event.triggered) && (p.indexOf(".") >= 0 && (f = p.split("."), p = f.shift(), f.sort()), r = p.indexOf(":") < 0 && "on" + p, e = e[oe.expando] ? e : new oe.Event(p, "object" == typeof e && e), e.isTrigger = s ? 2 : 3, e.namespace = f.join("."), e.namespace_re = e.namespace ? new RegExp("(^|\\.)" + f.join("\\.(?:.*\\.|)") + "(\\.|$)") : null, e.result = void 0, e.target || (e.target = i), n = null == n ? [e] : oe.makeArray(n, [e]), u = oe.event.special[p] || {}, s || !u.trigger || u.trigger.apply(i, n) !== !1)) {
                if (!s && !u.noBubble && !oe.isWindow(i)) {
                    for (l = u.delegateType || p, Me.test(l + p) || (a = a.parentNode); a; a = a.parentNode)
                        d.push(a), c = a;
                    c === (i.ownerDocument || me) && d.push(c.defaultView || c.parentWindow || t)
                }
                for (h = 0; (a = d[h++]) && !e.isPropagationStopped(); )
                    e.type = h > 1 ? l : u.bindType || p, o = (oe._data(a, "events") || {})[e.type] && oe._data(a, "handle"), o && o.apply(a, n), o = r && a[r], o && o.apply && oe.acceptData(a) && (e.result = o.apply(a, n), e.result === !1 && e.preventDefault());
                if (e.type = p, !s && !e.isDefaultPrevented() && (!u._default || u._default.apply(d.pop(), n) === !1) && oe.acceptData(i) && r && i[p] && !oe.isWindow(i)) {
                    c = i[r], c && (i[r] = null), oe.event.triggered = p;
                    try {
                        i[p]()
                    } catch (m) {
                    }
                    oe.event.triggered = void 0, c && (i[r] = c)
                }
                return e.result
            }
        }, dispatch: function(t) {
            t = oe.event.fix(t);
            var e, n, i, s, o, r = [], a = G.call(arguments), l = (oe._data(this, "events") || {})[t.type] || [], u = oe.event.special[t.type] || {};
            if (a[0] = t, t.delegateTarget = this, !u.preDispatch || u.preDispatch.call(this, t) !== !1) {
                for (r = oe.event.handlers.call(this, t, l), e = 0; (s = r[e++]) && !t.isPropagationStopped(); )
                    for (t.currentTarget = s.elem, o = 0; (i = s.handlers[o++]) && !t.isImmediatePropagationStopped(); )
                        (!t.namespace_re || t.namespace_re.test(i.namespace)) && (t.handleObj = i, t.data = i.data, n = ((oe.event.special[i.origType] || {}).handle || i.handler).apply(s.elem, a), void 0 !== n && (t.result = n) === !1 && (t.preventDefault(), t.stopPropagation()));
                return u.postDispatch && u.postDispatch.call(this, t), t.result
            }
        }, handlers: function(t, e) {
            var n, i, s, o, r = [], a = e.delegateCount, l = t.target;
            if (a && l.nodeType && (!t.button || "click" !== t.type))
                for (; l != this; l = l.parentNode || this)
                    if (1 === l.nodeType && (l.disabled !== !0 || "click" !== t.type)) {
                        for (s = [], o = 0; a > o; o++)
                            i = e[o], n = i.selector + " ", void 0 === s[n] && (s[n] = i.needsContext ? oe(n, this).index(l) >= 0 : oe.find(n, this, null, [l]).length), s[n] && s.push(i);
                        s.length && r.push({elem: l, handlers: s})
                    }
            return a < e.length && r.push({elem: this, handlers: e.slice(a)}), r
        }, fix: function(t) {
            if (t[oe.expando])
                return t;
            var e, n, i, s = t.type, o = t, r = this.fixHooks[s];
            for (r || (this.fixHooks[s] = r = Fe.test(s)?this.mouseHooks:He.test(s)?this.keyHooks:{}), i = r.props?this.props.concat(r.props):this.props, t = new oe.Event(o), e = i.length; e--; )
                n = i[e], t[n] = o[n];
            return t.target || (t.target = o.srcElement || me), 3 === t.target.nodeType && (t.target = t.target.parentNode), t.metaKey = !!t.metaKey, r.filter ? r.filter(t, o) : t
        }, props: "altKey bubbles cancelable ctrlKey currentTarget eventPhase metaKey relatedTarget shiftKey target timeStamp view which".split(" "), fixHooks: {}, keyHooks: {props: "char charCode key keyCode".split(" "), filter: function(t, e) {
                return null == t.which && (t.which = null != e.charCode ? e.charCode : e.keyCode), t
            }}, mouseHooks: {props: "button buttons clientX clientY fromElement offsetX offsetY pageX pageY screenX screenY toElement".split(" "), filter: function(t, e) {
                var n, i, s, o = e.button, r = e.fromElement;
                return null == t.pageX && null != e.clientX && (i = t.target.ownerDocument || me, s = i.documentElement, n = i.body, t.pageX = e.clientX + (s && s.scrollLeft || n && n.scrollLeft || 0) - (s && s.clientLeft || n && n.clientLeft || 0), t.pageY = e.clientY + (s && s.scrollTop || n && n.scrollTop || 0) - (s && s.clientTop || n && n.clientTop || 0)), !t.relatedTarget && r && (t.relatedTarget = r === t.target ? e.toElement : r), t.which || void 0 === o || (t.which = 1 & o ? 1 : 2 & o ? 3 : 4 & o ? 2 : 0), t
            }}, special: {load: {noBubble: !0}, focus: {trigger: function() {
                    if (this !== f() && this.focus)
                        try {
                            return this.focus(), !1
                        } catch (t) {
                        }
                }, delegateType: "focusin"}, blur: {trigger: function() {
                    return this === f() && this.blur ? (this.blur(), !1) : void 0
                }, delegateType: "focusout"}, click: {trigger: function() {
                    return oe.nodeName(this, "input") && "checkbox" === this.type && this.click ? (this.click(), !1) : void 0
                }, _default: function(t) {
                    return oe.nodeName(t.target, "a")
                }}, beforeunload: {postDispatch: function(t) {
                    void 0 !== t.result && (t.originalEvent.returnValue = t.result)
                }}}, simulate: function(t, e, n, i) {
            var s = oe.extend(new oe.Event, n, {type: t, isSimulated: !0, originalEvent: {}});
            i ? oe.event.trigger(s, null, e) : oe.event.dispatch.call(e, s), s.isDefaultPrevented() && n.preventDefault()
        }}, oe.removeEvent = me.removeEventListener ? function(t, e, n) {
        t.removeEventListener && t.removeEventListener(e, n, !1)
    } : function(t, e, n) {
        var i = "on" + e;
        t.detachEvent && (typeof t[i] === ke && (t[i] = null), t.detachEvent(i, n))
    }, oe.Event = function(t, e) {
        return this instanceof oe.Event ? (t && t.type ? (this.originalEvent = t, this.type = t.type, this.isDefaultPrevented = t.defaultPrevented || void 0 === t.defaultPrevented && (t.returnValue === !1 || t.getPreventDefault && t.getPreventDefault()) ? d : p) : this.type = t, e && oe.extend(this, e), this.timeStamp = t && t.timeStamp || oe.now(), void(this[oe.expando] = !0)) : new oe.Event(t, e)
    }, oe.Event.prototype = {isDefaultPrevented: p, isPropagationStopped: p, isImmediatePropagationStopped: p, preventDefault: function() {
            var t = this.originalEvent;
            this.isDefaultPrevented = d, t && (t.preventDefault ? t.preventDefault() : t.returnValue = !1)
        }, stopPropagation: function() {
            var t = this.originalEvent;
            this.isPropagationStopped = d, t && (t.stopPropagation && t.stopPropagation(), t.cancelBubble = !0)
        }, stopImmediatePropagation: function() {
            this.isImmediatePropagationStopped = d, this.stopPropagation()
        }}, oe.each({mouseenter: "mouseover", mouseleave: "mouseout"}, function(t, e) {
        oe.event.special[t] = {delegateType: e, bindType: e, handle: function(t) {
                var n, i = this, s = t.relatedTarget, o = t.handleObj;
                return(!s || s !== i && !oe.contains(i, s)) && (t.type = o.origType, n = o.handler.apply(this, arguments), t.type = e), n
            }}
    }), ie.submitBubbles || (oe.event.special.submit = {setup: function() {
            return oe.nodeName(this, "form") ? !1 : void oe.event.add(this, "click._submit keypress._submit", function(t) {
                var e = t.target, n = oe.nodeName(e, "input") || oe.nodeName(e, "button") ? e.form : void 0;
                n && !oe._data(n, "submitBubbles") && (oe.event.add(n, "submit._submit", function(t) {
                    t._submit_bubble = !0
                }), oe._data(n, "submitBubbles", !0))
            })
        }, postDispatch: function(t) {
            t._submit_bubble && (delete t._submit_bubble, this.parentNode && !t.isTrigger && oe.event.simulate("submit", this.parentNode, t, !0))
        }, teardown: function() {
            return oe.nodeName(this, "form") ? !1 : void oe.event.remove(this, "._submit")
        }}), ie.changeBubbles || (oe.event.special.change = {setup: function() {
            return Le.test(this.nodeName) ? (("checkbox" === this.type || "radio" === this.type) && (oe.event.add(this, "propertychange._change", function(t) {
                "checked" === t.originalEvent.propertyName && (this._just_changed = !0)
            }), oe.event.add(this, "click._change", function(t) {
                this._just_changed && !t.isTrigger && (this._just_changed = !1), oe.event.simulate("change", this, t, !0)
            })), !1) : void oe.event.add(this, "beforeactivate._change", function(t) {
                var e = t.target;
                Le.test(e.nodeName) && !oe._data(e, "changeBubbles") && (oe.event.add(e, "change._change", function(t) {
                    !this.parentNode || t.isSimulated || t.isTrigger || oe.event.simulate("change", this.parentNode, t, !0)
                }), oe._data(e, "changeBubbles", !0))
            })
        }, handle: function(t) {
            var e = t.target;
            return this !== e || t.isSimulated || t.isTrigger || "radio" !== e.type && "checkbox" !== e.type ? t.handleObj.handler.apply(this, arguments) : void 0
        }, teardown: function() {
            return oe.event.remove(this, "._change"), !Le.test(this.nodeName)
        }}), ie.focusinBubbles || oe.each({focus: "focusin", blur: "focusout"}, function(t, e) {
        var n = function(t) {
            oe.event.simulate(e, t.target, oe.event.fix(t), !0)
        };
        oe.event.special[e] = {setup: function() {
                var i = this.ownerDocument || this, s = oe._data(i, e);
                s || i.addEventListener(t, n, !0), oe._data(i, e, (s || 0) + 1)
            }, teardown: function() {
                var i = this.ownerDocument || this, s = oe._data(i, e) - 1;
                s ? oe._data(i, e, s) : (i.removeEventListener(t, n, !0), oe._removeData(i, e))
            }}
    }), oe.fn.extend({on: function(t, e, n, i, s) {
            var o, r;
            if ("object" == typeof t) {
                "string" != typeof e && (n = n || e, e = void 0);
                for (o in t)
                    this.on(o, e, n, t[o], s);
                return this
            }
            if (null == n && null == i ? (i = e, n = e = void 0) : null == i && ("string" == typeof e ? (i = n, n = void 0) : (i = n, n = e, e = void 0)), i === !1)
                i = p;
            else if (!i)
                return this;
            return 1 === s && (r = i, i = function(t) {
                return oe().off(t), r.apply(this, arguments)
            }, i.guid = r.guid || (r.guid = oe.guid++)), this.each(function() {
                oe.event.add(this, t, i, n, e)
            })
        }, one: function(t, e, n, i) {
            return this.on(t, e, n, i, 1)
        }, off: function(t, e, n) {
            var i, s;
            if (t && t.preventDefault && t.handleObj)
                return i = t.handleObj, oe(t.delegateTarget).off(i.namespace ? i.origType + "." + i.namespace : i.origType, i.selector, i.handler), this;
            if ("object" == typeof t) {
                for (s in t)
                    this.off(s, e, t[s]);
                return this
            }
            return(e === !1 || "function" == typeof e) && (n = e, e = void 0), n === !1 && (n = p), this.each(function() {
                oe.event.remove(this, t, n, e)
            })
        }, trigger: function(t, e) {
            return this.each(function() {
                oe.event.trigger(t, e, this)
            })
        }, triggerHandler: function(t, e) {
            var n = this[0];
            return n ? oe.event.trigger(t, e, n, !0) : void 0
        }});
    var qe = "abbr|article|aside|audio|bdi|canvas|data|datalist|details|figcaption|figure|footer|header|hgroup|mark|meter|nav|output|progress|section|summary|time|video", Oe = / jQuery\d+="(?:null|\d+)"/g, Re = new RegExp("<(?:" + qe + ")[\\s/>]", "i"), Ie = /^\s+/, Be = /<(?!area|br|col|embed|hr|img|input|link|meta|param)(([\w:]+)[^>]*)\/>/gi, We = /<([\w:]+)/, ze = /<tbody/i, $e = /<|&#?\w+;/, Ve = /<(?:script|style|link)/i, Ue = /checked\s*(?:[^=]|=\s*.checked.)/i, Xe = /^$|\/(?:java|ecma)script/i, Ye = /^true\/(.*)/, Ge = /^\s*<!(?:\[CDATA\[|--)|(?:\]\]|--)>\s*$/g, Qe = {option: [1, "<select multiple='multiple'>", "</select>"], legend: [1, "<fieldset>", "</fieldset>"], area: [1, "<map>", "</map>"], param: [1, "<object>", "</object>"], thead: [1, "<table>", "</table>"], tr: [2, "<table><tbody>", "</tbody></table>"], col: [2, "<table><tbody></tbody><colgroup>", "</colgroup></table>"], td: [3, "<table><tbody><tr>", "</tr></tbody></table>"], _default: ie.htmlSerialize ? [0, "", ""] : [1, "X<div>", "</div>"]}, Je = m(me), Ke = Je.appendChild(me.createElement("div"));
    Qe.optgroup = Qe.option, Qe.tbody = Qe.tfoot = Qe.colgroup = Qe.caption = Qe.thead, Qe.th = Qe.td, oe.extend({clone: function(t, e, n) {
            var i, s, o, r, a, l = oe.contains(t.ownerDocument, t);
            if (ie.html5Clone || oe.isXMLDoc(t) || !Re.test("<" + t.nodeName + ">") ? o = t.cloneNode(!0) : (Ke.innerHTML = t.outerHTML, Ke.removeChild(o = Ke.firstChild)), !(ie.noCloneEvent && ie.noCloneChecked || 1 !== t.nodeType && 11 !== t.nodeType || oe.isXMLDoc(t)))
                for (i = v(o), a = v(t), r = 0; null != (s = a[r]); ++r)
                    i[r] && S(s, i[r]);
            if (e)
                if (n)
                    for (a = a || v(t), i = i || v(o), r = 0; null != (s = a[r]); r++)
                        _(s, i[r]);
                else
                    _(t, o);
            return i = v(o, "script"), i.length > 0 && x(i, !l && v(t, "script")), i = a = s = null, o
        }, buildFragment: function(t, e, n, i) {
            for (var s, o, r, a, l, u, c, h = t.length, d = m(e), p = [], f = 0; h > f; f++)
                if (o = t[f], o || 0 === o)
                    if ("object" === oe.type(o))
                        oe.merge(p, o.nodeType ? [o] : o);
                    else if ($e.test(o)) {
                        for (a = a || d.appendChild(e.createElement("div")), l = (We.exec(o) || ["", ""])[1].toLowerCase(), c = Qe[l] || Qe._default, a.innerHTML = c[1] + o.replace(Be, "<$1></$2>") + c[2], s = c[0]; s--; )
                            a = a.lastChild;
                        if (!ie.leadingWhitespace && Ie.test(o) && p.push(e.createTextNode(Ie.exec(o)[0])), !ie.tbody)
                            for (o = "table" !== l || ze.test(o)?"<table>" !== c[1] || ze.test(o)?0:a:a.firstChild, s = o && o.childNodes.length; s--; )
                                oe.nodeName(u = o.childNodes[s], "tbody") && !u.childNodes.length && o.removeChild(u);
                        for (oe.merge(p, a.childNodes), a.textContent = ""; a.firstChild; )
                            a.removeChild(a.firstChild);
                        a = d.lastChild
                    } else
                        p.push(e.createTextNode(o));
            for (a && d.removeChild(a), ie.appendChecked || oe.grep(v(p, "input"), g), f = 0; o = p[f++]; )
                if ((!i || -1 === oe.inArray(o, i)) && (r = oe.contains(o.ownerDocument, o), a = v(d.appendChild(o), "script"), r && x(a), n))
                    for (s = 0; o = a[s++]; )
                        Xe.test(o.type || "") && n.push(o);
            return a = null, d
        }, cleanData: function(t, e) {
            for (var n, i, s, o, r = 0, a = oe.expando, l = oe.cache, u = ie.deleteExpando, c = oe.event.special; null != (n = t[r]); r++)
                if ((e || oe.acceptData(n)) && (s = n[a], o = s && l[s])) {
                    if (o.events)
                        for (i in o.events)
                            c[i] ? oe.event.remove(n, i) : oe.removeEvent(n, i, o.handle);
                    l[s] && (delete l[s], u ? delete n[a] : typeof n.removeAttribute !== ke ? n.removeAttribute(a) : n[a] = null, Y.push(s))
                }
        }}), oe.fn.extend({text: function(t) {
            return Ae(this, function(t) {
                return void 0 === t ? oe.text(this) : this.empty().append((this[0] && this[0].ownerDocument || me).createTextNode(t))
            }, null, t, arguments.length)
        }, append: function() {
            return this.domManip(arguments, function(t) {
                if (1 === this.nodeType || 11 === this.nodeType || 9 === this.nodeType) {
                    var e = y(this, t);
                    e.appendChild(t)
                }
            })
        }, prepend: function() {
            return this.domManip(arguments, function(t) {
                if (1 === this.nodeType || 11 === this.nodeType || 9 === this.nodeType) {
                    var e = y(this, t);
                    e.insertBefore(t, e.firstChild)
                }
            })
        }, before: function() {
            return this.domManip(arguments, function(t) {
                this.parentNode && this.parentNode.insertBefore(t, this)
            })
        }, after: function() {
            return this.domManip(arguments, function(t) {
                this.parentNode && this.parentNode.insertBefore(t, this.nextSibling)
            })
        }, remove: function(t, e) {
            for (var n, i = t ? oe.filter(t, this) : this, s = 0; null != (n = i[s]); s++)
                e || 1 !== n.nodeType || oe.cleanData(v(n)), n.parentNode && (e && oe.contains(n.ownerDocument, n) && x(v(n, "script")), n.parentNode.removeChild(n));
            return this
        }, empty: function() {
            for (var t, e = 0; null != (t = this[e]); e++) {
                for (1 === t.nodeType && oe.cleanData(v(t, !1)); t.firstChild; )
                    t.removeChild(t.firstChild);
                t.options && oe.nodeName(t, "select") && (t.options.length = 0)
            }
            return this
        }, clone: function(t, e) {
            return t = null == t ? !1 : t, e = null == e ? t : e, this.map(function() {
                return oe.clone(this, t, e)
            })
        }, html: function(t) {
            return Ae(this, function(t) {
                var e = this[0] || {}, n = 0, i = this.length;
                if (void 0 === t)
                    return 1 === e.nodeType ? e.innerHTML.replace(Oe, "") : void 0;
                if (!("string" != typeof t || Ve.test(t) || !ie.htmlSerialize && Re.test(t) || !ie.leadingWhitespace && Ie.test(t) || Qe[(We.exec(t) || ["", ""])[1].toLowerCase()])) {
                    t = t.replace(Be, "<$1></$2>");
                    try {
                        for (; i > n; n++)
                            e = this[n] || {}, 1 === e.nodeType && (oe.cleanData(v(e, !1)), e.innerHTML = t);
                        e = 0
                    } catch (s) {
                    }
                }
                e && this.empty().append(t)
            }, null, t, arguments.length)
        }, replaceWith: function() {
            var t = arguments[0];
            return this.domManip(arguments, function(e) {
                t = this.parentNode, oe.cleanData(v(this)), t && t.replaceChild(e, this)
            }), t && (t.length || t.nodeType) ? this : this.remove()
        }, detach: function(t) {
            return this.remove(t, !0)
        }, domManip: function(t, e) {
            t = Q.apply([], t);
            var n, i, s, o, r, a, l = 0, u = this.length, c = this, h = u - 1, d = t[0], p = oe.isFunction(d);
            if (p || u > 1 && "string" == typeof d && !ie.checkClone && Ue.test(d))
                return this.each(function(n) {
                    var i = c.eq(n);
                    p && (t[0] = d.call(this, n, i.html())), i.domManip(t, e)
                });
            if (u && (a = oe.buildFragment(t, this[0].ownerDocument, !1, this), n = a.firstChild, 1 === a.childNodes.length && (a = n), n)) {
                for (o = oe.map(v(a, "script"), b), s = o.length; u > l; l++)
                    i = a, l !== h && (i = oe.clone(i, !0, !0), s && oe.merge(o, v(i, "script"))), e.call(this[l], i, l);
                if (s)
                    for (r = o[o.length - 1].ownerDocument, oe.map(o, w), l = 0; s > l; l++)
                        i = o[l], Xe.test(i.type || "") && !oe._data(i, "globalEval") && oe.contains(r, i) && (i.src ? oe._evalUrl && oe._evalUrl(i.src) : oe.globalEval((i.text || i.textContent || i.innerHTML || "").replace(Ge, "")));
                a = n = null
            }
            return this
        }}), oe.each({appendTo: "append", prependTo: "prepend", insertBefore: "before", insertAfter: "after", replaceAll: "replaceWith"}, function(t, e) {
        oe.fn[t] = function(t) {
            for (var n, i = 0, s = [], o = oe(t), r = o.length - 1; r >= i; i++)
                n = i === r ? this : this.clone(!0), oe(o[i])[e](n), J.apply(s, n.get());
            return this.pushStack(s)
        }
    });
    var Ze, tn = {};
    !function() {
        var t, e, n = me.createElement("div"), i = "-webkit-box-sizing:content-box;-moz-box-sizing:content-box;box-sizing:content-box;display:block;padding:0;margin:0;border:0";
        n.innerHTML = "  <link/><table></table><a href='/a'>a</a><input type='checkbox'/>", t = n.getElementsByTagName("a")[0], t.style.cssText = "float:left;opacity:.5", ie.opacity = /^0.5/.test(t.style.opacity), ie.cssFloat = !!t.style.cssFloat, n.style.backgroundClip = "content-box", n.cloneNode(!0).style.backgroundClip = "", ie.clearCloneStyle = "content-box" === n.style.backgroundClip, t = n = null, ie.shrinkWrapBlocks = function() {
            var t, n, s, o;
            if (null == e) {
                if (t = me.getElementsByTagName("body")[0], !t)
                    return;
                o = "border:0;width:0;height:0;position:absolute;top:0;left:-9999px", n = me.createElement("div"), s = me.createElement("div"), t.appendChild(n).appendChild(s), e = !1, typeof s.style.zoom !== ke && (s.style.cssText = i + ";width:1px;padding:1px;zoom:1", s.innerHTML = "<div></div>", s.firstChild.style.width = "5px", e = 3 !== s.offsetWidth), t.removeChild(n), t = n = s = null
            }
            return e
        }
    }();
    var en, nn, sn = /^margin/, on = new RegExp("^(" + Te + ")(?!px)[a-z%]+$", "i"), rn = /^(top|right|bottom|left)$/;
    t.getComputedStyle ? (en = function(t) {
        return t.ownerDocument.defaultView.getComputedStyle(t, null)
    }, nn = function(t, e, n) {
        var i, s, o, r, a = t.style;
        return n = n || en(t), r = n ? n.getPropertyValue(e) || n[e] : void 0, n && ("" !== r || oe.contains(t.ownerDocument, t) || (r = oe.style(t, e)), on.test(r) && sn.test(e) && (i = a.width, s = a.minWidth, o = a.maxWidth, a.minWidth = a.maxWidth = a.width = r, r = n.width, a.width = i, a.minWidth = s, a.maxWidth = o)), void 0 === r ? r : r + ""
    }) : me.documentElement.currentStyle && (en = function(t) {
        return t.currentStyle
    }, nn = function(t, e, n) {
        var i, s, o, r, a = t.style;
        return n = n || en(t), r = n ? n[e] : void 0, null == r && a && a[e] && (r = a[e]), on.test(r) && !rn.test(e) && (i = a.left, s = t.runtimeStyle, o = s && s.left, o && (s.left = t.currentStyle.left), a.left = "fontSize" === e ? "1em" : r, r = a.pixelLeft + "px", a.left = i, o && (s.left = o)), void 0 === r ? r : r + "" || "auto"
    }), !function() {
        function e() {
            var e, n, i = me.getElementsByTagName("body")[0];
            i && (e = me.createElement("div"), n = me.createElement("div"), e.style.cssText = u, i.appendChild(e).appendChild(n), n.style.cssText = "-webkit-box-sizing:border-box;-moz-box-sizing:border-box;box-sizing:border-box;position:absolute;display:block;padding:1px;border:1px;width:4px;margin-top:1%;top:1%", oe.swap(i, null != i.style.zoom ? {zoom: 1} : {}, function() {
                s = 4 === n.offsetWidth
            }), o = !0, r = !1, a = !0, t.getComputedStyle && (r = "1%" !== (t.getComputedStyle(n, null) || {}).top, o = "4px" === (t.getComputedStyle(n, null) || {width: "4px"}).width), i.removeChild(e), n = i = null)
        }
        var n, i, s, o, r, a, l = me.createElement("div"), u = "border:0;width:0;height:0;position:absolute;top:0;left:-9999px", c = "-webkit-box-sizing:content-box;-moz-box-sizing:content-box;box-sizing:content-box;display:block;padding:0;margin:0;border:0";
        l.innerHTML = "  <link/><table></table><a href='/a'>a</a><input type='checkbox'/>", n = l.getElementsByTagName("a")[0], n.style.cssText = "float:left;opacity:.5", ie.opacity = /^0.5/.test(n.style.opacity), ie.cssFloat = !!n.style.cssFloat, l.style.backgroundClip = "content-box", l.cloneNode(!0).style.backgroundClip = "", ie.clearCloneStyle = "content-box" === l.style.backgroundClip, n = l = null, oe.extend(ie, {reliableHiddenOffsets: function() {
                if (null != i)
                    return i;
                var t, e, n, s = me.createElement("div"), o = me.getElementsByTagName("body")[0];
                return o ? (s.setAttribute("className", "t"), s.innerHTML = "  <link/><table></table><a href='/a'>a</a><input type='checkbox'/>", t = me.createElement("div"), t.style.cssText = u, o.appendChild(t).appendChild(s), s.innerHTML = "<table><tr><td></td><td>t</td></tr></table>", e = s.getElementsByTagName("td"), e[0].style.cssText = "padding:0;margin:0;border:0;display:none", n = 0 === e[0].offsetHeight, e[0].style.display = "", e[1].style.display = "none", i = n && 0 === e[0].offsetHeight, o.removeChild(t), s = o = null, i) : void 0
            }, boxSizing: function() {
                return null == s && e(), s
            }, boxSizingReliable: function() {
                return null == o && e(), o
            }, pixelPosition: function() {
                return null == r && e(), r
            }, reliableMarginRight: function() {
                var e, n, i, s;
                if (null == a && t.getComputedStyle) {
                    if (e = me.getElementsByTagName("body")[0], !e)
                        return;
                    n = me.createElement("div"), i = me.createElement("div"), n.style.cssText = u, e.appendChild(n).appendChild(i), s = i.appendChild(me.createElement("div")), s.style.cssText = i.style.cssText = c, s.style.marginRight = s.style.width = "0", i.style.width = "1px", a = !parseFloat((t.getComputedStyle(s, null) || {}).marginRight), e.removeChild(n)
                }
                return a
            }})
    }(), oe.swap = function(t, e, n, i) {
        var s, o, r = {};
        for (o in e)
            r[o] = t.style[o], t.style[o] = e[o];
        s = n.apply(t, i || []);
        for (o in e)
            t.style[o] = r[o];
        return s
    };
    var an = /alpha\([^)]*\)/i, ln = /opacity\s*=\s*([^)]*)/, un = /^(none|table(?!-c[ea]).+)/, cn = new RegExp("^(" + Te + ")(.*)$", "i"), hn = new RegExp("^([+-])=(" + Te + ")", "i"), dn = {position: "absolute", visibility: "hidden", display: "block"}, pn = {letterSpacing: 0, fontWeight: 400}, fn = ["Webkit", "O", "Moz", "ms"];
    oe.extend({cssHooks: {opacity: {get: function(t, e) {
                    if (e) {
                        var n = nn(t, "opacity");
                        return"" === n ? "1" : n
                    }
                }}}, cssNumber: {columnCount: !0, fillOpacity: !0, fontWeight: !0, lineHeight: !0, opacity: !0, order: !0, orphans: !0, widows: !0, zIndex: !0, zoom: !0}, cssProps: {"float": ie.cssFloat ? "cssFloat" : "styleFloat"}, style: function(t, e, n, i) {
            if (t && 3 !== t.nodeType && 8 !== t.nodeType && t.style) {
                var s, o, r, a = oe.camelCase(e), l = t.style;
                if (e = oe.cssProps[a] || (oe.cssProps[a] = T(l, a)), r = oe.cssHooks[e] || oe.cssHooks[a], void 0 === n)
                    return r && "get"in r && void 0 !== (s = r.get(t, !1, i)) ? s : l[e];
                if (o = typeof n, "string" === o && (s = hn.exec(n)) && (n = (s[1] + 1) * s[2] + parseFloat(oe.css(t, e)), o = "number"), null != n && n === n && ("number" !== o || oe.cssNumber[a] || (n += "px"), ie.clearCloneStyle || "" !== n || 0 !== e.indexOf("background") || (l[e] = "inherit"), !(r && "set"in r && void 0 === (n = r.set(t, n, i)))))
                    try {
                        l[e] = "", l[e] = n
                    } catch (u) {
                    }
            }
        }, css: function(t, e, n, i) {
            var s, o, r, a = oe.camelCase(e);
            return e = oe.cssProps[a] || (oe.cssProps[a] = T(t.style, a)), r = oe.cssHooks[e] || oe.cssHooks[a], r && "get"in r && (o = r.get(t, !0, n)), void 0 === o && (o = nn(t, e, i)), "normal" === o && e in pn && (o = pn[e]), "" === n || n ? (s = parseFloat(o), n === !0 || oe.isNumeric(s) ? s || 0 : o) : o
        }}), oe.each(["height", "width"], function(t, e) {
        oe.cssHooks[e] = {get: function(t, n, i) {
                return n ? 0 === t.offsetWidth && un.test(oe.css(t, "display")) ? oe.swap(t, dn, function() {
                    return N(t, e, i)
                }) : N(t, e, i) : void 0
            }, set: function(t, n, i) {
                var s = i && en(t);
                return P(t, n, i ? A(t, e, i, ie.boxSizing() && "border-box" === oe.css(t, "boxSizing", !1, s), s) : 0)
            }}
    }), ie.opacity || (oe.cssHooks.opacity = {get: function(t, e) {
            return ln.test((e && t.currentStyle ? t.currentStyle.filter : t.style.filter) || "") ? .01 * parseFloat(RegExp.$1) + "" : e ? "1" : ""
        }, set: function(t, e) {
            var n = t.style, i = t.currentStyle, s = oe.isNumeric(e) ? "alpha(opacity=" + 100 * e + ")" : "", o = i && i.filter || n.filter || "";
            n.zoom = 1, (e >= 1 || "" === e) && "" === oe.trim(o.replace(an, "")) && n.removeAttribute && (n.removeAttribute("filter"), "" === e || i && !i.filter) || (n.filter = an.test(o) ? o.replace(an, s) : o + " " + s)
        }}), oe.cssHooks.marginRight = j(ie.reliableMarginRight, function(t, e) {
        return e ? oe.swap(t, {display: "inline-block"}, nn, [t, "marginRight"]) : void 0
    }), oe.each({margin: "", padding: "", border: "Width"}, function(t, e) {
        oe.cssHooks[t + e] = {expand: function(n) {
                for (var i = 0, s = {}, o = "string" == typeof n ? n.split(" ") : [n]; 4 > i; i++)
                    s[t + Ee[i] + e] = o[i] || o[i - 2] || o[0];
                return s
            }}, sn.test(t) || (oe.cssHooks[t + e].set = P)
    }), oe.fn.extend({css: function(t, e) {
            return Ae(this, function(t, e, n) {
                var i, s, o = {}, r = 0;
                if (oe.isArray(e)) {
                    for (i = en(t), s = e.length; s > r; r++)
                        o[e[r]] = oe.css(t, e[r], !1, i);
                    return o
                }
                return void 0 !== n ? oe.style(t, e, n) : oe.css(t, e)
            }, t, e, arguments.length > 1)
        }, show: function() {
            return E(this, !0)
        }, hide: function() {
            return E(this)
        }, toggle: function(t) {
            return"boolean" == typeof t ? t ? this.show() : this.hide() : this.each(function() {
                Pe(this) ? oe(this).show() : oe(this).hide()
            })
        }}), oe.Tween = L, L.prototype = {constructor: L, init: function(t, e, n, i, s, o) {
            this.elem = t, this.prop = n, this.easing = s || "swing", this.options = e, this.start = this.now = this.cur(), this.end = i, this.unit = o || (oe.cssNumber[n] ? "" : "px")
        }, cur: function() {
            var t = L.propHooks[this.prop];
            return t && t.get ? t.get(this) : L.propHooks._default.get(this)
        }, run: function(t) {
            var e, n = L.propHooks[this.prop];
            return this.pos = e = this.options.duration ? oe.easing[this.easing](t, this.options.duration * t, 0, 1, this.options.duration) : t, this.now = (this.end - this.start) * e + this.start, this.options.step && this.options.step.call(this.elem, this.now, this), n && n.set ? n.set(this) : L.propHooks._default.set(this), this
        }}, L.prototype.init.prototype = L.prototype, L.propHooks = {_default: {get: function(t) {
                var e;
                return null == t.elem[t.prop] || t.elem.style && null != t.elem.style[t.prop] ? (e = oe.css(t.elem, t.prop, ""), e && "auto" !== e ? e : 0) : t.elem[t.prop]
            }, set: function(t) {
                oe.fx.step[t.prop] ? oe.fx.step[t.prop](t) : t.elem.style && (null != t.elem.style[oe.cssProps[t.prop]] || oe.cssHooks[t.prop]) ? oe.style(t.elem, t.prop, t.now + t.unit) : t.elem[t.prop] = t.now
            }}}, L.propHooks.scrollTop = L.propHooks.scrollLeft = {set: function(t) {
            t.elem.nodeType && t.elem.parentNode && (t.elem[t.prop] = t.now)
        }}, oe.easing = {linear: function(t) {
            return t
        }, swing: function(t) {
            return.5 - Math.cos(t * Math.PI) / 2
        }}, oe.fx = L.prototype.init, oe.fx.step = {};
    var mn, vn, gn = /^(?:toggle|show|hide)$/, yn = new RegExp("^(?:([+-])=|)(" + Te + ")([a-z%]*)$", "i"), bn = /queueHooks$/, wn = [D], xn = {"*": [function(t, e) {
                var n = this.createTween(t, e), i = n.cur(), s = yn.exec(e), o = s && s[3] || (oe.cssNumber[t] ? "" : "px"), r = (oe.cssNumber[t] || "px" !== o && +i) && yn.exec(oe.css(n.elem, t)), a = 1, l = 20;
                if (r && r[3] !== o) {
                    o = o || r[3], s = s || [], r = +i || 1;
                    do
                        a = a || ".5", r /= a, oe.style(n.elem, t, r + o);
                    while (a !== (a = n.cur() / i) && 1 !== a && --l)
                }
                return s && (r = n.start = +r || +i || 0, n.unit = o, n.end = s[1] ? r + (s[1] + 1) * s[2] : +s[2]), n
            }]};
    oe.Animation = oe.extend(O, {tweener: function(t, e) {
            oe.isFunction(t) ? (e = t, t = ["*"]) : t = t.split(" ");
            for (var n, i = 0, s = t.length; s > i; i++)
                n = t[i], xn[n] = xn[n] || [], xn[n].unshift(e)
        }, prefilter: function(t, e) {
            e ? wn.unshift(t) : wn.push(t)
        }}), oe.speed = function(t, e, n) {
        var i = t && "object" == typeof t ? oe.extend({}, t) : {complete: n || !n && e || oe.isFunction(t) && t, duration: t, easing: n && e || e && !oe.isFunction(e) && e};
        return i.duration = oe.fx.off ? 0 : "number" == typeof i.duration ? i.duration : i.duration in oe.fx.speeds ? oe.fx.speeds[i.duration] : oe.fx.speeds._default, (null == i.queue || i.queue === !0) && (i.queue = "fx"), i.old = i.complete, i.complete = function() {
            oe.isFunction(i.old) && i.old.call(this), i.queue && oe.dequeue(this, i.queue)
        }, i
    }, oe.fn.extend({fadeTo: function(t, e, n, i) {
            return this.filter(Pe).css("opacity", 0).show().end().animate({opacity: e}, t, n, i)
        }, animate: function(t, e, n, i) {
            var s = oe.isEmptyObject(t), o = oe.speed(e, n, i), r = function() {
                var e = O(this, oe.extend({}, t), o);
                (s || oe._data(this, "finish")) && e.stop(!0)
            };
            return r.finish = r, s || o.queue === !1 ? this.each(r) : this.queue(o.queue, r)
        }, stop: function(t, e, n) {
            var i = function(t) {
                var e = t.stop;
                delete t.stop, e(n)
            };
            return"string" != typeof t && (n = e, e = t, t = void 0), e && t !== !1 && this.queue(t || "fx", []), this.each(function() {
                var e = !0, s = null != t && t + "queueHooks", o = oe.timers, r = oe._data(this);
                if (s)
                    r[s] && r[s].stop && i(r[s]);
                else
                    for (s in r)
                        r[s] && r[s].stop && bn.test(s) && i(r[s]);
                for (s = o.length; s--; )
                    o[s].elem !== this || null != t && o[s].queue !== t || (o[s].anim.stop(n), e = !1, o.splice(s, 1));
                (e || !n) && oe.dequeue(this, t)
            })
        }, finish: function(t) {
            return t !== !1 && (t = t || "fx"), this.each(function() {
                var e, n = oe._data(this), i = n[t + "queue"], s = n[t + "queueHooks"], o = oe.timers, r = i ? i.length : 0;
                for (n.finish = !0, oe.queue(this, t, []), s && s.stop && s.stop.call(this, !0), e = o.length; e--; )
                    o[e].elem === this && o[e].queue === t && (o[e].anim.stop(!0), o.splice(e, 1));
                for (e = 0; r > e; e++)
                    i[e] && i[e].finish && i[e].finish.call(this);
                delete n.finish
            })
        }}), oe.each(["toggle", "show", "hide"], function(t, e) {
        var n = oe.fn[e];
        oe.fn[e] = function(t, i, s) {
            return null == t || "boolean" == typeof t ? n.apply(this, arguments) : this.animate(F(e, !0), t, i, s)
        }
    }), oe.each({slideDown: F("show"), slideUp: F("hide"), slideToggle: F("toggle"), fadeIn: {opacity: "show"}, fadeOut: {opacity: "hide"}, fadeToggle: {opacity: "toggle"}}, function(t, e) {
        oe.fn[t] = function(t, n, i) {
            return this.animate(e, t, n, i)
        }
    }), oe.timers = [], oe.fx.tick = function() {
        var t, e = oe.timers, n = 0;
        for (mn = oe.now(); n < e.length; n++)
            t = e[n], t() || e[n] !== t || e.splice(n--, 1);
        e.length || oe.fx.stop(), mn = void 0
    }, oe.fx.timer = function(t) {
        oe.timers.push(t), t() ? oe.fx.start() : oe.timers.pop()
    }, oe.fx.interval = 13, oe.fx.start = function() {
        vn || (vn = setInterval(oe.fx.tick, oe.fx.interval))
    }, oe.fx.stop = function() {
        clearInterval(vn), vn = null
    }, oe.fx.speeds = {slow: 600, fast: 200, _default: 400}, oe.fn.delay = function(t, e) {
        return t = oe.fx ? oe.fx.speeds[t] || t : t, e = e || "fx", this.queue(e, function(e, n) {
            var i = setTimeout(e, t);
            n.stop = function() {
                clearTimeout(i)
            }
        })
    }, function() {
        var t, e, n, i, s = me.createElement("div");
        s.setAttribute("className", "t"), s.innerHTML = "  <link/><table></table><a href='/a'>a</a><input type='checkbox'/>", t = s.getElementsByTagName("a")[0], n = me.createElement("select"), i = n.appendChild(me.createElement("option")), e = s.getElementsByTagName("input")[0], t.style.cssText = "top:1px", ie.getSetAttribute = "t" !== s.className, ie.style = /top/.test(t.getAttribute("style")), ie.hrefNormalized = "/a" === t.getAttribute("href"), ie.checkOn = !!e.value, ie.optSelected = i.selected, ie.enctype = !!me.createElement("form").enctype, n.disabled = !0, ie.optDisabled = !i.disabled, e = me.createElement("input"), e.setAttribute("value", ""), ie.input = "" === e.getAttribute("value"), e.value = "t", e.setAttribute("type", "radio"), ie.radioValue = "t" === e.value, t = e = n = i = s = null
    }();
    var _n = /\r/g;
    oe.fn.extend({val: function(t) {
            var e, n, i, s = this[0];
            return arguments.length ? (i = oe.isFunction(t), this.each(function(n) {
                var s;
                1 === this.nodeType && (s = i ? t.call(this, n, oe(this).val()) : t, null == s ? s = "" : "number" == typeof s ? s += "" : oe.isArray(s) && (s = oe.map(s, function(t) {
                    return null == t ? "" : t + ""
                })), e = oe.valHooks[this.type] || oe.valHooks[this.nodeName.toLowerCase()], e && "set"in e && void 0 !== e.set(this, s, "value") || (this.value = s))
            })) : s ? (e = oe.valHooks[s.type] || oe.valHooks[s.nodeName.toLowerCase()], e && "get"in e && void 0 !== (n = e.get(s, "value")) ? n : (n = s.value, "string" == typeof n ? n.replace(_n, "") : null == n ? "" : n)) : void 0
        }}), oe.extend({valHooks: {option: {get: function(t) {
                    var e = oe.find.attr(t, "value");
                    return null != e ? e : oe.text(t)
                }}, select: {get: function(t) {
                    for (var e, n, i = t.options, s = t.selectedIndex, o = "select-one" === t.type || 0 > s, r = o ? null : [], a = o ? s + 1 : i.length, l = 0 > s ? a : o ? s : 0; a > l; l++)
                        if (n = i[l], !(!n.selected && l !== s || (ie.optDisabled ? n.disabled : null !== n.getAttribute("disabled")) || n.parentNode.disabled && oe.nodeName(n.parentNode, "optgroup"))) {
                            if (e = oe(n).val(), o)
                                return e;
                            r.push(e)
                        }
                    return r
                }, set: function(t, e) {
                    for (var n, i, s = t.options, o = oe.makeArray(e), r = s.length; r--; )
                        if (i = s[r], oe.inArray(oe.valHooks.option.get(i), o) >= 0)
                            try {
                                i.selected = n = !0
                            } catch (a) {
                                i.scrollHeight
                            }
                        else
                            i.selected = !1;
                    return n || (t.selectedIndex = -1), s
                }}}}), oe.each(["radio", "checkbox"], function() {
        oe.valHooks[this] = {set: function(t, e) {
                return oe.isArray(e) ? t.checked = oe.inArray(oe(t).val(), e) >= 0 : void 0
            }}, ie.checkOn || (oe.valHooks[this].get = function(t) {
            return null === t.getAttribute("value") ? "on" : t.value
        })
    });
    var Sn, kn, Cn = oe.expr.attrHandle, jn = /^(?:checked|selected)$/i, Tn = ie.getSetAttribute, En = ie.input;
    oe.fn.extend({attr: function(t, e) {
            return Ae(this, oe.attr, t, e, arguments.length > 1)
        }, removeAttr: function(t) {
            return this.each(function() {
                oe.removeAttr(this, t)
            })
        }}), oe.extend({attr: function(t, e, n) {
            var i, s, o = t.nodeType;
            return t && 3 !== o && 8 !== o && 2 !== o ? typeof t.getAttribute === ke ? oe.prop(t, e, n) : (1 === o && oe.isXMLDoc(t) || (e = e.toLowerCase(), i = oe.attrHooks[e] || (oe.expr.match.bool.test(e) ? kn : Sn)), void 0 === n ? i && "get"in i && null !== (s = i.get(t, e)) ? s : (s = oe.find.attr(t, e), null == s ? void 0 : s) : null !== n ? i && "set"in i && void 0 !== (s = i.set(t, n, e)) ? s : (t.setAttribute(e, n + ""), n) : void oe.removeAttr(t, e)) : void 0
        }, removeAttr: function(t, e) {
            var n, i, s = 0, o = e && e.match(we);
            if (o && 1 === t.nodeType)
                for (; n = o[s++]; )
                    i = oe.propFix[n] || n, oe.expr.match.bool.test(n) ? En && Tn || !jn.test(n) ? t[i] = !1 : t[oe.camelCase("default-" + n)] = t[i] = !1 : oe.attr(t, n, ""), t.removeAttribute(Tn ? n : i)
        }, attrHooks: {type: {set: function(t, e) {
                    if (!ie.radioValue && "radio" === e && oe.nodeName(t, "input")) {
                        var n = t.value;
                        return t.setAttribute("type", e), n && (t.value = n), e
                    }
                }}}}), kn = {set: function(t, e, n) {
            return e === !1 ? oe.removeAttr(t, n) : En && Tn || !jn.test(n) ? t.setAttribute(!Tn && oe.propFix[n] || n, n) : t[oe.camelCase("default-" + n)] = t[n] = !0, n
        }}, oe.each(oe.expr.match.bool.source.match(/\w+/g), function(t, e) {
        var n = Cn[e] || oe.find.attr;
        Cn[e] = En && Tn || !jn.test(e) ? function(t, e, i) {
            var s, o;
            return i || (o = Cn[e], Cn[e] = s, s = null != n(t, e, i) ? e.toLowerCase() : null, Cn[e] = o), s
        } : function(t, e, n) {
            return n ? void 0 : t[oe.camelCase("default-" + e)] ? e.toLowerCase() : null
        }
    }), En && Tn || (oe.attrHooks.value = {set: function(t, e, n) {
            return oe.nodeName(t, "input") ? void(t.defaultValue = e) : Sn && Sn.set(t, e, n)
        }}), Tn || (Sn = {set: function(t, e, n) {
            var i = t.getAttributeNode(n);
            return i || t.setAttributeNode(i = t.ownerDocument.createAttribute(n)), i.value = e += "", "value" === n || e === t.getAttribute(n) ? e : void 0
        }}, Cn.id = Cn.name = Cn.coords = function(t, e, n) {
        var i;
        return n ? void 0 : (i = t.getAttributeNode(e)) && "" !== i.value ? i.value : null
    }, oe.valHooks.button = {get: function(t, e) {
            var n = t.getAttributeNode(e);
            return n && n.specified ? n.value : void 0
        }, set: Sn.set}, oe.attrHooks.contenteditable = {set: function(t, e, n) {
            Sn.set(t, "" === e ? !1 : e, n)
        }}, oe.each(["width", "height"], function(t, e) {
        oe.attrHooks[e] = {set: function(t, n) {
                return"" === n ? (t.setAttribute(e, "auto"), n) : void 0
            }}
    })), ie.style || (oe.attrHooks.style = {get: function(t) {
            return t.style.cssText || void 0
        }, set: function(t, e) {
            return t.style.cssText = e + ""
        }});
    var Pn = /^(?:input|select|textarea|button|object)$/i, An = /^(?:a|area)$/i;
    oe.fn.extend({prop: function(t, e) {
            return Ae(this, oe.prop, t, e, arguments.length > 1)
        }, removeProp: function(t) {
            return t = oe.propFix[t] || t, this.each(function() {
                try {
                    this[t] = void 0, delete this[t]
                } catch (e) {
                }
            })
        }}), oe.extend({propFix: {"for": "htmlFor", "class": "className"}, prop: function(t, e, n) {
            var i, s, o, r = t.nodeType;
            return t && 3 !== r && 8 !== r && 2 !== r ? (o = 1 !== r || !oe.isXMLDoc(t), o && (e = oe.propFix[e] || e, s = oe.propHooks[e]), void 0 !== n ? s && "set"in s && void 0 !== (i = s.set(t, n, e)) ? i : t[e] = n : s && "get"in s && null !== (i = s.get(t, e)) ? i : t[e]) : void 0
        }, propHooks: {tabIndex: {get: function(t) {
                    var e = oe.find.attr(t, "tabindex");
                    return e ? parseInt(e, 10) : Pn.test(t.nodeName) || An.test(t.nodeName) && t.href ? 0 : -1
                }}}}), ie.hrefNormalized || oe.each(["href", "src"], function(t, e) {
        oe.propHooks[e] = {get: function(t) {
                return t.getAttribute(e, 4)
            }}
    }), ie.optSelected || (oe.propHooks.selected = {get: function(t) {
            var e = t.parentNode;
            return e && (e.selectedIndex, e.parentNode && e.parentNode.selectedIndex), null
        }}), oe.each(["tabIndex", "readOnly", "maxLength", "cellSpacing", "cellPadding", "rowSpan", "colSpan", "useMap", "frameBorder", "contentEditable"], function() {
        oe.propFix[this.toLowerCase()] = this
    }), ie.enctype || (oe.propFix.enctype = "encoding");
    var Nn = /[\t\r\n\f]/g;
    oe.fn.extend({addClass: function(t) {
            var e, n, i, s, o, r, a = 0, l = this.length, u = "string" == typeof t && t;
            if (oe.isFunction(t))
                return this.each(function(e) {
                    oe(this).addClass(t.call(this, e, this.className))
                });
            if (u)
                for (e = (t || "").match(we) || []; l > a; a++)
                    if (n = this[a], i = 1 === n.nodeType && (n.className ? (" " + n.className + " ").replace(Nn, " ") : " ")) {
                        for (o = 0; s = e[o++]; )
                            i.indexOf(" " + s + " ") < 0 && (i += s + " ");
                        r = oe.trim(i), n.className !== r && (n.className = r)
                    }
            return this
        }, removeClass: function(t) {
            var e, n, i, s, o, r, a = 0, l = this.length, u = 0 === arguments.length || "string" == typeof t && t;
            if (oe.isFunction(t))
                return this.each(function(e) {
                    oe(this).removeClass(t.call(this, e, this.className))
                });
            if (u)
                for (e = (t || "").match(we) || []; l > a; a++)
                    if (n = this[a], i = 1 === n.nodeType && (n.className ? (" " + n.className + " ").replace(Nn, " ") : "")) {
                        for (o = 0; s = e[o++]; )
                            for (; i.indexOf(" " + s + " ") >= 0; )
                                i = i.replace(" " + s + " ", " ");
                        r = t ? oe.trim(i) : "", n.className !== r && (n.className = r)
                    }
            return this
        }, toggleClass: function(t, e) {
            var n = typeof t;
            return"boolean" == typeof e && "string" === n ? e ? this.addClass(t) : this.removeClass(t) : this.each(oe.isFunction(t) ? function(n) {
                oe(this).toggleClass(t.call(this, n, this.className, e), e)
            } : function() {
                if ("string" === n)
                    for (var e, i = 0, s = oe(this), o = t.match(we) || []; e = o[i++]; )
                        s.hasClass(e) ? s.removeClass(e) : s.addClass(e);
                else
                    (n === ke || "boolean" === n) && (this.className && oe._data(this, "__className__", this.className), this.className = this.className || t === !1 ? "" : oe._data(this, "__className__") || "")
            })
        }, hasClass: function(t) {
            for (var e = " " + t + " ", n = 0, i = this.length; i > n; n++)
                if (1 === this[n].nodeType && (" " + this[n].className + " ").replace(Nn, " ").indexOf(e) >= 0)
                    return!0;
            return!1
        }}), oe.each("blur focus focusin focusout load resize scroll unload click dblclick mousedown mouseup mousemove mouseover mouseout mouseenter mouseleave change select submit keydown keypress keyup error contextmenu".split(" "), function(t, e) {
        oe.fn[e] = function(t, n) {
            return arguments.length > 0 ? this.on(e, null, t, n) : this.trigger(e)
        }
    }), oe.fn.extend({hover: function(t, e) {
            return this.mouseenter(t).mouseleave(e || t)
        }, bind: function(t, e, n) {
            return this.on(t, null, e, n)
        }, unbind: function(t, e) {
            return this.off(t, null, e)
        }, delegate: function(t, e, n, i) {
            return this.on(e, t, n, i)
        }, undelegate: function(t, e, n) {
            return 1 === arguments.length ? this.off(t, "**") : this.off(e, t || "**", n)
        }});
    var Ln = oe.now(), Hn = /\?/, Fn = /(,)|(\[|{)|(}|])|"(?:[^"\\\r\n]|\\["\\\/bfnrt]|\\u[\da-fA-F]{4})*"\s*:?|true|false|null|-?(?!0\d)\d+(?:\.\d+|)(?:[eE][+-]?\d+|)/g;
    oe.parseJSON = function(e) {
        if (t.JSON && t.JSON.parse)
            return t.JSON.parse(e + "");
        var n, i = null, s = oe.trim(e + "");
        return s && !oe.trim(s.replace(Fn, function(t, e, s, o) {
            return n && e && (i = 0), 0 === i ? t : (n = s || e, i += !o - !s, "")
        })) ? Function("return " + s)() : oe.error("Invalid JSON: " + e)
    }, oe.parseXML = function(e) {
        var n, i;
        if (!e || "string" != typeof e)
            return null;
        try {
            t.DOMParser ? (i = new DOMParser, n = i.parseFromString(e, "text/xml")) : (n = new ActiveXObject("Microsoft.XMLDOM"), n.async = "false", n.loadXML(e))
        } catch (s) {
            n = void 0
        }
        return n && n.documentElement && !n.getElementsByTagName("parsererror").length || oe.error("Invalid XML: " + e), n
    };
    var Mn, Dn, qn = /#.*$/, On = /([?&])_=[^&]*/, Rn = /^(.*?):[ \t]*([^\r\n]*)\r?$/gm, In = /^(?:about|app|app-storage|.+-extension|file|res|widget):$/, Bn = /^(?:GET|HEAD)$/, Wn = /^\/\//, zn = /^([\w.+-]+:)(?:\/\/(?:[^\/?#]*@|)([^\/?#:]*)(?::(\d+)|)|)/, $n = {}, Vn = {}, Un = "*/".concat("*");
    try {
        Dn = location.href
    } catch (Xn) {
        Dn = me.createElement("a"), Dn.href = "", Dn = Dn.href
    }
    Mn = zn.exec(Dn.toLowerCase()) || [], oe.extend({active: 0, lastModified: {}, etag: {}, ajaxSettings: {url: Dn, type: "GET", isLocal: In.test(Mn[1]), global: !0, processData: !0, async: !0, contentType: "application/x-www-form-urlencoded; charset=UTF-8", accepts: {"*": Un, text: "text/plain", html: "text/html", xml: "application/xml, text/xml", json: "application/json, text/javascript"}, contents: {xml: /xml/, html: /html/, json: /json/}, responseFields: {xml: "responseXML", text: "responseText", json: "responseJSON"}, converters: {"* text": String, "text html": !0, "text json": oe.parseJSON, "text xml": oe.parseXML}, flatOptions: {url: !0, context: !0}}, ajaxSetup: function(t, e) {
            return e ? B(B(t, oe.ajaxSettings), e) : B(oe.ajaxSettings, t)
        }, ajaxPrefilter: R($n), ajaxTransport: R(Vn), ajax: function(t, e) {
            function n(t, e, n, i) {
                var s, c, g, y, w, _ = e;
                2 !== b && (b = 2, a && clearTimeout(a), u = void 0, r = i || "", x.readyState = t > 0 ? 4 : 0, s = t >= 200 && 300 > t || 304 === t, n && (y = W(h, x, n)), y = z(h, y, x, s), s ? (h.ifModified && (w = x.getResponseHeader("Last-Modified"), w && (oe.lastModified[o] = w), w = x.getResponseHeader("etag"), w && (oe.etag[o] = w)), 204 === t || "HEAD" === h.type ? _ = "nocontent" : 304 === t ? _ = "notmodified" : (_ = y.state, c = y.data, g = y.error, s = !g)) : (g = _, (t || !_) && (_ = "error", 0 > t && (t = 0))), x.status = t, x.statusText = (e || _) + "", s ? f.resolveWith(d, [c, _, x]) : f.rejectWith(d, [x, _, g]), x.statusCode(v), v = void 0, l && p.trigger(s ? "ajaxSuccess" : "ajaxError", [x, h, s ? c : g]), m.fireWith(d, [x, _]), l && (p.trigger("ajaxComplete", [x, h]), --oe.active || oe.event.trigger("ajaxStop")))
            }
            "object" == typeof t && (e = t, t = void 0), e = e || {};
            var i, s, o, r, a, l, u, c, h = oe.ajaxSetup({}, e), d = h.context || h, p = h.context && (d.nodeType || d.jquery) ? oe(d) : oe.event, f = oe.Deferred(), m = oe.Callbacks("once memory"), v = h.statusCode || {}, g = {}, y = {}, b = 0, w = "canceled", x = {readyState: 0, getResponseHeader: function(t) {
                    var e;
                    if (2 === b) {
                        if (!c)
                            for (c = {}; e = Rn.exec(r); )
                                c[e[1].toLowerCase()] = e[2];
                        e = c[t.toLowerCase()]
                    }
                    return null == e ? null : e
                }, getAllResponseHeaders: function() {
                    return 2 === b ? r : null
                }, setRequestHeader: function(t, e) {
                    var n = t.toLowerCase();
                    return b || (t = y[n] = y[n] || t, g[t] = e), this
                }, overrideMimeType: function(t) {
                    return b || (h.mimeType = t), this
                }, statusCode: function(t) {
                    var e;
                    if (t)
                        if (2 > b)
                            for (e in t)
                                v[e] = [v[e], t[e]];
                        else
                            x.always(t[x.status]);
                    return this
                }, abort: function(t) {
                    var e = t || w;
                    return u && u.abort(e), n(0, e), this
                }};
            if (f.promise(x).complete = m.add, x.success = x.done, x.error = x.fail, h.url = ((t || h.url || Dn) + "").replace(qn, "").replace(Wn, Mn[1] + "//"), h.type = e.method || e.type || h.method || h.type, h.dataTypes = oe.trim(h.dataType || "*").toLowerCase().match(we) || [""], null == h.crossDomain && (i = zn.exec(h.url.toLowerCase()), h.crossDomain = !(!i || i[1] === Mn[1] && i[2] === Mn[2] && (i[3] || ("http:" === i[1] ? "80" : "443")) === (Mn[3] || ("http:" === Mn[1] ? "80" : "443")))), h.data && h.processData && "string" != typeof h.data && (h.data = oe.param(h.data, h.traditional)), I($n, h, e, x), 2 === b)
                return x;
            l = h.global, l && 0 === oe.active++ && oe.event.trigger("ajaxStart"), h.type = h.type.toUpperCase(), h.hasContent = !Bn.test(h.type), o = h.url, h.hasContent || (h.data && (o = h.url += (Hn.test(o) ? "&" : "?") + h.data, delete h.data), h.cache === !1 && (h.url = On.test(o) ? o.replace(On, "$1_=" + Ln++) : o + (Hn.test(o) ? "&" : "?") + "_=" + Ln++)), h.ifModified && (oe.lastModified[o] && x.setRequestHeader("If-Modified-Since", oe.lastModified[o]), oe.etag[o] && x.setRequestHeader("If-None-Match", oe.etag[o])), (h.data && h.hasContent && h.contentType !== !1 || e.contentType) && x.setRequestHeader("Content-Type", h.contentType), x.setRequestHeader("Accept", h.dataTypes[0] && h.accepts[h.dataTypes[0]] ? h.accepts[h.dataTypes[0]] + ("*" !== h.dataTypes[0] ? ", " + Un + "; q=0.01" : "") : h.accepts["*"]);
            for (s in h.headers)
                x.setRequestHeader(s, h.headers[s]);
            if (h.beforeSend && (h.beforeSend.call(d, x, h) === !1 || 2 === b))
                return x.abort();
            w = "abort";
            for (s in{success:1, error:1, complete:1})
                x[s](h[s]);
            if (u = I(Vn, h, e, x)) {
                x.readyState = 1, l && p.trigger("ajaxSend", [x, h]), h.async && h.timeout > 0 && (a = setTimeout(function() {
                    x.abort("timeout")
                }, h.timeout));
                try {
                    b = 1, u.send(g, n)
                } catch (_) {
                    if (!(2 > b))
                        throw _;
                    n(-1, _)
                }
            } else
                n(-1, "No Transport");
            return x
        }, getJSON: function(t, e, n) {
            return oe.get(t, e, n, "json")
        }, getScript: function(t, e) {
            return oe.get(t, void 0, e, "script")
        }}), oe.each(["get", "post"], function(t, e) {
        oe[e] = function(t, n, i, s) {
            return oe.isFunction(n) && (s = s || i, i = n, n = void 0), oe.ajax({url: t, type: e, dataType: s, data: n, success: i})
        }
    }), oe.each(["ajaxStart", "ajaxStop", "ajaxComplete", "ajaxError", "ajaxSuccess", "ajaxSend"], function(t, e) {
        oe.fn[e] = function(t) {
            return this.on(e, t)
        }
    }), oe._evalUrl = function(t) {
        return oe.ajax({url: t, type: "GET", dataType: "script", async: !1, global: !1, "throws": !0})
    }, oe.fn.extend({wrapAll: function(t) {
            if (oe.isFunction(t))
                return this.each(function(e) {
                    oe(this).wrapAll(t.call(this, e))
                });
            if (this[0]) {
                var e = oe(t, this[0].ownerDocument).eq(0).clone(!0);
                this[0].parentNode && e.insertBefore(this[0]), e.map(function() {
                    for (var t = this; t.firstChild && 1 === t.firstChild.nodeType; )
                        t = t.firstChild;
                    return t
                }).append(this)
            }
            return this
        }, wrapInner: function(t) {
            return this.each(oe.isFunction(t) ? function(e) {
                oe(this).wrapInner(t.call(this, e))
            } : function() {
                var e = oe(this), n = e.contents();
                n.length ? n.wrapAll(t) : e.append(t)
            })
        }, wrap: function(t) {
            var e = oe.isFunction(t);
            return this.each(function(n) {
                oe(this).wrapAll(e ? t.call(this, n) : t)
            })
        }, unwrap: function() {
            return this.parent().each(function() {
                oe.nodeName(this, "body") || oe(this).replaceWith(this.childNodes)
            }).end()
        }}), oe.expr.filters.hidden = function(t) {
        return t.offsetWidth <= 0 && t.offsetHeight <= 0 || !ie.reliableHiddenOffsets() && "none" === (t.style && t.style.display || oe.css(t, "display"))
    }, oe.expr.filters.visible = function(t) {
        return!oe.expr.filters.hidden(t)
    };
    var Yn = /%20/g, Gn = /\[\]$/, Qn = /\r?\n/g, Jn = /^(?:submit|button|image|reset|file)$/i, Kn = /^(?:input|select|textarea|keygen)/i;
    oe.param = function(t, e) {
        var n, i = [], s = function(t, e) {
            e = oe.isFunction(e) ? e() : null == e ? "" : e, i[i.length] = encodeURIComponent(t) + "=" + encodeURIComponent(e)
        };
        if (void 0 === e && (e = oe.ajaxSettings && oe.ajaxSettings.traditional), oe.isArray(t) || t.jquery && !oe.isPlainObject(t))
            oe.each(t, function() {
                s(this.name, this.value)
            });
        else
            for (n in t)
                $(n, t[n], e, s);
        return i.join("&").replace(Yn, "+")
    }, oe.fn.extend({serialize: function() {
            return oe.param(this.serializeArray())
        }, serializeArray: function() {
            return this.map(function() {
                var t = oe.prop(this, "elements");
                return t ? oe.makeArray(t) : this
            }).filter(function() {
                var t = this.type;
                return this.name && !oe(this).is(":disabled") && Kn.test(this.nodeName) && !Jn.test(t) && (this.checked || !Ne.test(t))
            }).map(function(t, e) {
                var n = oe(this).val();
                return null == n ? null : oe.isArray(n) ? oe.map(n, function(t) {
                    return{name: e.name, value: t.replace(Qn, "\r\n")}
                }) : {name: e.name, value: n.replace(Qn, "\r\n")}
            }).get()
        }}), oe.ajaxSettings.xhr = void 0 !== t.ActiveXObject ? function() {
        return!this.isLocal && /^(get|post|head|put|delete|options)$/i.test(this.type) && V() || U()
    } : V;
    var Zn = 0, ti = {}, ei = oe.ajaxSettings.xhr();
    t.ActiveXObject && oe(t).on("unload", function() {
        for (var t in ti)
            ti[t](void 0, !0)
    }), ie.cors = !!ei && "withCredentials"in ei, ei = ie.ajax = !!ei, ei && oe.ajaxTransport(function(t) {
        if (!t.crossDomain || ie.cors) {
            var e;
            return{send: function(n, i) {
                    var s, o = t.xhr(), r = ++Zn;
                    if (o.open(t.type, t.url, t.async, t.username, t.password), t.xhrFields)
                        for (s in t.xhrFields)
                            o[s] = t.xhrFields[s];
                    t.mimeType && o.overrideMimeType && o.overrideMimeType(t.mimeType), t.crossDomain || n["X-Requested-With"] || (n["X-Requested-With"] = "XMLHttpRequest");
                    for (s in n)
                        void 0 !== n[s] && o.setRequestHeader(s, n[s] + "");
                    o.send(t.hasContent && t.data || null), e = function(n, s) {
                        var a, l, u;
                        if (e && (s || 4 === o.readyState))
                            if (delete ti[r], e = void 0, o.onreadystatechange = oe.noop, s)
                                4 !== o.readyState && o.abort();
                            else {
                                u = {}, a = o.status, "string" == typeof o.responseText && (u.text = o.responseText);
                                try {
                                    l = o.statusText
                                } catch (c) {
                                    l = ""
                                }
                                a || !t.isLocal || t.crossDomain ? 1223 === a && (a = 204) : a = u.text ? 200 : 404
                            }
                        u && i(a, l, u, o.getAllResponseHeaders())
                    }, t.async ? 4 === o.readyState ? setTimeout(e) : o.onreadystatechange = ti[r] = e : e()
                }, abort: function() {
                    e && e(void 0, !0)
                }}
        }
    }), oe.ajaxSetup({accepts: {script: "text/javascript, application/javascript, application/ecmascript, application/x-ecmascript"}, contents: {script: /(?:java|ecma)script/}, converters: {"text script": function(t) {
                return oe.globalEval(t), t
            }}}), oe.ajaxPrefilter("script", function(t) {
        void 0 === t.cache && (t.cache = !1), t.crossDomain && (t.type = "GET", t.global = !1)
    }), oe.ajaxTransport("script", function(t) {
        if (t.crossDomain) {
            var e, n = me.head || oe("head")[0] || me.documentElement;
            return{send: function(i, s) {
                    e = me.createElement("script"), e.async = !0, t.scriptCharset && (e.charset = t.scriptCharset), e.src = t.url, e.onload = e.onreadystatechange = function(t, n) {
                        (n || !e.readyState || /loaded|complete/.test(e.readyState)) && (e.onload = e.onreadystatechange = null, e.parentNode && e.parentNode.removeChild(e), e = null, n || s(200, "success"))
                    }, n.insertBefore(e, n.firstChild)
                }, abort: function() {
                    e && e.onload(void 0, !0)
                }}
        }
    });
    var ni = [], ii = /(=)\?(?=&|$)|\?\?/;
    oe.ajaxSetup({jsonp: "callback", jsonpCallback: function() {
            var t = ni.pop() || oe.expando + "_" + Ln++;
            return this[t] = !0, t
        }}), oe.ajaxPrefilter("json jsonp", function(e, n, i) {
        var s, o, r, a = e.jsonp !== !1 && (ii.test(e.url) ? "url" : "string" == typeof e.data && !(e.contentType || "").indexOf("application/x-www-form-urlencoded") && ii.test(e.data) && "data");
        return a || "jsonp" === e.dataTypes[0] ? (s = e.jsonpCallback = oe.isFunction(e.jsonpCallback) ? e.jsonpCallback() : e.jsonpCallback, a ? e[a] = e[a].replace(ii, "$1" + s) : e.jsonp !== !1 && (e.url += (Hn.test(e.url) ? "&" : "?") + e.jsonp + "=" + s), e.converters["script json"] = function() {
            return r || oe.error(s + " was not called"), r[0]
        }, e.dataTypes[0] = "json", o = t[s], t[s] = function() {
            r = arguments
        }, i.always(function() {
            t[s] = o, e[s] && (e.jsonpCallback = n.jsonpCallback, ni.push(s)), r && oe.isFunction(o) && o(r[0]), r = o = void 0
        }), "script") : void 0
    }), oe.parseHTML = function(t, e, n) {
        if (!t || "string" != typeof t)
            return null;
        "boolean" == typeof e && (n = e, e = !1), e = e || me;
        var i = de.exec(t), s = !n && [];
        return i ? [e.createElement(i[1])] : (i = oe.buildFragment([t], e, s), s && s.length && oe(s).remove(), oe.merge([], i.childNodes))
    };
    var si = oe.fn.load;
    oe.fn.load = function(t, e, n) {
        if ("string" != typeof t && si)
            return si.apply(this, arguments);
        var i, s, o, r = this, a = t.indexOf(" ");
        return a >= 0 && (i = t.slice(a, t.length), t = t.slice(0, a)), oe.isFunction(e) ? (n = e, e = void 0) : e && "object" == typeof e && (o = "POST"), r.length > 0 && oe.ajax({url: t, type: o, dataType: "html", data: e}).done(function(t) {
            s = arguments, r.html(i ? oe("<div>").append(oe.parseHTML(t)).find(i) : t)
        }).complete(n && function(t, e) {
            r.each(n, s || [t.responseText, e, t])
        }), this
    }, oe.expr.filters.animated = function(t) {
        return oe.grep(oe.timers, function(e) {
            return t === e.elem
        }).length
    };
    var oi = t.document.documentElement;
    oe.offset = {setOffset: function(t, e, n) {
            var i, s, o, r, a, l, u, c = oe.css(t, "position"), h = oe(t), d = {};
            "static" === c && (t.style.position = "relative"), a = h.offset(), o = oe.css(t, "top"), l = oe.css(t, "left"), u = ("absolute" === c || "fixed" === c) && oe.inArray("auto", [o, l]) > -1, u ? (i = h.position(), r = i.top, s = i.left) : (r = parseFloat(o) || 0, s = parseFloat(l) || 0), oe.isFunction(e) && (e = e.call(t, n, a)), null != e.top && (d.top = e.top - a.top + r), null != e.left && (d.left = e.left - a.left + s), "using"in e ? e.using.call(t, d) : h.css(d)
        }}, oe.fn.extend({offset: function(t) {
            if (arguments.length)
                return void 0 === t ? this : this.each(function(e) {
                    oe.offset.setOffset(this, t, e)
                });
            var e, n, i = {top: 0, left: 0}, s = this[0], o = s && s.ownerDocument;
            return o ? (e = o.documentElement, oe.contains(e, s) ? (typeof s.getBoundingClientRect !== ke && (i = s.getBoundingClientRect()), n = X(o), {top: i.top + (n.pageYOffset || e.scrollTop) - (e.clientTop || 0), left: i.left + (n.pageXOffset || e.scrollLeft) - (e.clientLeft || 0)}) : i) : void 0
        }, position: function() {
            if (this[0]) {
                var t, e, n = {top: 0, left: 0}, i = this[0];
                return"fixed" === oe.css(i, "position") ? e = i.getBoundingClientRect() : (t = this.offsetParent(), e = this.offset(), oe.nodeName(t[0], "html") || (n = t.offset()), n.top += oe.css(t[0], "borderTopWidth", !0), n.left += oe.css(t[0], "borderLeftWidth", !0)), {top: e.top - n.top - oe.css(i, "marginTop", !0), left: e.left - n.left - oe.css(i, "marginLeft", !0)}
            }
        }, offsetParent: function() {
            return this.map(function() {
                for (var t = this.offsetParent || oi; t && !oe.nodeName(t, "html") && "static" === oe.css(t, "position"); )
                    t = t.offsetParent;
                return t || oi
            })
        }}), oe.each({scrollLeft: "pageXOffset", scrollTop: "pageYOffset"}, function(t, e) {
        var n = /Y/.test(e);
        oe.fn[t] = function(i) {
            return Ae(this, function(t, i, s) {
                var o = X(t);
                return void 0 === s ? o ? e in o ? o[e] : o.document.documentElement[i] : t[i] : void(o ? o.scrollTo(n ? oe(o).scrollLeft() : s, n ? s : oe(o).scrollTop()) : t[i] = s)
            }, t, i, arguments.length, null)
        }
    }), oe.each(["top", "left"], function(t, e) {
        oe.cssHooks[e] = j(ie.pixelPosition, function(t, n) {
            return n ? (n = nn(t, e), on.test(n) ? oe(t).position()[e] + "px" : n) : void 0
        })
    }), oe.each({Height: "height", Width: "width"}, function(t, e) {
        oe.each({padding: "inner" + t, content: e, "": "outer" + t}, function(n, i) {
            oe.fn[i] = function(i, s) {
                var o = arguments.length && (n || "boolean" != typeof i), r = n || (i === !0 || s === !0 ? "margin" : "border");
                return Ae(this, function(e, n, i) {
                    var s;
                    return oe.isWindow(e) ? e.document.documentElement["client" + t] : 9 === e.nodeType ? (s = e.documentElement, Math.max(e.body["scroll" + t], s["scroll" + t], e.body["offset" + t], s["offset" + t], s["client" + t])) : void 0 === i ? oe.css(e, n, r) : oe.style(e, n, i, r)
                }, e, o ? i : void 0, o, null)
            }
        })
    }), oe.fn.size = function() {
        return this.length
    }, oe.fn.andSelf = oe.fn.addBack, "function" == typeof define && define.amd && define("jquery", [], function() {
        return oe
    });
    var ri = t.jQuery, ai = t.$;
    return oe.noConflict = function(e) {
        return t.$ === oe && (t.$ = ai), e && t.jQuery === oe && (t.jQuery = ri), oe
    }, typeof e === ke && (t.jQuery = t.$ = oe), oe
}), /*! Lazy Load 1.9.3 - MIT license - Copyright 2010-2013 Mika Tuupola */
        !function(t, e, n, i) {
    var s = t(e);
    t.fn.lazyload = function(o) {
        function r() {
            var e = 0;
            l.each(function() {
                var n = t(this);
                if (!u.skip_invisible || n.is(":visible"))
                    if (t.abovethetop(this, u) || t.leftofbegin(this, u))
                        ;
                    else if (t.belowthefold(this, u) || t.rightoffold(this, u)) {
                        if (++e > u.failure_limit)
                            return!1
                    } else
                        n.trigger("appear"), e = 0
            })
        }
        var a, l = this, u = {threshold: 0, failure_limit: 0, event: "scroll", effect: "show", container: e, data_attribute: "original", skip_invisible: !0, appear: null, load: null, placeholder: "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsQAAA7EAZUrDhsAAAANSURBVBhXYzh8+PB/AAffA0nNPuCLAAAAAElFTkSuQmCC"};
        return o && (i !== o.failurelimit && (o.failure_limit = o.failurelimit, delete o.failurelimit), i !== o.effectspeed && (o.effect_speed = o.effectspeed, delete o.effectspeed), t.extend(u, o)), a = u.container === i || u.container === e ? s : t(u.container), 0 === u.event.indexOf("scroll") && a.bind(u.event, function() {
            return r()
        }), this.each(function() {
            var e = this, n = t(e);
            e.loaded = !1, (n.attr("src") === i || n.attr("src") === !1) && n.is("img") && n.attr("src", u.placeholder), n.one("appear", function() {
                if (!this.loaded) {
                    if (u.appear) {
                        var i = l.length;
                        u.appear.call(e, i, u)
                    }
                    t("<img />").bind("load", function() {
                        var i = n.attr("data-" + u.data_attribute);
                        n.hide(), n.is("img") ? n.attr("src", i) : n.css("background-image", "url('" + i + "')"), n[u.effect](u.effect_speed), e.loaded = !0;
                        var s = t.grep(l, function(t) {
                            return!t.loaded
                        });
                        if (l = t(s), u.load) {
                            var o = l.length;
                            u.load.call(e, o, u)
                        }
                    }).attr("src", n.attr("data-" + u.data_attribute))
                }
            }), 0 !== u.event.indexOf("scroll") && n.bind(u.event, function() {
                e.loaded || n.trigger("appear")
            })
        }), s.bind("resize", function() {
            r()
        }), /(?:iphone|ipod|ipad).*os 5/gi.test(navigator.appVersion) && s.bind("pageshow", function(e) {
            e.originalEvent && e.originalEvent.persisted && l.each(function() {
                t(this).trigger("appear")
            })
        }), t(n).ready(function() {
            r()
        }), this
    }, t.belowthefold = function(n, o) {
        var r;
        return r = o.container === i || o.container === e ? (e.innerHeight ? e.innerHeight : s.height()) + s.scrollTop() : t(o.container).offset().top + t(o.container).height(), r <= t(n).offset().top - o.threshold
    }, t.rightoffold = function(n, o) {
        var r;
        return r = o.container === i || o.container === e ? s.width() + s.scrollLeft() : t(o.container).offset().left + t(o.container).width(), r <= t(n).offset().left - o.threshold
    }, t.abovethetop = function(n, o) {
        var r;
        return r = o.container === i || o.container === e ? s.scrollTop() : t(o.container).offset().top, r >= t(n).offset().top + o.threshold + t(n).height()
    }, t.leftofbegin = function(n, o) {
        var r;
        return r = o.container === i || o.container === e ? s.scrollLeft() : t(o.container).offset().left, r >= t(n).offset().left + o.threshold + t(n).width()
    }, t.inviewport = function(e, n) {
        return!(t.rightoffold(e, n) || t.leftofbegin(e, n) || t.belowthefold(e, n) || t.abovethetop(e, n))
    }, t.extend(t.expr[":"], {"below-the-fold": function(e) {
            return t.belowthefold(e, {threshold: 0})
        }, "above-the-top": function(e) {
            return!t.belowthefold(e, {threshold: 0})
        }, "right-of-screen": function(e) {
            return t.rightoffold(e, {threshold: 0})
        }, "left-of-screen": function(e) {
            return!t.rightoffold(e, {threshold: 0})
        }, "in-viewport": function(e) {
            return t.inviewport(e, {threshold: 0})
        }, "above-the-fold": function(e) {
            return!t.belowthefold(e, {threshold: 0})
        }, "right-of-fold": function(e) {
            return t.rightoffold(e, {threshold: 0})
        }, "left-of-fold": function(e) {
            return!t.rightoffold(e, {threshold: 0})
        }})
}(jQuery, window, document), function() {
    function t() {
    }
    function e(t, e) {
        for (var n = t.length; n--; )
            if (t[n].listener === e)
                return n;
        return-1
    }
    function n(t) {
        return function() {
            return this[t].apply(this, arguments)
        }
    }
    var i = t.prototype, s = this, o = s.EventEmitter;
    i.getListeners = function(t) {
        var e, n, i = this._getEvents();
        if ("object" == typeof t) {
            e = {};
            for (n in i)
                i.hasOwnProperty(n) && t.test(n) && (e[n] = i[n])
        } else
            e = i[t] || (i[t] = []);
        return e
    }, i.flattenListeners = function(t) {
        var e, n = [];
        for (e = 0; t.length > e; e += 1)
            n.push(t[e].listener);
        return n
    }, i.getListenersAsObject = function(t) {
        var e, n = this.getListeners(t);
        return n instanceof Array && (e = {}, e[t] = n), e || n
    }, i.addListener = function(t, n) {
        var i, s = this.getListenersAsObject(t), o = "object" == typeof n;
        for (i in s)
            s.hasOwnProperty(i) && -1 === e(s[i], n) && s[i].push(o ? n : {listener: n, once: !1});
        return this
    }, i.on = n("addListener"), i.addOnceListener = function(t, e) {
        return this.addListener(t, {listener: e, once: !0})
    }, i.once = n("addOnceListener"), i.defineEvent = function(t) {
        return this.getListeners(t), this
    }, i.defineEvents = function(t) {
        for (var e = 0; t.length > e; e += 1)
            this.defineEvent(t[e]);
        return this
    }, i.removeListener = function(t, n) {
        var i, s, o = this.getListenersAsObject(t);
        for (s in o)
            o.hasOwnProperty(s) && (i = e(o[s], n), -1 !== i && o[s].splice(i, 1));
        return this
    }, i.off = n("removeListener"), i.addListeners = function(t, e) {
        return this.manipulateListeners(!1, t, e)
    }, i.removeListeners = function(t, e) {
        return this.manipulateListeners(!0, t, e)
    }, i.manipulateListeners = function(t, e, n) {
        var i, s, o = t ? this.removeListener : this.addListener, r = t ? this.removeListeners : this.addListeners;
        if ("object" != typeof e || e instanceof RegExp)
            for (i = n.length; i--; )
                o.call(this, e, n[i]);
        else
            for (i in e)
                e.hasOwnProperty(i) && (s = e[i]) && ("function" == typeof s ? o.call(this, i, s) : r.call(this, i, s));
        return this
    }, i.removeEvent = function(t) {
        var e, n = typeof t, i = this._getEvents();
        if ("string" === n)
            delete i[t];
        else if ("object" === n)
            for (e in i)
                i.hasOwnProperty(e) && t.test(e) && delete i[e];
        else
            delete this._events;
        return this
    }, i.removeAllListeners = n("removeEvent"), i.emitEvent = function(t, e) {
        var n, i, s, o, r = this.getListenersAsObject(t);
        for (s in r)
            if (r.hasOwnProperty(s))
                for (i = r[s].length; i--; )
                    n = r[s][i], n.once === !0 && this.removeListener(t, n.listener), o = n.listener.apply(this, e || []), o === this._getOnceReturnValue() && this.removeListener(t, n.listener);
        return this
    }, i.trigger = n("emitEvent"), i.emit = function(t) {
        var e = Array.prototype.slice.call(arguments, 1);
        return this.emitEvent(t, e)
    }, i.setOnceReturnValue = function(t) {
        return this._onceReturnValue = t, this
    }, i._getOnceReturnValue = function() {
        return this.hasOwnProperty("_onceReturnValue") ? this._onceReturnValue : !0
    }, i._getEvents = function() {
        return this._events || (this._events = {})
    }, t.noConflict = function() {
        return s.EventEmitter = o, t
    }, "function" == typeof define && define.amd ? define("eventEmitter/EventEmitter", [], function() {
        return t
    }) : "object" == typeof module && module.exports ? module.exports = t : this.EventEmitter = t
}.call(this), function(t) {
    function e(e) {
        var n = t.event;
        return n.target = n.target || n.srcElement || e, n
    }
    var n = document.documentElement, i = function() {
    };
    n.addEventListener ? i = function(t, e, n) {
        t.addEventListener(e, n, !1)
    } : n.attachEvent && (i = function(t, n, i) {
        t[n + i] = i.handleEvent ? function() {
            var n = e(t);
            i.handleEvent.call(i, n)
        } : function() {
            var n = e(t);
            i.call(t, n)
        }, t.attachEvent("on" + n, t[n + i])
    });
    var s = function() {
    };
    n.removeEventListener ? s = function(t, e, n) {
        t.removeEventListener(e, n, !1)
    } : n.detachEvent && (s = function(t, e, n) {
        t.detachEvent("on" + e, t[e + n]);
        try {
            delete t[e + n]
        } catch (i) {
            t[e + n] = void 0
        }
    });
    var o = {bind: i, unbind: s};
    "function" == typeof define && define.amd ? define("eventie/eventie", o) : t.eventie = o
}(this), function(t, e) {
    "function" == typeof define && define.amd ? define(["eventEmitter/EventEmitter", "eventie/eventie"], function(n, i) {
        return e(t, n, i)
    }) : "object" == typeof exports ? module.exports = e(t, require("eventEmitter"), require("eventie")) : t.imagesLoaded = e(t, t.EventEmitter, t.eventie)
}(this, function(t, e, n) {
    function i(t, e) {
        for (var n in e)
            t[n] = e[n];
        return t
    }
    function s(t) {
        return"[object Array]" === d.call(t)
    }
    function o(t) {
        var e = [];
        if (s(t))
            e = t;
        else if ("number" == typeof t.length)
            for (var n = 0, i = t.length; i > n; n++)
                e.push(t[n]);
        else
            e.push(t);
        return e
    }
    function r(t, e, n) {
        if (!(this instanceof r))
            return new r(t, e);
        "string" == typeof t && (t = document.querySelectorAll(t)), this.elements = o(t), this.options = i({}, this.options), "function" == typeof e ? n = e : i(this.options, e), n && this.on("always", n), this.getImages(), u && (this.jqDeferred = new u.Deferred);
        var s = this;
        setTimeout(function() {
            s.check()
        })
    }
    function a(t) {
        this.img = t
    }
    function l(t) {
        this.src = t, p[t] = this
    }
    var u = t.jQuery, c = t.console, h = void 0 !== c, d = Object.prototype.toString;
    r.prototype = new e, r.prototype.options = {}, r.prototype.getImages = function() {
        this.images = [];
        for (var t = 0, e = this.elements.length; e > t; t++) {
            var n = this.elements[t];
            "IMG" === n.nodeName && this.addImage(n);
            var i = n.nodeType;
            if (i && (1 === i || 9 === i || 11 === i))
                for (var s = n.querySelectorAll("img"), o = 0, r = s.length; r > o; o++) {
                    var a = s[o];
                    this.addImage(a)
                }
        }
    }, r.prototype.addImage = function(t) {
        var e = new a(t);
        this.images.push(e)
    }, r.prototype.check = function() {
        function t(t, s) {
            return e.options.debug && h && c.log("confirm", t, s), e.progress(t), n++, n === i && e.complete(), !0
        }
        var e = this, n = 0, i = this.images.length;
        if (this.hasAnyBroken = !1, !i)
            return this.complete(), void 0;
        for (var s = 0; i > s; s++) {
            var o = this.images[s];
            o.on("confirm", t), o.check()
        }
    }, r.prototype.progress = function(t) {
        this.hasAnyBroken = this.hasAnyBroken || !t.isLoaded;
        var e = this;
        setTimeout(function() {
            e.emit("progress", e, t), e.jqDeferred && e.jqDeferred.notify && e.jqDeferred.notify(e, t)
        })
    }, r.prototype.complete = function() {
        var t = this.hasAnyBroken ? "fail" : "done";
        this.isComplete = !0;
        var e = this;
        setTimeout(function() {
            if (e.emit(t, e), e.emit("always", e), e.jqDeferred) {
                var n = e.hasAnyBroken ? "reject" : "resolve";
                e.jqDeferred[n](e)
            }
        })
    }, u && (u.fn.imagesLoaded = function(t, e) {
        var n = new r(this, t, e);
        return n.jqDeferred.promise(u(this))
    }), a.prototype = new e, a.prototype.check = function() {
        var t = p[this.img.src] || new l(this.img.src);
        if (t.isConfirmed)
            return this.confirm(t.isLoaded, "cached was confirmed"), void 0;
        if (this.img.complete && void 0 !== this.img.naturalWidth)
            return this.confirm(0 !== this.img.naturalWidth, "naturalWidth"), void 0;
        var e = this;
        t.on("confirm", function(t, n) {
            return e.confirm(t.isLoaded, n), !0
        }), t.check()
    }, a.prototype.confirm = function(t, e) {
        this.isLoaded = t, this.emit("confirm", this, e)
    };
    var p = {};
    return l.prototype = new e, l.prototype.check = function() {
        if (!this.isChecked) {
            var t = new Image;
            n.bind(t, "load", this), n.bind(t, "error", this), t.src = this.src, this.isChecked = !0
        }
    }, l.prototype.handleEvent = function(t) {
        var e = "on" + t.type;
        this[e] && this[e](t)
    }, l.prototype.onload = function(t) {
        this.confirm(!0, "onload"), this.unbindProxyEvents(t)
    }, l.prototype.onerror = function(t) {
        this.confirm(!1, "onerror"), this.unbindProxyEvents(t)
    }, l.prototype.confirm = function(t, e) {
        this.isConfirmed = !0, this.isLoaded = t, this.emit("confirm", this, e)
    }, l.prototype.unbindProxyEvents = function(t) {
        n.unbind(t.target, "load", this), n.unbind(t.target, "error", this)
    }, r
}), "undefined" == typeof jQuery)
    throw new Error("Bootstrap's JavaScript requires jQuery");
+function(t) {
    "use strict";
    function e() {
        var t = document.createElement("bootstrap"), e = {WebkitTransition: "webkitTransitionEnd", MozTransition: "transitionend", OTransition: "oTransitionEnd otransitionend", transition: "transitionend"};
        for (var n in e)
            if (void 0 !== t.style[n])
                return{end: e[n]};
        return!1
    }
    t.fn.emulateTransitionEnd = function(e) {
        var n = !1, i = this;
        t(this).one(t.support.transition.end, function() {
            n = !0
        });
        var s = function() {
            n || t(i).trigger(t.support.transition.end)
        };
        return setTimeout(s, e), this
    }, t(function() {
        t.support.transition = e()
    })
}(jQuery), +function(t) {
    "use strict";
    var e = '[data-dismiss="alert"]', n = function(n) {
        t(n).on("click", e, this.close)
    };
    n.prototype.close = function(e) {
        function n() {
            o.trigger("closed.bs.alert").remove()
        }
        var i = t(this), s = i.attr("data-target");
        s || (s = i.attr("href"), s = s && s.replace(/.*(?=#[^\s]*$)/, ""));
        var o = t(s);
        e && e.preventDefault(), o.length || (o = i.hasClass("alert") ? i : i.parent()), o.trigger(e = t.Event("close.bs.alert")), e.isDefaultPrevented() || (o.removeClass("in"), t.support.transition && o.hasClass("fade") ? o.one(t.support.transition.end, n).emulateTransitionEnd(150) : n())
    };
    var i = t.fn.alert;
    t.fn.alert = function(e) {
        return this.each(function() {
            var i = t(this), s = i.data("bs.alert");
            s || i.data("bs.alert", s = new n(this)), "string" == typeof e && s[e].call(i)
        })
    }, t.fn.alert.Constructor = n, t.fn.alert.noConflict = function() {
        return t.fn.alert = i, this
    }, t(document).on("click.bs.alert.data-api", e, n.prototype.close)
}(jQuery), +function(t) {
    "use strict";
    var e = function(n, i) {
        this.$element = t(n), this.options = t.extend({}, e.DEFAULTS, i), this.isLoading = !1
    };
    e.DEFAULTS = {loadingText: "loading..."}, e.prototype.setState = function(e) {
        var n = "disabled", i = this.$element, s = i.is("input") ? "val" : "html", o = i.data();
        e += "Text", o.resetText || i.data("resetText", i[s]()), i[s](o[e] || this.options[e]), setTimeout(t.proxy(function() {
            "loadingText" == e ? (this.isLoading = !0, i.addClass(n).attr(n, n)) : this.isLoading && (this.isLoading = !1, i.removeClass(n).removeAttr(n))
        }, this), 0)
    }, e.prototype.toggle = function() {
        var t = !0, e = this.$element.closest('[data-toggle="buttons"]');
        if (e.length) {
            var n = this.$element.find("input");
            "radio" == n.prop("type") && (n.prop("checked") && this.$element.hasClass("active") ? t = !1 : e.find(".active").removeClass("active")), t && n.prop("checked", !this.$element.hasClass("active")).trigger("change")
        }
        t && this.$element.toggleClass("active")
    };
    var n = t.fn.button;
    t.fn.button = function(n) {
        return this.each(function() {
            var i = t(this), s = i.data("bs.button"), o = "object" == typeof n && n;
            s || i.data("bs.button", s = new e(this, o)), "toggle" == n ? s.toggle() : n && s.setState(n)
        })
    }, t.fn.button.Constructor = e, t.fn.button.noConflict = function() {
        return t.fn.button = n, this
    }, t(document).on("click.bs.button.data-api", "[data-toggle^=button]", function(e) {
        var n = t(e.target);
        n.hasClass("btn") || (n = n.closest(".btn")), n.button("toggle"), e.preventDefault()
    })
}(jQuery), +function(t) {
    "use strict";
    var e = function(e, n) {
        this.$element = t(e), this.$indicators = this.$element.find(".carousel-indicators"), this.options = n, this.paused = this.sliding = this.interval = this.$active = this.$items = null, "hover" == this.options.pause && this.$element.on("mouseenter", t.proxy(this.pause, this)).on("mouseleave", t.proxy(this.cycle, this))
    };
    e.DEFAULTS = {interval: 5e3, pause: "hover", wrap: !0}, e.prototype.cycle = function(e) {
        return e || (this.paused = !1), this.interval && clearInterval(this.interval), this.options.interval && !this.paused && (this.interval = setInterval(t.proxy(this.next, this), this.options.interval)), this
    }, e.prototype.getActiveIndex = function() {
        return this.$active = this.$element.find(".item.active"), this.$items = this.$active.parent().children(), this.$items.index(this.$active)
    }, e.prototype.to = function(e) {
        var n = this, i = this.getActiveIndex();
        return e > this.$items.length - 1 || 0 > e ? void 0 : this.sliding ? this.$element.one("slid.bs.carousel", function() {
            n.to(e)
        }) : i == e ? this.pause().cycle() : this.slide(e > i ? "next" : "prev", t(this.$items[e]))
    }, e.prototype.pause = function(e) {
        return e || (this.paused = !0), this.$element.find(".next, .prev").length && t.support.transition && (this.$element.trigger(t.support.transition.end), this.cycle(!0)), this.interval = clearInterval(this.interval), this
    }, e.prototype.next = function() {
        return this.sliding ? void 0 : this.slide("next")
    }, e.prototype.prev = function() {
        return this.sliding ? void 0 : this.slide("prev")
    }, e.prototype.slide = function(e, n) {
        var i = this.$element.find(".item.active"), s = n || i[e](), o = this.interval, r = "next" == e ? "left" : "right", a = "next" == e ? "first" : "last", l = this;
        if (!s.length) {
            if (!this.options.wrap)
                return;
            s = this.$element.find(".item")[a]()
        }
        if (s.hasClass("active"))
            return this.sliding = !1;
        var u = t.Event("slide.bs.carousel", {relatedTarget: s[0], direction: r});
        return this.$element.trigger(u), u.isDefaultPrevented() ? void 0 : (this.sliding = !0, o && this.pause(), this.$indicators.length && (this.$indicators.find(".active").removeClass("active"), this.$element.one("slid.bs.carousel", function() {
            var e = t(l.$indicators.children()[l.getActiveIndex()]);
            e && e.addClass("active")
        })), t.support.transition && this.$element.hasClass("slide") ? (s.addClass(e), s[0].offsetWidth, i.addClass(r), s.addClass(r), i.one(t.support.transition.end, function() {
            s.removeClass([e, r].join(" ")).addClass("active"), i.removeClass(["active", r].join(" ")), l.sliding = !1, setTimeout(function() {
                l.$element.trigger("slid.bs.carousel")
            }, 0)
        }).emulateTransitionEnd(1e3 * i.css("transition-duration").slice(0, -1))) : (i.removeClass("active"), s.addClass("active"), this.sliding = !1, this.$element.trigger("slid.bs.carousel")), o && this.cycle(), this)
    };
    var n = t.fn.carousel;
    t.fn.carousel = function(n) {
        return this.each(function() {
            var i = t(this), s = i.data("bs.carousel"), o = t.extend({}, e.DEFAULTS, i.data(), "object" == typeof n && n), r = "string" == typeof n ? n : o.slide;
            s || i.data("bs.carousel", s = new e(this, o)), "number" == typeof n ? s.to(n) : r ? s[r]() : o.interval && s.pause().cycle()
        })
    }, t.fn.carousel.Constructor = e, t.fn.carousel.noConflict = function() {
        return t.fn.carousel = n, this
    }, t(document).on("click.bs.carousel.data-api", "[data-slide], [data-slide-to]", function(e) {
        var n, i = t(this), s = t(i.attr("data-target") || (n = i.attr("href")) && n.replace(/.*(?=#[^\s]+$)/, "")), o = t.extend({}, s.data(), i.data()), r = i.attr("data-slide-to");
        r && (o.interval = !1), s.carousel(o), (r = i.attr("data-slide-to")) && s.data("bs.carousel").to(r), e.preventDefault()
    }), t(window).on("load", function() {
        t('[data-ride="carousel"]').each(function() {
            var e = t(this);
            e.carousel(e.data())
        })
    })
}(jQuery), +function(t) {
    "use strict";
    var e = function(n, i) {
        this.$element = t(n), this.options = t.extend({}, e.DEFAULTS, i), this.transitioning = null, this.options.parent && (this.$parent = t(this.options.parent)), this.options.toggle && this.toggle()
    };
    e.DEFAULTS = {toggle: !0}, e.prototype.dimension = function() {
        var t = this.$element.hasClass("width");
        return t ? "width" : "height"
    }, e.prototype.show = function() {
        if (!this.transitioning && !this.$element.hasClass("in")) {
            var e = t.Event("show.bs.collapse");
            if (this.$element.trigger(e), !e.isDefaultPrevented()) {
                var n = this.$parent && this.$parent.find("> .panel > .in");
                if (n && n.length) {
                    var i = n.data("bs.collapse");
                    if (i && i.transitioning)
                        return;
                    n.collapse("hide"), i || n.data("bs.collapse", null)
                }
                var s = this.dimension();
                this.$element.removeClass("collapse").addClass("collapsing")[s](0), this.transitioning = 1;
                var o = function() {
                    this.$element.removeClass("collapsing").addClass("collapse in")[s]("auto"), this.transitioning = 0, this.$element.trigger("shown.bs.collapse")
                };
                if (!t.support.transition)
                    return o.call(this);
                var r = t.camelCase(["scroll", s].join("-"));
                this.$element.one(t.support.transition.end, t.proxy(o, this)).emulateTransitionEnd(350)[s](this.$element[0][r])
            }
        }
    }, e.prototype.hide = function() {
        if (!this.transitioning && this.$element.hasClass("in")) {
            var e = t.Event("hide.bs.collapse");
            if (this.$element.trigger(e), !e.isDefaultPrevented()) {
                var n = this.dimension();
                this.$element[n](this.$element[n]())[0].offsetHeight, this.$element.addClass("collapsing").removeClass("collapse").removeClass("in"), this.transitioning = 1;
                var i = function() {
                    this.transitioning = 0, this.$element.trigger("hidden.bs.collapse").removeClass("collapsing").addClass("collapse")
                };
                return t.support.transition ? void this.$element[n](0).one(t.support.transition.end, t.proxy(i, this)).emulateTransitionEnd(350) : i.call(this)
            }
        }
    }, e.prototype.toggle = function() {
        this[this.$element.hasClass("in") ? "hide" : "show"]()
    };
    var n = t.fn.collapse;
    t.fn.collapse = function(n) {
        return this.each(function() {
            var i = t(this), s = i.data("bs.collapse"), o = t.extend({}, e.DEFAULTS, i.data(), "object" == typeof n && n);
            !s && o.toggle && "show" == n && (n = !n), s || i.data("bs.collapse", s = new e(this, o)), "string" == typeof n && s[n]()
        })
    }, t.fn.collapse.Constructor = e, t.fn.collapse.noConflict = function() {
        return t.fn.collapse = n, this
    }, t(document).on("click.bs.collapse.data-api", "[data-toggle=collapse]", function(e) {
        var n, i = t(this), s = i.attr("data-target") || e.preventDefault() || (n = i.attr("href")) && n.replace(/.*(?=#[^\s]+$)/, ""), o = t(s), r = o.data("bs.collapse"), a = r ? "toggle" : i.data(), l = i.attr("data-parent"), u = l && t(l);
        r && r.transitioning || (u && u.find('[data-toggle=collapse][data-parent="' + l + '"]').not(i).addClass("collapsed"), i[o.hasClass("in") ? "addClass" : "removeClass"]("collapsed")), o.collapse(a)
    })
}(jQuery), +function(t) {
    "use strict";
    function e(e) {
        t(i).remove(), t(s).each(function() {
            var i = n(t(this)), s = {relatedTarget: this};
            i.hasClass("open") && (i.trigger(e = t.Event("hide.bs.dropdown", s)), e.isDefaultPrevented() || i.removeClass("open").trigger("hidden.bs.dropdown", s))
        })
    }
    function n(e) {
        var n = e.attr("data-target");
        n || (n = e.attr("href"), n = n && /#[A-Za-z]/.test(n) && n.replace(/.*(?=#[^\s]*$)/, ""));
        var i = n && t(n);
        return i && i.length ? i : e.parent()
    }
    var i = ".dropdown-backdrop", s = "[data-toggle=dropdown]", o = function(e) {
        t(e).on("click.bs.dropdown", this.toggle)
    };
    o.prototype.toggle = function(i) {
        var s = t(this);
        if (!s.is(".disabled, :disabled")) {
            var o = n(s), r = o.hasClass("open");
            if (e(), !r) {
                "ontouchstart"in document.documentElement && !o.closest(".navbar-nav").length && t('<div class="dropdown-backdrop"/>').insertAfter(t(this)).on("click", e);
                var a = {relatedTarget: this};
                if (o.trigger(i = t.Event("show.bs.dropdown", a)), i.isDefaultPrevented())
                    return;
                o.toggleClass("open").trigger("shown.bs.dropdown", a), s.focus()
            }
            return!1
        }
    }, o.prototype.keydown = function(e) {
        if (/(38|40|27)/.test(e.keyCode)) {
            var i = t(this);
            if (e.preventDefault(), e.stopPropagation(), !i.is(".disabled, :disabled")) {
                var o = n(i), r = o.hasClass("open");
                if (!r || r && 27 == e.keyCode)
                    return 27 == e.which && o.find(s).focus(), i.click();
                var a = " li:not(.divider):visible a", l = o.find("[role=menu]" + a + ", [role=listbox]" + a);
                if (l.length) {
                    var u = l.index(l.filter(":focus"));
                    38 == e.keyCode && u > 0 && u--, 40 == e.keyCode && u < l.length - 1 && u++, ~u || (u = 0), l.eq(u).focus()
                }
            }
        }
    };
    var r = t.fn.dropdown;
    t.fn.dropdown = function(e) {
        return this.each(function() {
            var n = t(this), i = n.data("bs.dropdown");
            i || n.data("bs.dropdown", i = new o(this)), "string" == typeof e && i[e].call(n)
        })
    }, t.fn.dropdown.Constructor = o, t.fn.dropdown.noConflict = function() {
        return t.fn.dropdown = r, this
    }, t(document).on("click.bs.dropdown.data-api", e).on("click.bs.dropdown.data-api", ".dropdown form", function(t) {
        t.stopPropagation()
    }).on("click.bs.dropdown.data-api", s, o.prototype.toggle).on("keydown.bs.dropdown.data-api", s + ", [role=menu], [role=listbox]", o.prototype.keydown)
}(jQuery), +function(t) {
    "use strict";
    var e = function(e, n) {
        this.options = n, this.$element = t(e), this.$backdrop = this.isShown = null, this.options.remote && this.$element.find(".modal-content").load(this.options.remote, t.proxy(function() {
            this.$element.trigger("loaded.bs.modal")
        }, this))
    };
    e.DEFAULTS = {backdrop: !0, keyboard: !0, show: !0}, e.prototype.toggle = function(t) {
        return this[this.isShown ? "hide" : "show"](t)
    }, e.prototype.show = function(e) {
        var n = this, i = t.Event("show.bs.modal", {relatedTarget: e});
        this.$element.trigger(i), this.isShown || i.isDefaultPrevented() || (this.isShown = !0, this.escape(), this.$element.on("click.dismiss.bs.modal", '[data-dismiss="modal"]', t.proxy(this.hide, this)), this.backdrop(function() {
            var i = t.support.transition && n.$element.hasClass("fade");
            n.$element.parent().length || n.$element.appendTo(document.body), n.$element.show().scrollTop(0), i && n.$element[0].offsetWidth, n.$element.addClass("in").attr("aria-hidden", !1), n.enforceFocus();
            var s = t.Event("shown.bs.modal", {relatedTarget: e});
            i ? n.$element.find(".modal-dialog").one(t.support.transition.end, function() {
                n.$element.focus().trigger(s)
            }).emulateTransitionEnd(300) : n.$element.focus().trigger(s)
        }))
    }, e.prototype.hide = function(e) {
        e && e.preventDefault(), e = t.Event("hide.bs.modal"), this.$element.trigger(e), this.isShown && !e.isDefaultPrevented() && (this.isShown = !1, this.escape(), t(document).off("focusin.bs.modal"), this.$element.removeClass("in").attr("aria-hidden", !0).off("click.dismiss.bs.modal"), t.support.transition && this.$element.hasClass("fade") ? this.$element.one(t.support.transition.end, t.proxy(this.hideModal, this)).emulateTransitionEnd(300) : this.hideModal())
    }, e.prototype.enforceFocus = function() {
        t(document).off("focusin.bs.modal").on("focusin.bs.modal", t.proxy(function(t) {
            this.$element[0] === t.target || this.$element.has(t.target).length || this.$element.focus()
        }, this))
    }, e.prototype.escape = function() {
        this.isShown && this.options.keyboard ? this.$element.on("keyup.dismiss.bs.modal", t.proxy(function(t) {
            27 == t.which && this.hide()
        }, this)) : this.isShown || this.$element.off("keyup.dismiss.bs.modal")
    }, e.prototype.hideModal = function() {
        var t = this;
        this.$element.hide(), this.backdrop(function() {
            t.removeBackdrop(), t.$element.trigger("hidden.bs.modal")
        })
    }, e.prototype.removeBackdrop = function() {
        this.$backdrop && this.$backdrop.remove(), this.$backdrop = null
    }, e.prototype.backdrop = function(e) {
        var n = this.$element.hasClass("fade") ? "fade" : "";
        if (this.isShown && this.options.backdrop) {
            var i = t.support.transition && n;
            if (this.$backdrop = t('<div class="modal-backdrop ' + n + '" />').appendTo(document.body), this.$element.on("click.dismiss.bs.modal", t.proxy(function(t) {
                t.target === t.currentTarget && ("static" == this.options.backdrop ? this.$element[0].focus.call(this.$element[0]) : this.hide.call(this))
            }, this)), i && this.$backdrop[0].offsetWidth, this.$backdrop.addClass("in"), !e)
                return;
            i ? this.$backdrop.one(t.support.transition.end, e).emulateTransitionEnd(150) : e()
        } else
            !this.isShown && this.$backdrop ? (this.$backdrop.removeClass("in"), t.support.transition && this.$element.hasClass("fade") ? this.$backdrop.one(t.support.transition.end, e).emulateTransitionEnd(150) : e()) : e && e()
    };
    var n = t.fn.modal;
    t.fn.modal = function(n, i) {
        return this.each(function() {
            var s = t(this), o = s.data("bs.modal"), r = t.extend({}, e.DEFAULTS, s.data(), "object" == typeof n && n);
            o || s.data("bs.modal", o = new e(this, r)), "string" == typeof n ? o[n](i) : r.show && o.show(i)
        })
    }, t.fn.modal.Constructor = e, t.fn.modal.noConflict = function() {
        return t.fn.modal = n, this
    }, t(document).on("click.bs.modal.data-api", '[data-toggle="modal"]', function(e) {
        var n = t(this), i = n.attr("href"), s = t(n.attr("data-target") || i && i.replace(/.*(?=#[^\s]+$)/, "")), o = s.data("bs.modal") ? "toggle" : t.extend({remote: !/#/.test(i) && i}, s.data(), n.data());
        n.is("a") && e.preventDefault(), s.modal(o, this).one("hide", function() {
            n.is(":visible") && n.focus()
        })
    }), t(document).on("show.bs.modal", ".modal", function() {
        t(document.body).addClass("modal-open")
    }).on("hidden.bs.modal", ".modal", function() {
        t(document.body).removeClass("modal-open")
    })
}(jQuery), +function(t) {
    "use strict";
    var e = function(t, e) {
        this.type = this.options = this.enabled = this.timeout = this.hoverState = this.$element = null, this.init("tooltip", t, e)
    };
    e.DEFAULTS = {animation: !0, placement: "top", selector: !1, template: '<div class="tooltip"><div class="tooltip-arrow"></div><div class="tooltip-inner"></div></div>', trigger: "hover focus", title: "", delay: 0, html: !1, container: !1}, e.prototype.init = function(e, n, i) {
        this.enabled = !0, this.type = e, this.$element = t(n), this.options = this.getOptions(i);
        for (var s = this.options.trigger.split(" "), o = s.length; o--; ) {
            var r = s[o];
            if ("click" == r)
                this.$element.on("click." + this.type, this.options.selector, t.proxy(this.toggle, this));
            else if ("manual" != r) {
                var a = "hover" == r ? "mouseenter" : "focusin", l = "hover" == r ? "mouseleave" : "focusout";
                this.$element.on(a + "." + this.type, this.options.selector, t.proxy(this.enter, this)), this.$element.on(l + "." + this.type, this.options.selector, t.proxy(this.leave, this))
            }
        }
        this.options.selector ? this._options = t.extend({}, this.options, {trigger: "manual", selector: ""}) : this.fixTitle()
    }, e.prototype.getDefaults = function() {
        return e.DEFAULTS
    }, e.prototype.getOptions = function(e) {
        return e = t.extend({}, this.getDefaults(), this.$element.data(), e), e.delay && "number" == typeof e.delay && (e.delay = {show: e.delay, hide: e.delay}), e
    }, e.prototype.getDelegateOptions = function() {
        var e = {}, n = this.getDefaults();
        return this._options && t.each(this._options, function(t, i) {
            n[t] != i && (e[t] = i)
        }), e
    }, e.prototype.enter = function(e) {
        var n = e instanceof this.constructor ? e : t(e.currentTarget)[this.type](this.getDelegateOptions()).data("bs." + this.type);
        return clearTimeout(n.timeout), n.hoverState = "in", n.options.delay && n.options.delay.show ? void(n.timeout = setTimeout(function() {
            "in" == n.hoverState && n.show()
        }, n.options.delay.show)) : n.show()
    }, e.prototype.leave = function(e) {
        var n = e instanceof this.constructor ? e : t(e.currentTarget)[this.type](this.getDelegateOptions()).data("bs." + this.type);
        return clearTimeout(n.timeout), n.hoverState = "out", n.options.delay && n.options.delay.hide ? void(n.timeout = setTimeout(function() {
            "out" == n.hoverState && n.hide()
        }, n.options.delay.hide)) : n.hide()
    }, e.prototype.show = function() {
        var e = t.Event("show.bs." + this.type);
        if (this.hasContent() && this.enabled) {
            if (this.$element.trigger(e), e.isDefaultPrevented())
                return;
            var n = this, i = this.tip();
            this.setContent(), this.options.animation && i.addClass("fade");
            var s = "function" == typeof this.options.placement ? this.options.placement.call(this, i[0], this.$element[0]) : this.options.placement, o = /\s?auto?\s?/i, r = o.test(s);
            r && (s = s.replace(o, "") || "top"), i.detach().css({top: 0, left: 0, display: "block"}).addClass(s), this.options.container ? i.appendTo(this.options.container) : i.insertAfter(this.$element);
            var a = this.getPosition(), l = i[0].offsetWidth, u = i[0].offsetHeight;
            if (r) {
                var c = this.$element.parent(), h = s, d = document.documentElement.scrollTop || document.body.scrollTop, p = "body" == this.options.container ? window.innerWidth : c.outerWidth(), f = "body" == this.options.container ? window.innerHeight : c.outerHeight(), m = "body" == this.options.container ? 0 : c.offset().left;
                s = "bottom" == s && a.top + a.height + u - d > f ? "top" : "top" == s && a.top - d - u < 0 ? "bottom" : "right" == s && a.right + l > p ? "left" : "left" == s && a.left - l < m ? "right" : s, i.removeClass(h).addClass(s)
            }
            var v = this.getCalculatedOffset(s, a, l, u);
            this.applyPlacement(v, s), this.hoverState = null;
            var g = function() {
                n.$element.trigger("shown.bs." + n.type)
            };
            t.support.transition && this.$tip.hasClass("fade") ? i.one(t.support.transition.end, g).emulateTransitionEnd(150) : g()
        }
    }, e.prototype.applyPlacement = function(e, n) {
        var i, s = this.tip(), o = s[0].offsetWidth, r = s[0].offsetHeight, a = parseInt(s.css("margin-top"), 10), l = parseInt(s.css("margin-left"), 10);
        isNaN(a) && (a = 0), isNaN(l) && (l = 0), e.top = e.top + a, e.left = e.left + l, t.offset.setOffset(s[0], t.extend({using: function(t) {
                s.css({top: Math.round(t.top), left: Math.round(t.left)})
            }}, e), 0), s.addClass("in");
        var u = s[0].offsetWidth, c = s[0].offsetHeight;
        if ("top" == n && c != r && (i = !0, e.top = e.top + r - c), /bottom|top/.test(n)) {
            var h = 0;
            e.left < 0 && (h = -2 * e.left, e.left = 0, s.offset(e), u = s[0].offsetWidth, c = s[0].offsetHeight), this.replaceArrow(h - o + u, u, "left")
        } else
            this.replaceArrow(c - r, c, "top");
        i && s.offset(e)
    }, e.prototype.replaceArrow = function(t, e, n) {
        this.arrow().css(n, t ? 50 * (1 - t / e) + "%" : "")
    }, e.prototype.setContent = function() {
        var t = this.tip(), e = this.getTitle();
        t.find(".tooltip-inner")[this.options.html ? "html" : "text"](e), t.removeClass("fade in top bottom left right")
    }, e.prototype.hide = function() {
        function e() {
            "in" != n.hoverState && i.detach(), n.$element.trigger("hidden.bs." + n.type)
        }
        var n = this, i = this.tip(), s = t.Event("hide.bs." + this.type);
        return this.$element.trigger(s), s.isDefaultPrevented() ? void 0 : (i.removeClass("in"), t.support.transition && this.$tip.hasClass("fade") ? i.one(t.support.transition.end, e).emulateTransitionEnd(150) : e(), this.hoverState = null, this)
    }, e.prototype.fixTitle = function() {
        var t = this.$element;
        (t.attr("title") || "string" != typeof t.attr("data-original-title")) && t.attr("data-original-title", t.attr("title") || "").attr("title", "")
    }, e.prototype.hasContent = function() {
        return this.getTitle()
    }, e.prototype.getPosition = function() {
        var e = this.$element[0];
        return t.extend({}, "function" == typeof e.getBoundingClientRect ? e.getBoundingClientRect() : {width: e.offsetWidth, height: e.offsetHeight}, this.$element.offset())
    }, e.prototype.getCalculatedOffset = function(t, e, n, i) {
        return"bottom" == t ? {top: e.top + e.height, left: e.left + e.width / 2 - n / 2} : "top" == t ? {top: e.top - i, left: e.left + e.width / 2 - n / 2} : "left" == t ? {top: e.top + e.height / 2 - i / 2, left: e.left - n} : {top: e.top + e.height / 2 - i / 2, left: e.left + e.width}
    }, e.prototype.getTitle = function() {
        var t, e = this.$element, n = this.options;
        return t = e.attr("data-original-title") || ("function" == typeof n.title ? n.title.call(e[0]) : n.title)
    }, e.prototype.tip = function() {
        return this.$tip = this.$tip || t(this.options.template)
    }, e.prototype.arrow = function() {
        return this.$arrow = this.$arrow || this.tip().find(".tooltip-arrow")
    }, e.prototype.validate = function() {
        this.$element[0].parentNode || (this.hide(), this.$element = null, this.options = null)
    }, e.prototype.enable = function() {
        this.enabled = !0
    }, e.prototype.disable = function() {
        this.enabled = !1
    }, e.prototype.toggleEnabled = function() {
        this.enabled = !this.enabled
    }, e.prototype.toggle = function(e) {
        var n = e ? t(e.currentTarget)[this.type](this.getDelegateOptions()).data("bs." + this.type) : this;
        n.tip().hasClass("in") ? n.leave(n) : n.enter(n)
    }, e.prototype.destroy = function() {
        clearTimeout(this.timeout), this.hide().$element.off("." + this.type).removeData("bs." + this.type)
    };
    var n = t.fn.tooltip;
    t.fn.tooltip = function(n) {
        return this.each(function() {
            var i = t(this), s = i.data("bs.tooltip"), o = "object" == typeof n && n;
            (s || "destroy" != n) && (s || i.data("bs.tooltip", s = new e(this, o)), "string" == typeof n && s[n]())
        })
    }, t.fn.tooltip.Constructor = e, t.fn.tooltip.noConflict = function() {
        return t.fn.tooltip = n, this
    }
}(jQuery), +function(t) {
    "use strict";
    var e = function(t, e) {
        this.init("popover", t, e)
    };
    if (!t.fn.tooltip)
        throw new Error("Popover requires tooltip.js");
    e.DEFAULTS = t.extend({}, t.fn.tooltip.Constructor.DEFAULTS, {placement: "right", trigger: "click", content: "", template: '<div class="popover"><div class="arrow"></div><h3 class="popover-title"></h3><div class="popover-content"></div></div>'}), e.prototype = t.extend({}, t.fn.tooltip.Constructor.prototype), e.prototype.constructor = e, e.prototype.getDefaults = function() {
        return e.DEFAULTS
    }, e.prototype.setContent = function() {
        var t = this.tip(), e = this.getTitle(), n = this.getContent();
        t.find(".popover-title")[this.options.html ? "html" : "text"](e), t.find(".popover-content")[this.options.html ? "string" == typeof n ? "html" : "append" : "text"](n), t.removeClass("fade top bottom left right in"), t.find(".popover-title").html() || t.find(".popover-title").hide()
    }, e.prototype.hasContent = function() {
        return this.getTitle() || this.getContent()
    }, e.prototype.getContent = function() {
        var t = this.$element, e = this.options;
        return t.attr("data-content") || ("function" == typeof e.content ? e.content.call(t[0]) : e.content)
    }, e.prototype.arrow = function() {
        return this.$arrow = this.$arrow || this.tip().find(".arrow")
    }, e.prototype.tip = function() {
        return this.$tip || (this.$tip = t(this.options.template)), this.$tip
    };
    var n = t.fn.popover;
    t.fn.popover = function(n) {
        return this.each(function() {
            var i = t(this), s = i.data("bs.popover"), o = "object" == typeof n && n;
            (s || "destroy" != n) && (s || i.data("bs.popover", s = new e(this, o)), "string" == typeof n && s[n]())
        })
    }, t.fn.popover.Constructor = e, t.fn.popover.noConflict = function() {
        return t.fn.popover = n, this
    }
}(jQuery), +function(t) {
    "use strict";
    function e(n, i) {
        var s, o = t.proxy(this.process, this);
        this.$element = t(t(n).is("body") ? window : n), this.$body = t("body"), this.$scrollElement = this.$element.on("scroll.bs.scroll-spy.data-api", o), this.options = t.extend({}, e.DEFAULTS, i), this.selector = (this.options.target || (s = t(n).attr("href")) && s.replace(/.*(?=#[^\s]+$)/, "") || "") + " .nav li > a", this.offsets = t([]), this.targets = t([]), this.activeTarget = null, this.refresh(), this.process()
    }
    e.DEFAULTS = {offset: 10}, e.prototype.refresh = function() {
        var e = this.$element[0] == window ? "offset" : "position";
        this.offsets = t([]), this.targets = t([]);
        var n = this;
        this.$body.find(this.selector).map(function() {
            var i = t(this), s = i.data("target") || i.attr("href"), o = /^#./.test(s) && t(s);
            return o && o.length && o.is(":visible") && [[o[e]().top + (!t.isWindow(n.$scrollElement.get(0)) && n.$scrollElement.scrollTop()), s]] || null
        }).sort(function(t, e) {
            return t[0] - e[0]
        }).each(function() {
            n.offsets.push(this[0]), n.targets.push(this[1])
        })
    }, e.prototype.process = function() {
        var t, e = this.$scrollElement.scrollTop() + this.options.offset, n = this.$scrollElement[0].scrollHeight || this.$body[0].scrollHeight, i = n - this.$scrollElement.height(), s = this.offsets, o = this.targets, r = this.activeTarget;
        if (e >= i)
            return r != (t = o.last()[0]) && this.activate(t);
        if (r && e <= s[0])
            return r != (t = o[0]) && this.activate(t);
        for (t = s.length; t--; )
            r != o[t] && e >= s[t] && (!s[t + 1] || e <= s[t + 1]) && this.activate(o[t])
    }, e.prototype.activate = function(e) {
        this.activeTarget = e, t(this.selector).parentsUntil(this.options.target, ".active").removeClass("active");
        var n = this.selector + '[data-target="' + e + '"],' + this.selector + '[href="' + e + '"]', i = t(n).parents("li").addClass("active");
        i.parent(".dropdown-menu").length && (i = i.closest("li.dropdown").addClass("active")), i.trigger("activate.bs.scrollspy")
    };
    var n = t.fn.scrollspy;
    t.fn.scrollspy = function(n) {
        return this.each(function() {
            var i = t(this), s = i.data("bs.scrollspy"), o = "object" == typeof n && n;
            s || i.data("bs.scrollspy", s = new e(this, o)), "string" == typeof n && s[n]()
        })
    }, t.fn.scrollspy.Constructor = e, t.fn.scrollspy.noConflict = function() {
        return t.fn.scrollspy = n, this
    }, t(window).on("load", function() {
        t('[data-spy="scroll"]').each(function() {
            var e = t(this);
            e.scrollspy(e.data())
        })
    })
}(jQuery), +function(t) {
    "use strict";
    var e = function(e) {
        this.element = t(e)
    };
    e.prototype.show = function() {
        var e = this.element, n = e.closest("ul:not(.dropdown-menu)"), i = e.data("target");
        if (i || (i = e.attr("href"), i = i && i.replace(/.*(?=#[^\s]*$)/, "")), !e.parent("li").hasClass("active")) {
            var s = n.find(".active:last a")[0], o = t.Event("show.bs.tab", {relatedTarget: s});
            if (e.trigger(o), !o.isDefaultPrevented()) {
                var r = t(i);
                this.activate(e.parent("li"), n), this.activate(r, r.parent(), function() {
                    e.trigger({type: "shown.bs.tab", relatedTarget: s})
                })
            }
        }
    }, e.prototype.activate = function(e, n, i) {
        function s() {
            o.removeClass("active").find("> .dropdown-menu > .active").removeClass("active"), e.addClass("active"), r ? (e[0].offsetWidth, e.addClass("in")) : e.removeClass("fade"), e.parent(".dropdown-menu") && e.closest("li.dropdown").addClass("active"), i && i()
        }
        var o = n.find("> .active"), r = i && t.support.transition && o.hasClass("fade");
        r ? o.one(t.support.transition.end, s).emulateTransitionEnd(150) : s(), o.removeClass("in")
    };
    var n = t.fn.tab;
    t.fn.tab = function(n) {
        return this.each(function() {
            var i = t(this), s = i.data("bs.tab");
            s || i.data("bs.tab", s = new e(this)), "string" == typeof n && s[n]()
        })
    }, t.fn.tab.Constructor = e, t.fn.tab.noConflict = function() {
        return t.fn.tab = n, this
    }, t(document).on("click.bs.tab.data-api", '[data-toggle="tab"], [data-toggle="pill"]', function(e) {
        e.preventDefault(), t(this).tab("show")
    })
}(jQuery), +function(t) {
    "use strict";
    var e = function(n, i) {
        this.options = t.extend({}, e.DEFAULTS, i), this.$window = t(window).on("scroll.bs.affix.data-api", t.proxy(this.checkPosition, this)).on("click.bs.affix.data-api", t.proxy(this.checkPositionWithEventLoop, this)), this.$element = t(n), this.affixed = this.unpin = this.pinnedOffset = null, this.checkPosition()
    };
    e.RESET = "affix affix-top affix-bottom", e.DEFAULTS = {offset: 0}, e.prototype.getPinnedOffset = function() {
        if (this.pinnedOffset)
            return this.pinnedOffset;
        this.$element.removeClass(e.RESET).addClass("affix");
        var t = this.$window.scrollTop(), n = this.$element.offset();
        return this.pinnedOffset = n.top - t
    }, e.prototype.checkPositionWithEventLoop = function() {
        setTimeout(t.proxy(this.checkPosition, this), 1)
    }, e.prototype.checkPosition = function() {
        if (this.$element.is(":visible")) {
            var n = t(document).height(), i = this.$window.scrollTop(), s = this.$element.offset(), o = this.options.offset, r = o.top, a = o.bottom;
            "top" == this.affixed && (s.top += i), "object" != typeof o && (a = r = o), "function" == typeof r && (r = o.top(this.$element)), "function" == typeof a && (a = o.bottom(this.$element));
            var l = null != this.unpin && i + this.unpin <= s.top ? !1 : null != a && s.top + this.$element.height() >= n - a ? "bottom" : null != r && r >= i ? "top" : !1;
            if (this.affixed !== l) {
                this.unpin && this.$element.css("top", "");
                var u = "affix" + (l ? "-" + l : ""), c = t.Event(u + ".bs.affix");
                this.$element.trigger(c), c.isDefaultPrevented() || (this.affixed = l, this.unpin = "bottom" == l ? this.getPinnedOffset() : null, this.$element.removeClass(e.RESET).addClass(u).trigger(t.Event(u.replace("affix", "affixed"))), "bottom" == l && this.$element.offset({top: n - a - this.$element.height()}))
            }
        }
    };
    var n = t.fn.affix;
    t.fn.affix = function(n) {
        return this.each(function() {
            var i = t(this), s = i.data("bs.affix"), o = "object" == typeof n && n;
            s || i.data("bs.affix", s = new e(this, o)), "string" == typeof n && s[n]()
        })
    }, t.fn.affix.Constructor = e, t.fn.affix.noConflict = function() {
        return t.fn.affix = n, this
    }, t(window).on("load", function() {
        t('[data-spy="affix"]').each(function() {
            var e = t(this), n = e.data();
            n.offset = n.offset || {}, n.offsetBottom && (n.offset.bottom = n.offsetBottom), n.offsetTop && (n.offset.top = n.offsetTop), e.affix(n)
        })
    })
}(jQuery), /*! jQuery UI - v1.11.1 - 2014-09-25
 * http://jqueryui.com
 * Includes: core.js, widget.js, mouse.js, slider.js
 * Copyright 2014 jQuery Foundation and other contributors; Licensed MIT */
        function(t) {
            "function" == typeof define && define.amd ? define(["jquery"], t) : t(jQuery)
        }(function(t) {
    function e(e, i) {
        var s, o, r, a = e.nodeName.toLowerCase();
        return"area" === a ? (s = e.parentNode, o = s.name, e.href && o && "map" === s.nodeName.toLowerCase() ? (r = t("img[usemap='#" + o + "']")[0], !!r && n(r)) : !1) : (/input|select|textarea|button|object/.test(a) ? !e.disabled : "a" === a ? e.href || i : i) && n(e)
    }
    function n(e) {
        return t.expr.filters.visible(e) && !t(e).parents().addBack().filter(function() {
            return"hidden" === t.css(this, "visibility")
        }).length
    }
    t.ui = t.ui || {}, t.extend(t.ui, {version: "1.11.1", keyCode: {BACKSPACE: 8, COMMA: 188, DELETE: 46, DOWN: 40, END: 35, ENTER: 13, ESCAPE: 27, HOME: 36, LEFT: 37, PAGE_DOWN: 34, PAGE_UP: 33, PERIOD: 190, RIGHT: 39, SPACE: 32, TAB: 9, UP: 38}}), t.fn.extend({scrollParent: function(e) {
            var n = this.css("position"), i = "absolute" === n, s = e ? /(auto|scroll|hidden)/ : /(auto|scroll)/, o = this.parents().filter(function() {
                var e = t(this);
                return i && "static" === e.css("position") ? !1 : s.test(e.css("overflow") + e.css("overflow-y") + e.css("overflow-x"))
            }).eq(0);
            return"fixed" !== n && o.length ? o : t(this[0].ownerDocument || document)
        }, uniqueId: function() {
            var t = 0;
            return function() {
                return this.each(function() {
                    this.id || (this.id = "ui-id-" + ++t)
                })
            }
        }(), removeUniqueId: function() {
            return this.each(function() {
                /^ui-id-\d+$/.test(this.id) && t(this).removeAttr("id")
            })
        }}), t.extend(t.expr[":"], {data: t.expr.createPseudo ? t.expr.createPseudo(function(e) {
            return function(n) {
                return!!t.data(n, e)
            }
        }) : function(e, n, i) {
            return!!t.data(e, i[3])
        }, focusable: function(n) {
            return e(n, !isNaN(t.attr(n, "tabindex")))
        }, tabbable: function(n) {
            var i = t.attr(n, "tabindex"), s = isNaN(i);
            return(s || i >= 0) && e(n, !s)
        }}), t("<a>").outerWidth(1).jquery || t.each(["Width", "Height"], function(e, n) {
        function i(e, n, i, o) {
            return t.each(s, function() {
                n -= parseFloat(t.css(e, "padding" + this)) || 0, i && (n -= parseFloat(t.css(e, "border" + this + "Width")) || 0), o && (n -= parseFloat(t.css(e, "margin" + this)) || 0)
            }), n
        }
        var s = "Width" === n ? ["Left", "Right"] : ["Top", "Bottom"], o = n.toLowerCase(), r = {innerWidth: t.fn.innerWidth, innerHeight: t.fn.innerHeight, outerWidth: t.fn.outerWidth, outerHeight: t.fn.outerHeight};
        t.fn["inner" + n] = function(e) {
            return void 0 === e ? r["inner" + n].call(this) : this.each(function() {
                t(this).css(o, i(this, e) + "px")
            })
        }, t.fn["outer" + n] = function(e, s) {
            return"number" != typeof e ? r["outer" + n].call(this, e) : this.each(function() {
                t(this).css(o, i(this, e, !0, s) + "px")
            })
        }
    }), t.fn.addBack || (t.fn.addBack = function(t) {
        return this.add(null == t ? this.prevObject : this.prevObject.filter(t))
    }), t("<a>").data("a-b", "a").removeData("a-b").data("a-b") && (t.fn.removeData = function(e) {
        return function(n) {
            return arguments.length ? e.call(this, t.camelCase(n)) : e.call(this)
        }
    }(t.fn.removeData)), t.ui.ie = !!/msie [\w.]+/.exec(navigator.userAgent.toLowerCase()), t.fn.extend({focus: function(e) {
            return function(n, i) {
                return"number" == typeof n ? this.each(function() {
                    var e = this;
                    setTimeout(function() {
                        t(e).focus(), i && i.call(e)
                    }, n)
                }) : e.apply(this, arguments)
            }
        }(t.fn.focus), disableSelection: function() {
            var t = "onselectstart"in document.createElement("div") ? "selectstart" : "mousedown";
            return function() {
                return this.bind(t + ".ui-disableSelection", function(t) {
                    t.preventDefault()
                })
            }
        }(), enableSelection: function() {
            return this.unbind(".ui-disableSelection")
        }, zIndex: function(e) {
            if (void 0 !== e)
                return this.css("zIndex", e);
            if (this.length)
                for (var n, i, s = t(this[0]); s.length && s[0] !== document; ) {
                    if (n = s.css("position"), ("absolute" === n || "relative" === n || "fixed" === n) && (i = parseInt(s.css("zIndex"), 10), !isNaN(i) && 0 !== i))
                        return i;
                    s = s.parent()
                }
            return 0
        }}), t.ui.plugin = {add: function(e, n, i) {
            var s, o = t.ui[e].prototype;
            for (s in i)
                o.plugins[s] = o.plugins[s] || [], o.plugins[s].push([n, i[s]])
        }, call: function(t, e, n, i) {
            var s, o = t.plugins[e];
            if (o && (i || t.element[0].parentNode && 11 !== t.element[0].parentNode.nodeType))
                for (s = 0; o.length > s; s++)
                    t.options[o[s][0]] && o[s][1].apply(t.element, n)
        }};
    var i = 0, s = Array.prototype.slice;
    t.cleanData = function(e) {
        return function(n) {
            var i, s, o;
            for (o = 0; null != (s = n[o]); o++)
                try {
                    i = t._data(s, "events"), i && i.remove && t(s).triggerHandler("remove")
                } catch (r) {
                }
            e(n)
        }
    }(t.cleanData), t.widget = function(e, n, i) {
        var s, o, r, a, l = {}, u = e.split(".")[0];
        return e = e.split(".")[1], s = u + "-" + e, i || (i = n, n = t.Widget), t.expr[":"][s.toLowerCase()] = function(e) {
            return!!t.data(e, s)
        }, t[u] = t[u] || {}, o = t[u][e], r = t[u][e] = function(t, e) {
            return this._createWidget ? (arguments.length && this._createWidget(t, e), void 0) : new r(t, e)
        }, t.extend(r, o, {version: i.version, _proto: t.extend({}, i), _childConstructors: []}), a = new n, a.options = t.widget.extend({}, a.options), t.each(i, function(e, i) {
            return t.isFunction(i) ? (l[e] = function() {
                var t = function() {
                    return n.prototype[e].apply(this, arguments)
                }, s = function(t) {
                    return n.prototype[e].apply(this, t)
                };
                return function() {
                    var e, n = this._super, o = this._superApply;
                    return this._super = t, this._superApply = s, e = i.apply(this, arguments), this._super = n, this._superApply = o, e
                }
            }(), void 0) : (l[e] = i, void 0)
        }), r.prototype = t.widget.extend(a, {widgetEventPrefix: o ? a.widgetEventPrefix || e : e}, l, {constructor: r, namespace: u, widgetName: e, widgetFullName: s}), o ? (t.each(o._childConstructors, function(e, n) {
            var i = n.prototype;
            t.widget(i.namespace + "." + i.widgetName, r, n._proto)
        }), delete o._childConstructors) : n._childConstructors.push(r), t.widget.bridge(e, r), r
    }, t.widget.extend = function(e) {
        for (var n, i, o = s.call(arguments, 1), r = 0, a = o.length; a > r; r++)
            for (n in o[r])
                i = o[r][n], o[r].hasOwnProperty(n) && void 0 !== i && (e[n] = t.isPlainObject(i) ? t.isPlainObject(e[n]) ? t.widget.extend({}, e[n], i) : t.widget.extend({}, i) : i);
        return e
    }, t.widget.bridge = function(e, n) {
        var i = n.prototype.widgetFullName || e;
        t.fn[e] = function(o) {
            var r = "string" == typeof o, a = s.call(arguments, 1), l = this;
            return o = !r && a.length ? t.widget.extend.apply(null, [o].concat(a)) : o, r ? this.each(function() {
                var n, s = t.data(this, i);
                return"instance" === o ? (l = s, !1) : s ? t.isFunction(s[o]) && "_" !== o.charAt(0) ? (n = s[o].apply(s, a), n !== s && void 0 !== n ? (l = n && n.jquery ? l.pushStack(n.get()) : n, !1) : void 0) : t.error("no such method '" + o + "' for " + e + " widget instance") : t.error("cannot call methods on " + e + " prior to initialization; attempted to call method '" + o + "'")
            }) : this.each(function() {
                var e = t.data(this, i);
                e ? (e.option(o || {}), e._init && e._init()) : t.data(this, i, new n(o, this))
            }), l
        }
    }, t.Widget = function() {
    }, t.Widget._childConstructors = [], t.Widget.prototype = {widgetName: "widget", widgetEventPrefix: "", defaultElement: "<div>", options: {disabled: !1, create: null}, _createWidget: function(e, n) {
            n = t(n || this.defaultElement || this)[0], this.element = t(n), this.uuid = i++, this.eventNamespace = "." + this.widgetName + this.uuid, this.options = t.widget.extend({}, this.options, this._getCreateOptions(), e), this.bindings = t(), this.hoverable = t(), this.focusable = t(), n !== this && (t.data(n, this.widgetFullName, this), this._on(!0, this.element, {remove: function(t) {
                    t.target === n && this.destroy()
                }}), this.document = t(n.style ? n.ownerDocument : n.document || n), this.window = t(this.document[0].defaultView || this.document[0].parentWindow)), this._create(), this._trigger("create", null, this._getCreateEventData()), this._init()
        }, _getCreateOptions: t.noop, _getCreateEventData: t.noop, _create: t.noop, _init: t.noop, destroy: function() {
            this._destroy(), this.element.unbind(this.eventNamespace).removeData(this.widgetFullName).removeData(t.camelCase(this.widgetFullName)), this.widget().unbind(this.eventNamespace).removeAttr("aria-disabled").removeClass(this.widgetFullName + "-disabled ui-state-disabled"), this.bindings.unbind(this.eventNamespace), this.hoverable.removeClass("ui-state-hover"), this.focusable.removeClass("ui-state-focus")
        }, _destroy: t.noop, widget: function() {
            return this.element
        }, option: function(e, n) {
            var i, s, o, r = e;
            if (0 === arguments.length)
                return t.widget.extend({}, this.options);
            if ("string" == typeof e)
                if (r = {}, i = e.split("."), e = i.shift(), i.length) {
                    for (s = r[e] = t.widget.extend({}, this.options[e]), o = 0; i.length - 1 > o; o++)
                        s[i[o]] = s[i[o]] || {}, s = s[i[o]];
                    if (e = i.pop(), 1 === arguments.length)
                        return void 0 === s[e] ? null : s[e];
                    s[e] = n
                } else {
                    if (1 === arguments.length)
                        return void 0 === this.options[e] ? null : this.options[e];
                    r[e] = n
                }
            return this._setOptions(r), this
        }, _setOptions: function(t) {
            var e;
            for (e in t)
                this._setOption(e, t[e]);
            return this
        }, _setOption: function(t, e) {
            return this.options[t] = e, "disabled" === t && (this.widget().toggleClass(this.widgetFullName + "-disabled", !!e), e && (this.hoverable.removeClass("ui-state-hover"), this.focusable.removeClass("ui-state-focus"))), this
        }, enable: function() {
            return this._setOptions({disabled: !1})
        }, disable: function() {
            return this._setOptions({disabled: !0})
        }, _on: function(e, n, i) {
            var s, o = this;
            "boolean" != typeof e && (i = n, n = e, e = !1), i ? (n = s = t(n), this.bindings = this.bindings.add(n)) : (i = n, n = this.element, s = this.widget()), t.each(i, function(i, r) {
                function a() {
                    return e || o.options.disabled !== !0 && !t(this).hasClass("ui-state-disabled") ? ("string" == typeof r ? o[r] : r).apply(o, arguments) : void 0
                }
                "string" != typeof r && (a.guid = r.guid = r.guid || a.guid || t.guid++);
                var l = i.match(/^([\w:-]*)\s*(.*)$/), u = l[1] + o.eventNamespace, c = l[2];
                c ? s.delegate(c, u, a) : n.bind(u, a)
            })
        }, _off: function(t, e) {
            e = (e || "").split(" ").join(this.eventNamespace + " ") + this.eventNamespace, t.unbind(e).undelegate(e)
        }, _delay: function(t, e) {
            function n() {
                return("string" == typeof t ? i[t] : t).apply(i, arguments)
            }
            var i = this;
            return setTimeout(n, e || 0)
        }, _hoverable: function(e) {
            this.hoverable = this.hoverable.add(e), this._on(e, {mouseenter: function(e) {
                    t(e.currentTarget).addClass("ui-state-hover")
                }, mouseleave: function(e) {
                    t(e.currentTarget).removeClass("ui-state-hover")
                }})
        }, _focusable: function(e) {
            this.focusable = this.focusable.add(e), this._on(e, {focusin: function(e) {
                    t(e.currentTarget).addClass("ui-state-focus")
                }, focusout: function(e) {
                    t(e.currentTarget).removeClass("ui-state-focus")
                }})
        }, _trigger: function(e, n, i) {
            var s, o, r = this.options[e];
            if (i = i || {}, n = t.Event(n), n.type = (e === this.widgetEventPrefix ? e : this.widgetEventPrefix + e).toLowerCase(), n.target = this.element[0], o = n.originalEvent)
                for (s in o)
                    s in n || (n[s] = o[s]);
            return this.element.trigger(n, i), !(t.isFunction(r) && r.apply(this.element[0], [n].concat(i)) === !1 || n.isDefaultPrevented())
        }}, t.each({show: "fadeIn", hide: "fadeOut"}, function(e, n) {
        t.Widget.prototype["_" + e] = function(i, s, o) {
            "string" == typeof s && (s = {effect: s});
            var r, a = s ? s === !0 || "number" == typeof s ? n : s.effect || n : e;
            s = s || {}, "number" == typeof s && (s = {duration: s}), r = !t.isEmptyObject(s), s.complete = o, s.delay && i.delay(s.delay), r && t.effects && t.effects.effect[a] ? i[e](s) : a !== e && i[a] ? i[a](s.duration, s.easing, o) : i.queue(function(n) {
                t(this)[e](), o && o.call(i[0]), n()
            })
        }
    }), t.widget;
    var o = !1;
    t(document).mouseup(function() {
        o = !1
    }), t.widget("ui.mouse", {version: "1.11.1", options: {cancel: "input,textarea,button,select,option", distance: 1, delay: 0}, _mouseInit: function() {
            var e = this;
            this.element.bind("mousedown." + this.widgetName, function(t) {
                return e._mouseDown(t)
            }).bind("click." + this.widgetName, function(n) {
                return!0 === t.data(n.target, e.widgetName + ".preventClickEvent") ? (t.removeData(n.target, e.widgetName + ".preventClickEvent"), n.stopImmediatePropagation(), !1) : void 0
            }), this.started = !1
        }, _mouseDestroy: function() {
            this.element.unbind("." + this.widgetName), this._mouseMoveDelegate && this.document.unbind("mousemove." + this.widgetName, this._mouseMoveDelegate).unbind("mouseup." + this.widgetName, this._mouseUpDelegate)
        }, _mouseDown: function(e) {
            if (!o) {
                this._mouseStarted && this._mouseUp(e), this._mouseDownEvent = e;
                var n = this, i = 1 === e.which, s = "string" == typeof this.options.cancel && e.target.nodeName ? t(e.target).closest(this.options.cancel).length : !1;
                return i && !s && this._mouseCapture(e) ? (this.mouseDelayMet = !this.options.delay, this.mouseDelayMet || (this._mouseDelayTimer = setTimeout(function() {
                    n.mouseDelayMet = !0
                }, this.options.delay)), this._mouseDistanceMet(e) && this._mouseDelayMet(e) && (this._mouseStarted = this._mouseStart(e) !== !1, !this._mouseStarted) ? (e.preventDefault(), !0) : (!0 === t.data(e.target, this.widgetName + ".preventClickEvent") && t.removeData(e.target, this.widgetName + ".preventClickEvent"), this._mouseMoveDelegate = function(t) {
                    return n._mouseMove(t)
                }, this._mouseUpDelegate = function(t) {
                    return n._mouseUp(t)
                }, this.document.bind("mousemove." + this.widgetName, this._mouseMoveDelegate).bind("mouseup." + this.widgetName, this._mouseUpDelegate), e.preventDefault(), o = !0, !0)) : !0
            }
        }, _mouseMove: function(e) {
            return t.ui.ie && (!document.documentMode || 9 > document.documentMode) && !e.button ? this._mouseUp(e) : e.which ? this._mouseStarted ? (this._mouseDrag(e), e.preventDefault()) : (this._mouseDistanceMet(e) && this._mouseDelayMet(e) && (this._mouseStarted = this._mouseStart(this._mouseDownEvent, e) !== !1, this._mouseStarted ? this._mouseDrag(e) : this._mouseUp(e)), !this._mouseStarted) : this._mouseUp(e)
        }, _mouseUp: function(e) {
            return this.document.unbind("mousemove." + this.widgetName, this._mouseMoveDelegate).unbind("mouseup." + this.widgetName, this._mouseUpDelegate), this._mouseStarted && (this._mouseStarted = !1, e.target === this._mouseDownEvent.target && t.data(e.target, this.widgetName + ".preventClickEvent", !0), this._mouseStop(e)), o = !1, !1
        }, _mouseDistanceMet: function(t) {
            return Math.max(Math.abs(this._mouseDownEvent.pageX - t.pageX), Math.abs(this._mouseDownEvent.pageY - t.pageY)) >= this.options.distance
        }, _mouseDelayMet: function() {
            return this.mouseDelayMet
        }, _mouseStart: function() {
        }, _mouseDrag: function() {
        }, _mouseStop: function() {
        }, _mouseCapture: function() {
            return!0
        }}), t.widget("ui.slider", t.ui.mouse, {version: "1.11.1", widgetEventPrefix: "slide", options: {animate: !1, distance: 0, max: 100, min: 0, orientation: "horizontal", range: !1, step: 1, value: 0, values: null, change: null, slide: null, start: null, stop: null}, numPages: 5, _create: function() {
            this._keySliding = !1, this._mouseSliding = !1, this._animateOff = !0, this._handleIndex = null, this._detectOrientation(), this._mouseInit(), this.element.addClass("ui-slider ui-slider-" + this.orientation + " ui-widget ui-widget-content ui-corner-all"), this._refresh(), this._setOption("disabled", this.options.disabled), this._animateOff = !1
        }, _refresh: function() {
            this._createRange(), this._createHandles(), this._setupEvents(), this._refreshValue()
        }, _createHandles: function() {
            var e, n, i = this.options, s = this.element.find(".ui-slider-handle").addClass("ui-state-default ui-corner-all"), o = "<span class='ui-slider-handle ui-state-default ui-corner-all' tabindex='0'></span>", r = [];
            for (n = i.values && i.values.length || 1, s.length > n && (s.slice(n).remove(), s = s.slice(0, n)), e = s.length; n > e; e++)
                r.push(o);
            this.handles = s.add(t(r.join("")).appendTo(this.element)), this.handle = this.handles.eq(0), this.handles.each(function(e) {
                t(this).data("ui-slider-handle-index", e)
            })
        }, _createRange: function() {
            var e = this.options, n = "";
            e.range ? (e.range === !0 && (e.values ? e.values.length && 2 !== e.values.length ? e.values = [e.values[0], e.values[0]] : t.isArray(e.values) && (e.values = e.values.slice(0)) : e.values = [this._valueMin(), this._valueMin()]), this.range && this.range.length ? this.range.removeClass("ui-slider-range-min ui-slider-range-max").css({left: "", bottom: ""}) : (this.range = t("<div></div>").appendTo(this.element), n = "ui-slider-range ui-widget-header ui-corner-all"), this.range.addClass(n + ("min" === e.range || "max" === e.range ? " ui-slider-range-" + e.range : ""))) : (this.range && this.range.remove(), this.range = null)
        }, _setupEvents: function() {
            this._off(this.handles), this._on(this.handles, this._handleEvents), this._hoverable(this.handles), this._focusable(this.handles)
        }, _destroy: function() {
            this.handles.remove(), this.range && this.range.remove(), this.element.removeClass("ui-slider ui-slider-horizontal ui-slider-vertical ui-widget ui-widget-content ui-corner-all"), this._mouseDestroy()
        }, _mouseCapture: function(e) {
            var n, i, s, o, r, a, l, u, c = this, h = this.options;
            return h.disabled ? !1 : (this.elementSize = {width: this.element.outerWidth(), height: this.element.outerHeight()}, this.elementOffset = this.element.offset(), n = {x: e.pageX, y: e.pageY}, i = this._normValueFromMouse(n), s = this._valueMax() - this._valueMin() + 1, this.handles.each(function(e) {
                var n = Math.abs(i - c.values(e));
                (s > n || s === n && (e === c._lastChangedValue || c.values(e) === h.min)) && (s = n, o = t(this), r = e)
            }), a = this._start(e, r), a === !1 ? !1 : (this._mouseSliding = !0, this._handleIndex = r, o.addClass("ui-state-active").focus(), l = o.offset(), u = !t(e.target).parents().addBack().is(".ui-slider-handle"), this._clickOffset = u ? {left: 0, top: 0} : {left: e.pageX - l.left - o.width() / 2, top: e.pageY - l.top - o.height() / 2 - (parseInt(o.css("borderTopWidth"), 10) || 0) - (parseInt(o.css("borderBottomWidth"), 10) || 0) + (parseInt(o.css("marginTop"), 10) || 0)}, this.handles.hasClass("ui-state-hover") || this._slide(e, r, i), this._animateOff = !0, !0))
        }, _mouseStart: function() {
            return!0
        }, _mouseDrag: function(t) {
            var e = {x: t.pageX, y: t.pageY}, n = this._normValueFromMouse(e);
            return this._slide(t, this._handleIndex, n), !1
        }, _mouseStop: function(t) {
            return this.handles.removeClass("ui-state-active"), this._mouseSliding = !1, this._stop(t, this._handleIndex), this._change(t, this._handleIndex), this._handleIndex = null, this._clickOffset = null, this._animateOff = !1, !1
        }, _detectOrientation: function() {
            this.orientation = "vertical" === this.options.orientation ? "vertical" : "horizontal"
        }, _normValueFromMouse: function(t) {
            var e, n, i, s, o;
            return"horizontal" === this.orientation ? (e = this.elementSize.width, n = t.x - this.elementOffset.left - (this._clickOffset ? this._clickOffset.left : 0)) : (e = this.elementSize.height, n = t.y - this.elementOffset.top - (this._clickOffset ? this._clickOffset.top : 0)), i = n / e, i > 1 && (i = 1), 0 > i && (i = 0), "vertical" === this.orientation && (i = 1 - i), s = this._valueMax() - this._valueMin(), o = this._valueMin() + i * s, this._trimAlignValue(o)
        }, _start: function(t, e) {
            var n = {handle: this.handles[e], value: this.value()};
            return this.options.values && this.options.values.length && (n.value = this.values(e), n.values = this.values()), this._trigger("start", t, n)
        }, _slide: function(t, e, n) {
            var i, s, o;
            this.options.values && this.options.values.length ? (i = this.values(e ? 0 : 1), 2 === this.options.values.length && this.options.range === !0 && (0 === e && n > i || 1 === e && i > n) && (n = i), n !== this.values(e) && (s = this.values(), s[e] = n, o = this._trigger("slide", t, {handle: this.handles[e], value: n, values: s}), i = this.values(e ? 0 : 1), o !== !1 && this.values(e, n))) : n !== this.value() && (o = this._trigger("slide", t, {handle: this.handles[e], value: n}), o !== !1 && this.value(n))
        }, _stop: function(t, e) {
            var n = {handle: this.handles[e], value: this.value()};
            this.options.values && this.options.values.length && (n.value = this.values(e), n.values = this.values()), this._trigger("stop", t, n)
        }, _change: function(t, e) {
            if (!this._keySliding && !this._mouseSliding) {
                var n = {handle: this.handles[e], value: this.value()};
                this.options.values && this.options.values.length && (n.value = this.values(e), n.values = this.values()), this._lastChangedValue = e, this._trigger("change", t, n)
            }
        }, value: function(t) {
            return arguments.length ? (this.options.value = this._trimAlignValue(t), this._refreshValue(), this._change(null, 0), void 0) : this._value()
        }, values: function(e, n) {
            var i, s, o;
            if (arguments.length > 1)
                return this.options.values[e] = this._trimAlignValue(n), this._refreshValue(), this._change(null, e), void 0;
            if (!arguments.length)
                return this._values();
            if (!t.isArray(arguments[0]))
                return this.options.values && this.options.values.length ? this._values(e) : this.value();
            for (i = this.options.values, s = arguments[0], o = 0; i.length > o; o += 1)
                i[o] = this._trimAlignValue(s[o]), this._change(null, o);
            this._refreshValue()
        }, _setOption: function(e, n) {
            var i, s = 0;
            switch ("range" === e && this.options.range === !0 && ("min" === n ? (this.options.value = this._values(0), this.options.values = null) : "max" === n && (this.options.value = this._values(this.options.values.length - 1), this.options.values = null)), t.isArray(this.options.values) && (s = this.options.values.length), "disabled" === e && this.element.toggleClass("ui-state-disabled", !!n), this._super(e, n), e) {
                case"orientation":
                    this._detectOrientation(), this.element.removeClass("ui-slider-horizontal ui-slider-vertical").addClass("ui-slider-" + this.orientation), this._refreshValue(), this.handles.css("horizontal" === n ? "bottom" : "left", "");
                    break;
                case"value":
                    this._animateOff = !0, this._refreshValue(), this._change(null, 0), this._animateOff = !1;
                    break;
                case"values":
                    for (this._animateOff = !0, this._refreshValue(), i = 0; s > i; i += 1)
                        this._change(null, i);
                    this._animateOff = !1;
                    break;
                case"min":
                case"max":
                    this._animateOff = !0, this._refreshValue(), this._animateOff = !1;
                    break;
                case"range":
                    this._animateOff = !0, this._refresh(), this._animateOff = !1
            }
        }, _value: function() {
            var t = this.options.value;
            return t = this._trimAlignValue(t)
        }, _values: function(t) {
            var e, n, i;
            if (arguments.length)
                return e = this.options.values[t], e = this._trimAlignValue(e);
            if (this.options.values && this.options.values.length) {
                for (n = this.options.values.slice(), i = 0; n.length > i; i += 1)
                    n[i] = this._trimAlignValue(n[i]);
                return n
            }
            return[]
        }, _trimAlignValue: function(t) {
            if (this._valueMin() >= t)
                return this._valueMin();
            if (t >= this._valueMax())
                return this._valueMax();
            var e = this.options.step > 0 ? this.options.step : 1, n = (t - this._valueMin()) % e, i = t - n;
            return 2 * Math.abs(n) >= e && (i += n > 0 ? e : -e), parseFloat(i.toFixed(5))
        }, _valueMin: function() {
            return this.options.min
        }, _valueMax: function() {
            return this.options.max
        }, _refreshValue: function() {
            var e, n, i, s, o, r = this.options.range, a = this.options, l = this, u = this._animateOff ? !1 : a.animate, c = {};
            this.options.values && this.options.values.length ? this.handles.each(function(i) {
                n = 100 * ((l.values(i) - l._valueMin()) / (l._valueMax() - l._valueMin())), c["horizontal" === l.orientation ? "left" : "bottom"] = n + "%", t(this).stop(1, 1)[u ? "animate" : "css"](c, a.animate), l.options.range === !0 && ("horizontal" === l.orientation ? (0 === i && l.range.stop(1, 1)[u ? "animate" : "css"]({left: n + "%"}, a.animate), 1 === i && l.range[u ? "animate" : "css"]({width: n - e + "%"}, {queue: !1, duration: a.animate})) : (0 === i && l.range.stop(1, 1)[u ? "animate" : "css"]({bottom: n + "%"}, a.animate), 1 === i && l.range[u ? "animate" : "css"]({height: n - e + "%"}, {queue: !1, duration: a.animate}))), e = n
            }) : (i = this.value(), s = this._valueMin(), o = this._valueMax(), n = o !== s ? 100 * ((i - s) / (o - s)) : 0, c["horizontal" === this.orientation ? "left" : "bottom"] = n + "%", this.handle.stop(1, 1)[u ? "animate" : "css"](c, a.animate), "min" === r && "horizontal" === this.orientation && this.range.stop(1, 1)[u ? "animate" : "css"]({width: n + "%"}, a.animate), "max" === r && "horizontal" === this.orientation && this.range[u ? "animate" : "css"]({width: 100 - n + "%"}, {queue: !1, duration: a.animate}), "min" === r && "vertical" === this.orientation && this.range.stop(1, 1)[u ? "animate" : "css"]({height: n + "%"}, a.animate), "max" === r && "vertical" === this.orientation && this.range[u ? "animate" : "css"]({height: 100 - n + "%"}, {queue: !1, duration: a.animate}))
        }, _handleEvents: {keydown: function(e) {
                var n, i, s, o, r = t(e.target).data("ui-slider-handle-index");
                switch (e.keyCode) {
                    case t.ui.keyCode.HOME:
                    case t.ui.keyCode.END:
                    case t.ui.keyCode.PAGE_UP:
                    case t.ui.keyCode.PAGE_DOWN:
                    case t.ui.keyCode.UP:
                    case t.ui.keyCode.RIGHT:
                    case t.ui.keyCode.DOWN:
                    case t.ui.keyCode.LEFT:
                        if (e.preventDefault(), !this._keySliding && (this._keySliding = !0, t(e.target).addClass("ui-state-active"), n = this._start(e, r), n === !1))
                            return
                }
                switch (o = this.options.step, i = s = this.options.values && this.options.values.length ? this.values(r) : this.value(), e.keyCode) {
                    case t.ui.keyCode.HOME:
                        s = this._valueMin();
                        break;
                    case t.ui.keyCode.END:
                        s = this._valueMax();
                        break;
                    case t.ui.keyCode.PAGE_UP:
                        s = this._trimAlignValue(i + (this._valueMax() - this._valueMin()) / this.numPages);
                        break;
                    case t.ui.keyCode.PAGE_DOWN:
                        s = this._trimAlignValue(i - (this._valueMax() - this._valueMin()) / this.numPages);
                        break;
                    case t.ui.keyCode.UP:
                    case t.ui.keyCode.RIGHT:
                        if (i === this._valueMax())
                            return;
                        s = this._trimAlignValue(i + o);
                        break;
                    case t.ui.keyCode.DOWN:
                    case t.ui.keyCode.LEFT:
                        if (i === this._valueMin())
                            return;
                        s = this._trimAlignValue(i - o)
                }
                this._slide(e, r, s)
            }, keyup: function(e) {
                var n = t(e.target).data("ui-slider-handle-index");
                this._keySliding && (this._keySliding = !1, this._stop(e, n), this._change(e, n), t(e.target).removeClass("ui-state-active"))
            }}})
}), function(t) {
    var e, n = {className: "autosizejs", id: "autosizejs", append: "\n", callback: !1, resizeDelay: 10, placeholder: !0}, i = '<textarea tabindex="-1" style="position:absolute; top:-999px; left:0; right:auto; bottom:auto; border:0; padding: 0; -moz-box-sizing:content-box; -webkit-box-sizing:content-box; box-sizing:content-box; word-wrap:break-word; height:0 !important; min-height:0 !important; overflow:hidden; transition:none; -webkit-transition:none; -moz-transition:none;"/>', s = ["fontFamily", "fontSize", "fontWeight", "fontStyle", "letterSpacing", "textTransform", "wordSpacing", "textIndent"], o = t(i).data("autosize", !0)[0];
    o.style.lineHeight = "99px", "99px" === t(o).css("lineHeight") && s.push("lineHeight"), o.style.lineHeight = "", t.fn.autosize = function(i) {
        return this.length ? (i = t.extend({}, n, i || {}), o.parentNode !== document.body && t(document.body).append(o), this.each(function() {
            function n() {
                var e, n = window.getComputedStyle ? window.getComputedStyle(d, null) : !1;
                n ? (e = d.getBoundingClientRect().width, (0 === e || "number" != typeof e) && (e = parseInt(n.width, 10)), t.each(["paddingLeft", "paddingRight", "borderLeftWidth", "borderRightWidth"], function(t, i) {
                    e -= parseInt(n[i], 10)
                })) : e = Math.max(p.width(), 0), o.style.width = e + "px"
            }
            function r() {
                var r = {};
                if (e = d, o.className = i.className, o.id = i.id, u = parseInt(p.css("maxHeight"), 10), t.each(s, function(t, e) {
                    r[e] = p.css(e)
                }), t(o).css(r).attr("wrap", p.attr("wrap")), n(), window.chrome) {
                    var a = d.style.width;
                    d.style.width = "0px", d.offsetWidth, d.style.width = a
                }
            }
            function a() {
                var t, s;
                e !== d ? r() : n(), o.value = !d.value && i.placeholder ? (p.attr("placeholder") || "") + i.append : d.value + i.append, o.style.overflowY = d.style.overflowY, s = parseInt(d.style.height, 10), o.scrollTop = 0, o.scrollTop = 9e4, t = o.scrollTop, u && t > u ? (d.style.overflowY = "scroll", t = u) : (d.style.overflowY = "hidden", c > t && (t = c)), t += f, s !== t && (d.style.height = t + "px", m && i.callback.call(d, d))
            }
            function l() {
                clearTimeout(h), h = setTimeout(function() {
                    var t = p.width();
                    t !== g && (g = t, a())
                }, parseInt(i.resizeDelay, 10))
            }
            var u, c, h, d = this, p = t(d), f = 0, m = t.isFunction(i.callback), v = {height: d.style.height, overflow: d.style.overflow, overflowY: d.style.overflowY, wordWrap: d.style.wordWrap, resize: d.style.resize}, g = p.width();
            p.data("autosize") || (p.data("autosize", !0), ("border-box" === p.css("box-sizing") || "border-box" === p.css("-moz-box-sizing") || "border-box" === p.css("-webkit-box-sizing")) && (f = p.outerHeight() - p.height()), c = Math.max(parseInt(p.css("minHeight"), 10) - f || 0, p.height()), p.css({overflow: "hidden", overflowY: "hidden", wordWrap: "break-word", resize: "none" === p.css("resize") || "vertical" === p.css("resize") ? "none" : "horizontal"}), "onpropertychange"in d ? "oninput"in d ? p.on("input.autosize keyup.autosize", a) : p.on("propertychange.autosize", function() {
                "value" === event.propertyName && a()
            }) : p.on("input.autosize", a), i.resizeDelay !== !1 && t(window).on("resize.autosize", l), p.on("autosize.resize", a), p.on("autosize.resizeIncludeStyle", function() {
                e = null, a()
            }), p.on("autosize.destroy", function() {
                e = null, clearTimeout(h), t(window).off("resize", l), p.off("autosize").off(".autosize").css(v).removeData("autosize")
            }), a())
        })) : this
    }
}(window.jQuery || window.$), /*!
 * jQuery throttle / debounce - v1.1 - 3/7/2010
 * http://benalman.com/projects/jquery-throttle-debounce-plugin/
 * 
 * Copyright (c) 2010 "Cowboy" Ben Alman
 * Dual licensed under the MIT and GPL licenses.
 * http://benalman.com/about/license/
 */
// Copyright (c) 2010 "Cowboy" Ben Alman,
        function(t, e) {
            "$:nomunge";
            var n, i = t.jQuery || t.Cowboy || (t.Cowboy = {});
            i.throttle = n = function(t, n, s, o) {
                function r() {
                    function i() {
                        l = +new Date, s.apply(u, h)
                    }
                    function r() {
                        a = e
                    }
                    var u = this, c = +new Date - l, h = arguments;
                    o && !a && i(), a && clearTimeout(a), o === e && c > t ? i() : n !== !0 && (a = setTimeout(o ? r : i, o === e ? t - c : t))
                }
                var a, l = 0;
                return"boolean" != typeof n && (o = s, s = n, n = e), i.guid && (r.guid = s.guid = s.guid || i.guid++), r
            }, i.debounce = function(t, i, s) {
                return s === e ? n(t, i, !1) : n(t, s, i !== !1)
            }
        }(this), /*
         * jPlayer Plugin for jQuery JavaScript Library
         * http://www.jplayer.org
         *
         * Copyright (c) 2009 - 2014 Happyworm Ltd
         * Licensed under the MIT license.
         * http://opensource.org/licenses/MIT
         *
         * Author: Mark J Panaghiston
         * Version: 2.6.0
         * Date: 2nd April 2014
         */
        function(t, e) {
            "function" == typeof define && define.amd ? define(["jquery"], e) : t.jQuery ? e(t.jQuery) : e(t.Zepto)
        }(this, function(t, e) {
    t.fn.jPlayer = function(n) {
        var i = "string" == typeof n, s = Array.prototype.slice.call(arguments, 1), o = this;
        return n = !i && s.length ? t.extend.apply(null, [!0, n].concat(s)) : n, i && "_" === n.charAt(0) ? o : (i ? this.each(function() {
            var i = t(this).data("jPlayer"), r = i && t.isFunction(i[n]) ? i[n].apply(i, s) : i;
            return r !== i && r !== e ? (o = r, !1) : void 0
        }) : this.each(function() {
            var e = t(this).data("jPlayer");
            e ? e.option(n || {}) : t(this).data("jPlayer", new t.jPlayer(n, this))
        }), o)
    }, t.jPlayer = function(e, n) {
        if (arguments.length) {
            this.element = t(n), this.options = t.extend(!0, {}, this.options, e);
            var i = this;
            this.element.bind("remove.jPlayer", function() {
                i.destroy()
            }), this._init()
        }
    }, "function" != typeof t.fn.stop && (t.fn.stop = function() {
    }), t.jPlayer.emulateMethods = "load play pause", t.jPlayer.emulateStatus = "src readyState networkState currentTime duration paused ended playbackRate", t.jPlayer.emulateOptions = "muted volume", t.jPlayer.reservedEvent = "ready flashreset resize repeat error warning", t.jPlayer.event = {}, t.each("ready setmedia flashreset resize repeat click error warning loadstart progress suspend abort emptied stalled play pause loadedmetadata loadeddata waiting playing canplay canplaythrough seeking seeked timeupdate ended ratechange durationchange volumechange".split(" "), function() {
        t.jPlayer.event[this] = "jPlayer_" + this
    }), t.jPlayer.htmlEvent = "loadstart abort emptied stalled loadedmetadata loadeddata canplay canplaythrough".split(" "), t.jPlayer.pause = function() {
        t.each(t.jPlayer.prototype.instances, function(t, e) {
            e.data("jPlayer").status.srcSet && e.jPlayer("pause")
        })
    }, t.jPlayer.timeFormat = {showHour: !1, showMin: !0, showSec: !0, padHour: !1, padMin: !0, padSec: !0, sepHour: ":", sepMin: ":", sepSec: ""};
    var n = function() {
        this.init()
    };
    n.prototype = {init: function() {
            this.options = {timeFormat: t.jPlayer.timeFormat}
        }, time: function(t) {
            var e = new Date(1e3 * (t && "number" == typeof t ? t : 0)), n = e.getUTCHours();
            return t = this.options.timeFormat.showHour ? e.getUTCMinutes() : e.getUTCMinutes() + 60 * n, e = this.options.timeFormat.showMin ? e.getUTCSeconds() : e.getUTCSeconds() + 60 * t, n = this.options.timeFormat.padHour && 10 > n ? "0" + n : n, t = this.options.timeFormat.padMin && 10 > t ? "0" + t : t, e = this.options.timeFormat.padSec && 10 > e ? "0" + e : e, n = "" + (this.options.timeFormat.showHour ? n + this.options.timeFormat.sepHour : ""), n += this.options.timeFormat.showMin ? t + this.options.timeFormat.sepMin : "", n += this.options.timeFormat.showSec ? e + this.options.timeFormat.sepSec : ""
        }};
    var i = new n;
    t.jPlayer.convertTime = function(t) {
        return i.time(t)
    }, t.jPlayer.uaBrowser = function(t) {
        t = t.toLowerCase();
        var e = /(opera)(?:.*version)?[ \/]([\w.]+)/, n = /(msie) ([\w.]+)/, i = /(mozilla)(?:.*? rv:([\w.]+))?/;
        return t = /(webkit)[ \/]([\w.]+)/.exec(t) || e.exec(t) || n.exec(t) || 0 > t.indexOf("compatible") && i.exec(t) || [], {browser: t[1] || "", version: t[2] || "0"}
    }, t.jPlayer.uaPlatform = function(t) {
        var e = t.toLowerCase(), n = /(android)/, i = /(mobile)/;
        return t = /(ipad|iphone|ipod|android|blackberry|playbook|windows ce|webos)/.exec(e) || [], e = /(ipad|playbook)/.exec(e) || !i.exec(e) && n.exec(e) || [], t[1] && (t[1] = t[1].replace(/\s/g, "_")), {platform: t[1] || "", tablet: e[1] || ""}
    }, t.jPlayer.browser = {}, t.jPlayer.platform = {};
    var s = t.jPlayer.uaBrowser(navigator.userAgent);
    s.browser && (t.jPlayer.browser[s.browser] = !0, t.jPlayer.browser.version = s.version), s = t.jPlayer.uaPlatform(navigator.userAgent), s.platform && (t.jPlayer.platform[s.platform] = !0, t.jPlayer.platform.mobile = !s.tablet, t.jPlayer.platform.tablet = !!s.tablet), t.jPlayer.getDocMode = function() {
        var e;
        return t.jPlayer.browser.msie && (document.documentMode ? e = document.documentMode : (e = 5, document.compatMode && "CSS1Compat" === document.compatMode && (e = 7))), e
    }, t.jPlayer.browser.documentMode = t.jPlayer.getDocMode(), t.jPlayer.nativeFeatures = {init: function() {
            var t, e, n = document, i = n.createElement("video"), s = {w3c: "fullscreenEnabled fullscreenElement requestFullscreen exitFullscreen fullscreenchange fullscreenerror".split(" "), moz: "mozFullScreenEnabled mozFullScreenElement mozRequestFullScreen mozCancelFullScreen mozfullscreenchange mozfullscreenerror".split(" "), webkit: " webkitCurrentFullScreenElement webkitRequestFullScreen webkitCancelFullScreen webkitfullscreenchange ".split(" "), webkitVideo: "webkitSupportsFullscreen webkitDisplayingFullscreen webkitEnterFullscreen webkitExitFullscreen  ".split(" ")}, o = ["w3c", "moz", "webkit", "webkitVideo"];
            for (this.fullscreen = i = {support: {w3c: !!n[s.w3c[0]], moz: !!n[s.moz[0]], webkit: "function" == typeof n[s.webkit[3]], webkitVideo: "function" == typeof i[s.webkitVideo[2]]}, used: {}}, t = 0, e = o.length; e > t; t++) {
                var r = o[t];
                if (i.support[r]) {
                    i.spec = r, i.used[r] = !0;
                    break
                }
            }
            if (i.spec) {
                var a = s[i.spec];
                i.api = {fullscreenEnabled: !0, fullscreenElement: function(t) {
                        return t = t ? t : n, t[a[1]]
                    }, requestFullscreen: function(t) {
                        return t[a[2]]()
                    }, exitFullscreen: function(t) {
                        return t = t ? t : n, t[a[3]]()
                    }}, i.event = {fullscreenchange: a[4], fullscreenerror: a[5]}
            } else
                i.api = {fullscreenEnabled: !1, fullscreenElement: function() {
                        return null
                    }, requestFullscreen: function() {
                    }, exitFullscreen: function() {
                    }}, i.event = {}
        }}, t.jPlayer.nativeFeatures.init(), t.jPlayer.focus = null, t.jPlayer.keyIgnoreElementNames = "INPUT TEXTAREA";
    var o = function(e) {
        var n, i = t.jPlayer.focus;
        i && (t.each(t.jPlayer.keyIgnoreElementNames.split(/\s+/g), function(t, i) {
            return e.target.nodeName.toUpperCase() === i.toUpperCase() ? (n = !0, !1) : void 0
        }), n || t.each(i.options.keyBindings, function(n, s) {
            return s && e.which === s.key && t.isFunction(s.fn) ? (e.preventDefault(), s.fn(i), !1) : void 0
        }))
    };
    t.jPlayer.keys = function(e) {
        t(document.documentElement).unbind("keydown.jPlayer"), e && t(document.documentElement).bind("keydown.jPlayer", o)
    }, t.jPlayer.keys(!0), t.jPlayer.prototype = {count: 0, version: {script: "2.6.0", needFlash: "2.6.0", flash: "unknown"}, options: {swfPath: "js", solution: "html, flash", supplied: "mp3", preload: "metadata", volume: .8, muted: !1, remainingDuration: !1, toggleDuration: !1, playbackRate: 1, defaultPlaybackRate: 1, minPlaybackRate: .5, maxPlaybackRate: 4, wmode: "opaque", backgroundColor: "#000000", cssSelectorAncestor: "#jp_container_1", cssSelector: {videoPlay: ".jp-video-play", play: ".jp-play", pause: ".jp-pause", stop: ".jp-stop", seekBar: ".jp-seek-bar", playBar: ".jp-play-bar", mute: ".jp-mute", unmute: ".jp-unmute", volumeBar: ".jp-volume-bar", volumeBarValue: ".jp-volume-bar-value", volumeMax: ".jp-volume-max", playbackRateBar: ".jp-playback-rate-bar", playbackRateBarValue: ".jp-playback-rate-bar-value", currentTime: ".jp-current-time", duration: ".jp-duration", title: ".jp-title", fullScreen: ".jp-full-screen", restoreScreen: ".jp-restore-screen", repeat: ".jp-repeat", repeatOff: ".jp-repeat-off", gui: ".jp-gui", noSolution: ".jp-no-solution"}, smoothPlayBar: !1, fullScreen: !1, fullWindow: !1, autohide: {restored: !1, full: !0, fadeIn: 200, fadeOut: 600, hold: 1e3}, loop: !1, repeat: function(e) {
                e.jPlayer.options.loop ? t(this).unbind(".jPlayerRepeat").bind(t.jPlayer.event.ended + ".jPlayer.jPlayerRepeat", function() {
                    t(this).jPlayer("play")
                }) : t(this).unbind(".jPlayerRepeat")
            }, nativeVideoControls: {}, noFullWindow: {msie: /msie [0-6]\./, ipad: /ipad.*?os [0-4]\./, iphone: /iphone/, ipod: /ipod/, android_pad: /android [0-3]\.(?!.*?mobile)/, android_phone: /android.*?mobile/, blackberry: /blackberry/, windows_ce: /windows ce/, iemobile: /iemobile/, webos: /webos/}, noVolume: {ipad: /ipad/, iphone: /iphone/, ipod: /ipod/, android_pad: /android(?!.*?mobile)/, android_phone: /android.*?mobile/, blackberry: /blackberry/, windows_ce: /windows ce/, iemobile: /iemobile/, webos: /webos/, playbook: /playbook/}, timeFormat: {}, keyEnabled: !1, audioFullScreen: !1, keyBindings: {play: {key: 32, fn: function(t) {
                        t.status.paused ? t.play() : t.pause()
                    }}, fullScreen: {key: 13, fn: function(t) {
                        (t.status.video || t.options.audioFullScreen) && t._setOption("fullScreen", !t.options.fullScreen)
                    }}, muted: {key: 8, fn: function(t) {
                        t._muted(!t.options.muted)
                    }}, volumeUp: {key: 38, fn: function(t) {
                        t.volume(t.options.volume + .1)
                    }}, volumeDown: {key: 40, fn: function(t) {
                        t.volume(t.options.volume - .1)
                    }}}, verticalVolume: !1, verticalPlaybackRate: !1, globalVolume: !1, idPrefix: "jp", noConflict: "jQuery", emulateHtml: !1, consoleAlerts: !0, errorAlerts: !1, warningAlerts: !1}, optionsAudio: {size: {width: "0px", height: "0px", cssClass: ""}, sizeFull: {width: "0px", height: "0px", cssClass: ""}}, optionsVideo: {size: {width: "480px", height: "270px", cssClass: "jp-video-270p"}, sizeFull: {width: "100%", height: "100%", cssClass: "jp-video-full"}}, instances: {}, status: {src: "", media: {}, paused: !0, format: {}, formatType: "", waitForPlay: !0, waitForLoad: !0, srcSet: !1, video: !1, seekPercent: 0, currentPercentRelative: 0, currentPercentAbsolute: 0, currentTime: 0, duration: 0, remaining: 0, videoWidth: 0, videoHeight: 0, readyState: 0, networkState: 0, playbackRate: 1, ended: 0}, internal: {ready: !1}, solution: {html: !0, flash: !0}, format: {mp3: {codec: 'audio/mpeg; codecs="mp3"', flashCanPlay: !0, media: "audio"}, m4a: {codec: 'audio/mp4; codecs="mp4a.40.2"', flashCanPlay: !0, media: "audio"}, m3u8a: {codec: 'application/vnd.apple.mpegurl; codecs="mp4a.40.2"', flashCanPlay: !1, media: "audio"}, m3ua: {codec: "audio/mpegurl", flashCanPlay: !1, media: "audio"}, oga: {codec: 'audio/ogg; codecs="vorbis, opus"', flashCanPlay: !1, media: "audio"}, flac: {codec: "audio/x-flac", flashCanPlay: !1, media: "audio"}, wav: {codec: 'audio/wav; codecs="1"', flashCanPlay: !1, media: "audio"}, webma: {codec: 'audio/webm; codecs="vorbis"', flashCanPlay: !1, media: "audio"}, fla: {codec: "audio/x-flv", flashCanPlay: !0, media: "audio"}, rtmpa: {codec: 'audio/rtmp; codecs="rtmp"', flashCanPlay: !0, media: "audio"}, m4v: {codec: 'video/mp4; codecs="avc1.42E01E, mp4a.40.2"', flashCanPlay: !0, media: "video"}, m3u8v: {codec: 'application/vnd.apple.mpegurl; codecs="avc1.42E01E, mp4a.40.2"', flashCanPlay: !1, media: "video"}, m3uv: {codec: "audio/mpegurl", flashCanPlay: !1, media: "video"}, ogv: {codec: 'video/ogg; codecs="theora, vorbis"', flashCanPlay: !1, media: "video"}, webmv: {codec: 'video/webm; codecs="vorbis, vp8"', flashCanPlay: !1, media: "video"}, flv: {codec: "video/x-flv", flashCanPlay: !0, media: "video"}, rtmpv: {codec: 'video/rtmp; codecs="rtmp"', flashCanPlay: !0, media: "video"}}, _init: function() {
            var n = this;
            if (this.element.empty(), this.status = t.extend({}, this.status), this.internal = t.extend({}, this.internal), this.options.timeFormat = t.extend({}, t.jPlayer.timeFormat, this.options.timeFormat), this.internal.cmdsIgnored = t.jPlayer.platform.ipad || t.jPlayer.platform.iphone || t.jPlayer.platform.ipod, this.internal.domNode = this.element.get(0), this.options.keyEnabled && !t.jPlayer.focus && (t.jPlayer.focus = this), this.androidFix = {setMedia: !1, play: !1, pause: !1, time: 0 / 0}, t.jPlayer.platform.android && (this.options.preload = "auto" !== this.options.preload ? "metadata" : "auto"), this.formats = [], this.solutions = [], this.require = {}, this.htmlElement = {}, this.html = {}, this.html.audio = {}, this.html.video = {}, this.flash = {}, this.css = {}, this.css.cs = {}, this.css.jq = {}, this.ancestorJq = [], this.options.volume = this._limitValue(this.options.volume, 0, 1), t.each(this.options.supplied.toLowerCase().split(","), function(e, i) {
                var s = i.replace(/^\s+|\s+$/g, "");
                if (n.format[s]) {
                    var o = !1;
                    t.each(n.formats, function(t, e) {
                        return s === e ? (o = !0, !1) : void 0
                    }), o || n.formats.push(s)
                }
            }), t.each(this.options.solution.toLowerCase().split(","), function(e, i) {
                var s = i.replace(/^\s+|\s+$/g, "");
                if (n.solution[s]) {
                    var o = !1;
                    t.each(n.solutions, function(t, e) {
                        return s === e ? (o = !0, !1) : void 0
                    }), o || n.solutions.push(s)
                }
            }), this.internal.instance = "jp_" + this.count, this.instances[this.internal.instance] = this.element, this.element.attr("id") || this.element.attr("id", this.options.idPrefix + "_jplayer_" + this.count), this.internal.self = t.extend({}, {id: this.element.attr("id"), jq: this.element}), this.internal.audio = t.extend({}, {id: this.options.idPrefix + "_audio_" + this.count, jq: e}), this.internal.video = t.extend({}, {id: this.options.idPrefix + "_video_" + this.count, jq: e}), this.internal.flash = t.extend({}, {id: this.options.idPrefix + "_flash_" + this.count, jq: e, swf: this.options.swfPath + (".swf" !== this.options.swfPath.toLowerCase().slice(-4) ? (this.options.swfPath && "/" !== this.options.swfPath.slice(-1) ? "/" : "") + "Jplayer.swf" : "")}), this.internal.poster = t.extend({}, {id: this.options.idPrefix + "_poster_" + this.count, jq: e}), t.each(t.jPlayer.event, function(t, i) {
                n.options[t] !== e && (n.element.bind(i + ".jPlayer", n.options[t]), n.options[t] = e)
            }), this.require.audio = !1, this.require.video = !1, t.each(this.formats, function(t, e) {
                n.require[n.format[e].media] = !0
            }), this.options = this.require.video ? t.extend(!0, {}, this.optionsVideo, this.options) : t.extend(!0, {}, this.optionsAudio, this.options), this._setSize(), this.status.nativeVideoControls = this._uaBlocklist(this.options.nativeVideoControls), this.status.noFullWindow = this._uaBlocklist(this.options.noFullWindow), this.status.noVolume = this._uaBlocklist(this.options.noVolume), t.jPlayer.nativeFeatures.fullscreen.api.fullscreenEnabled && this._fullscreenAddEventListeners(), this._restrictNativeVideoControls(), this.htmlElement.poster = document.createElement("img"), this.htmlElement.poster.id = this.internal.poster.id, this.htmlElement.poster.onload = function() {
                n.status.video && !n.status.waitForPlay || n.internal.poster.jq.show()
            }, this.element.append(this.htmlElement.poster), this.internal.poster.jq = t("#" + this.internal.poster.id), this.internal.poster.jq.css({width: this.status.width, height: this.status.height}), this.internal.poster.jq.hide(), this.internal.poster.jq.bind("click.jPlayer", function() {
                n._trigger(t.jPlayer.event.click)
            }), this.html.audio.available = !1, this.require.audio && (this.htmlElement.audio = document.createElement("audio"), this.htmlElement.audio.id = this.internal.audio.id, this.html.audio.available = !!this.htmlElement.audio.canPlayType && this._testCanPlayType(this.htmlElement.audio)), this.html.video.available = !1, this.require.video && (this.htmlElement.video = document.createElement("video"), this.htmlElement.video.id = this.internal.video.id, this.html.video.available = !!this.htmlElement.video.canPlayType && this._testCanPlayType(this.htmlElement.video)), this.flash.available = this._checkForFlash(10.1), this.html.canPlay = {}, this.flash.canPlay = {}, t.each(this.formats, function(t, e) {
                n.html.canPlay[e] = n.html[n.format[e].media].available && "" !== n.htmlElement[n.format[e].media].canPlayType(n.format[e].codec), n.flash.canPlay[e] = n.format[e].flashCanPlay && n.flash.available
            }), this.html.desired = !1, this.flash.desired = !1, t.each(this.solutions, function(e, i) {
                if (0 === e)
                    n[i].desired = !0;
                else {
                    var s = !1, o = !1;
                    t.each(n.formats, function(t, e) {
                        n[n.solutions[0]].canPlay[e] && ("video" === n.format[e].media ? o = !0 : s = !0)
                    }), n[i].desired = n.require.audio && !s || n.require.video && !o
                }
            }), this.html.support = {}, this.flash.support = {}, t.each(this.formats, function(t, e) {
                n.html.support[e] = n.html.canPlay[e] && n.html.desired, n.flash.support[e] = n.flash.canPlay[e] && n.flash.desired
            }), this.html.used = !1, this.flash.used = !1, t.each(this.solutions, function(e, i) {
                t.each(n.formats, function(t, e) {
                    return n[i].support[e] ? (n[i].used = !0, !1) : void 0
                })
            }), this._resetActive(), this._resetGate(), this._cssSelectorAncestor(this.options.cssSelectorAncestor), this.html.used || this.flash.used ? this.css.jq.noSolution.length && this.css.jq.noSolution.hide() : (this._error({type: t.jPlayer.error.NO_SOLUTION, context: "{solution:'" + this.options.solution + "', supplied:'" + this.options.supplied + "'}", message: t.jPlayer.errorMsg.NO_SOLUTION, hint: t.jPlayer.errorHint.NO_SOLUTION}), this.css.jq.noSolution.length && this.css.jq.noSolution.show()), this.flash.used) {
                var i, s = "jQuery=" + encodeURI(this.options.noConflict) + "&id=" + encodeURI(this.internal.self.id) + "&vol=" + this.options.volume + "&muted=" + this.options.muted;
                if (t.jPlayer.browser.msie && (9 > Number(t.jPlayer.browser.version) || 9 > t.jPlayer.browser.documentMode)) {
                    s = ['<param name="movie" value="' + this.internal.flash.swf + '" />', '<param name="FlashVars" value="' + s + '" />', '<param name="allowScriptAccess" value="always" />', '<param name="bgcolor" value="' + this.options.backgroundColor + '" />', '<param name="wmode" value="' + this.options.wmode + '" />'], i = document.createElement('<object id="' + this.internal.flash.id + '" classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" width="0" height="0" tabindex="-1"></object>');
                    for (var o = 0; o < s.length; o++)
                        i.appendChild(document.createElement(s[o]))
                } else
                    o = function(t, e, n) {
                        var i = document.createElement("param");
                        i.setAttribute("name", e), i.setAttribute("value", n), t.appendChild(i)
                    }, i = document.createElement("object"), i.setAttribute("id", this.internal.flash.id), i.setAttribute("name", this.internal.flash.id), i.setAttribute("data", this.internal.flash.swf), i.setAttribute("type", "application/x-shockwave-flash"), i.setAttribute("width", "1"), i.setAttribute("height", "1"), i.setAttribute("tabindex", "-1"), o(i, "flashvars", s), o(i, "allowscriptaccess", "always"), o(i, "bgcolor", this.options.backgroundColor), o(i, "wmode", this.options.wmode);
                this.element.append(i), this.internal.flash.jq = t(i)
            }
            this.status.playbackRateEnabled = this.html.used && !this.flash.used ? this._testPlaybackRate("audio") : !1, this._updatePlaybackRate(), this.html.used && (this.html.audio.available && (this._addHtmlEventListeners(this.htmlElement.audio, this.html.audio), this.element.append(this.htmlElement.audio), this.internal.audio.jq = t("#" + this.internal.audio.id)), this.html.video.available && (this._addHtmlEventListeners(this.htmlElement.video, this.html.video), this.element.append(this.htmlElement.video), this.internal.video.jq = t("#" + this.internal.video.id), this.status.nativeVideoControls ? this.internal.video.jq.css({width: this.status.width, height: this.status.height}) : this.internal.video.jq.css({width: "0px", height: "0px"}), this.internal.video.jq.bind("click.jPlayer", function() {
                n._trigger(t.jPlayer.event.click)
            }))), this.options.emulateHtml && this._emulateHtmlBridge(), this.html.used && !this.flash.used && setTimeout(function() {
                n.internal.ready = !0, n.version.flash = "n/a", n._trigger(t.jPlayer.event.repeat), n._trigger(t.jPlayer.event.ready)
            }, 100), this._updateNativeVideoControls(), this.css.jq.videoPlay.length && this.css.jq.videoPlay.hide(), t.jPlayer.prototype.count++
        }, destroy: function() {
            this.clearMedia(), this._removeUiClass(), this.css.jq.currentTime.length && this.css.jq.currentTime.text(""), this.css.jq.duration.length && this.css.jq.duration.text(""), t.each(this.css.jq, function(t, e) {
                e.length && e.unbind(".jPlayer")
            }), this.internal.poster.jq.unbind(".jPlayer"), this.internal.video.jq && this.internal.video.jq.unbind(".jPlayer"), this._fullscreenRemoveEventListeners(), this === t.jPlayer.focus && (t.jPlayer.focus = null), this.options.emulateHtml && this._destroyHtmlBridge(), this.element.removeData("jPlayer"), this.element.unbind(".jPlayer"), this.element.empty(), delete this.instances[this.internal.instance]
        }, enable: function() {
        }, disable: function() {
        }, _testCanPlayType: function(t) {
            try {
                return t.canPlayType(this.format.mp3.codec), !0
            } catch (e) {
                return!1
            }
        }, _testPlaybackRate: function(t) {
            t = document.createElement("string" == typeof t ? t : "audio");
            try {
                return"playbackRate"in t ? (t.playbackRate = .5, .5 === t.playbackRate) : !1
            } catch (e) {
                return!1
            }
        }, _uaBlocklist: function(e) {
            var n = navigator.userAgent.toLowerCase(), i = !1;
            return t.each(e, function(t, e) {
                return e && e.test(n) ? (i = !0, !1) : void 0
            }), i
        }, _restrictNativeVideoControls: function() {
            this.require.audio && this.status.nativeVideoControls && (this.status.nativeVideoControls = !1, this.status.noFullWindow = !0)
        }, _updateNativeVideoControls: function() {
            this.html.video.available && this.html.used && (this.htmlElement.video.controls = this.status.nativeVideoControls, this._updateAutohide(), this.status.nativeVideoControls && this.require.video ? (this.internal.poster.jq.hide(), this.internal.video.jq.css({width: this.status.width, height: this.status.height})) : this.status.waitForPlay && this.status.video && (this.internal.poster.jq.show(), this.internal.video.jq.css({width: "0px", height: "0px"})))
        }, _addHtmlEventListeners: function(e, n) {
            var i = this;
            e.preload = this.options.preload, e.muted = this.options.muted, e.volume = this.options.volume, this.status.playbackRateEnabled && (e.defaultPlaybackRate = this.options.defaultPlaybackRate, e.playbackRate = this.options.playbackRate), e.addEventListener("progress", function() {
                n.gate && (i.internal.cmdsIgnored && 0 < this.readyState && (i.internal.cmdsIgnored = !1), i.androidFix.setMedia = !1, i.androidFix.play && (i.androidFix.play = !1, i.play(i.androidFix.time)), i.androidFix.pause && (i.androidFix.pause = !1, i.pause(i.androidFix.time)), i._getHtmlStatus(e), i._updateInterface(), i._trigger(t.jPlayer.event.progress))
            }, !1), e.addEventListener("timeupdate", function() {
                n.gate && (i._getHtmlStatus(e), i._updateInterface(), i._trigger(t.jPlayer.event.timeupdate))
            }, !1), e.addEventListener("durationchange", function() {
                n.gate && (i._getHtmlStatus(e), i._updateInterface(), i._trigger(t.jPlayer.event.durationchange))
            }, !1), e.addEventListener("play", function() {
                n.gate && (i._updateButtons(!0), i._html_checkWaitForPlay(), i._trigger(t.jPlayer.event.play))
            }, !1), e.addEventListener("playing", function() {
                n.gate && (i._updateButtons(!0), i._seeked(), i._trigger(t.jPlayer.event.playing))
            }, !1), e.addEventListener("pause", function() {
                n.gate && (i._updateButtons(!1), i._trigger(t.jPlayer.event.pause))
            }, !1), e.addEventListener("waiting", function() {
                n.gate && (i._seeking(), i._trigger(t.jPlayer.event.waiting))
            }, !1), e.addEventListener("seeking", function() {
                n.gate && (i._seeking(), i._trigger(t.jPlayer.event.seeking))
            }, !1), e.addEventListener("seeked", function() {
                n.gate && (i._seeked(), i._trigger(t.jPlayer.event.seeked))
            }, !1), e.addEventListener("volumechange", function() {
                n.gate && (i.options.volume = e.volume, i.options.muted = e.muted, i._updateMute(), i._updateVolume(), i._trigger(t.jPlayer.event.volumechange))
            }, !1), e.addEventListener("ratechange", function() {
                n.gate && (i.options.defaultPlaybackRate = e.defaultPlaybackRate, i.options.playbackRate = e.playbackRate, i._updatePlaybackRate(), i._trigger(t.jPlayer.event.ratechange))
            }, !1), e.addEventListener("suspend", function() {
                n.gate && (i._seeked(), i._trigger(t.jPlayer.event.suspend))
            }, !1), e.addEventListener("ended", function() {
                n.gate && (t.jPlayer.browser.webkit || (i.htmlElement.media.currentTime = 0), i.htmlElement.media.pause(), i._updateButtons(!1), i._getHtmlStatus(e, !0), i._updateInterface(), i._trigger(t.jPlayer.event.ended))
            }, !1), e.addEventListener("error", function() {
                n.gate && (i._updateButtons(!1), i._seeked(), i.status.srcSet && (clearTimeout(i.internal.htmlDlyCmdId), i.status.waitForLoad = !0, i.status.waitForPlay = !0, i.status.video && !i.status.nativeVideoControls && i.internal.video.jq.css({width: "0px", height: "0px"}), i._validString(i.status.media.poster) && !i.status.nativeVideoControls && i.internal.poster.jq.show(), i.css.jq.videoPlay.length && i.css.jq.videoPlay.show(), i._error({type: t.jPlayer.error.URL, context: i.status.src, message: t.jPlayer.errorMsg.URL, hint: t.jPlayer.errorHint.URL})))
            }, !1), t.each(t.jPlayer.htmlEvent, function(s, o) {
                e.addEventListener(this, function() {
                    n.gate && i._trigger(t.jPlayer.event[o])
                }, !1)
            })
        }, _getHtmlStatus: function(t, e) {
            var n = 0, i = 0, s = 0, o = 0;
            isFinite(t.duration) && (this.status.duration = t.duration), n = t.currentTime, i = 0 < this.status.duration ? 100 * n / this.status.duration : 0, "object" == typeof t.seekable && 0 < t.seekable.length ? (s = 0 < this.status.duration ? 100 * t.seekable.end(t.seekable.length - 1) / this.status.duration : 100, o = 0 < this.status.duration ? 100 * t.currentTime / t.seekable.end(t.seekable.length - 1) : 0) : (s = 100, o = i), e && (i = o = n = 0), this.status.seekPercent = s, this.status.currentPercentRelative = o, this.status.currentPercentAbsolute = i, this.status.currentTime = n, this.status.remaining = this.status.duration - this.status.currentTime, this.status.videoWidth = t.videoWidth, this.status.videoHeight = t.videoHeight, this.status.readyState = t.readyState, this.status.networkState = t.networkState, this.status.playbackRate = t.playbackRate, this.status.ended = t.ended
        }, _resetStatus: function() {
            this.status = t.extend({}, this.status, t.jPlayer.prototype.status)
        }, _trigger: function(e, n, i) {
            e = t.Event(e), e.jPlayer = {}, e.jPlayer.version = t.extend({}, this.version), e.jPlayer.options = t.extend(!0, {}, this.options), e.jPlayer.status = t.extend(!0, {}, this.status), e.jPlayer.html = t.extend(!0, {}, this.html), e.jPlayer.flash = t.extend(!0, {}, this.flash), n && (e.jPlayer.error = t.extend({}, n)), i && (e.jPlayer.warning = t.extend({}, i)), this.element.trigger(e)
        }, jPlayerFlashEvent: function(e, n) {
            if (e === t.jPlayer.event.ready)
                if (this.internal.ready) {
                    if (this.flash.gate) {
                        if (this.status.srcSet) {
                            var i = this.status.currentTime, s = this.status.paused;
                            this.setMedia(this.status.media), this.volumeWorker(this.options.volume), i > 0 && (s ? this.pause(i) : this.play(i))
                        }
                        this._trigger(t.jPlayer.event.flashreset)
                    }
                } else
                    this.internal.ready = !0, this.internal.flash.jq.css({width: "0px", height: "0px"}), this.version.flash = n.version, this.version.needFlash !== this.version.flash && this._error({type: t.jPlayer.error.VERSION, context: this.version.flash, message: t.jPlayer.errorMsg.VERSION + this.version.flash, hint: t.jPlayer.errorHint.VERSION}), this._trigger(t.jPlayer.event.repeat), this._trigger(e);
            if (this.flash.gate)
                switch (e) {
                    case t.jPlayer.event.progress:
                        this._getFlashStatus(n), this._updateInterface(), this._trigger(e);
                        break;
                    case t.jPlayer.event.timeupdate:
                        this._getFlashStatus(n), this._updateInterface(), this._trigger(e);
                        break;
                    case t.jPlayer.event.play:
                        this._seeked(), this._updateButtons(!0), this._trigger(e);
                        break;
                    case t.jPlayer.event.pause:
                        this._updateButtons(!1), this._trigger(e);
                        break;
                    case t.jPlayer.event.ended:
                        this._updateButtons(!1), this._trigger(e);
                        break;
                    case t.jPlayer.event.click:
                        this._trigger(e);
                        break;
                    case t.jPlayer.event.error:
                        this.status.waitForLoad = !0, this.status.waitForPlay = !0, this.status.video && this.internal.flash.jq.css({width: "0px", height: "0px"}), this._validString(this.status.media.poster) && this.internal.poster.jq.show(), this.css.jq.videoPlay.length && this.status.video && this.css.jq.videoPlay.show(), this.status.video ? this._flash_setVideo(this.status.media) : this._flash_setAudio(this.status.media), this._updateButtons(!1), this._error({type: t.jPlayer.error.URL, context: n.src, message: t.jPlayer.errorMsg.URL, hint: t.jPlayer.errorHint.URL});
                        break;
                    case t.jPlayer.event.seeking:
                        this._seeking(), this._trigger(e);
                        break;
                    case t.jPlayer.event.seeked:
                        this._seeked(), this._trigger(e);
                        break;
                    case t.jPlayer.event.ready:
                        break;
                    default:
                        this._trigger(e)
                }
            return!1
        }, _getFlashStatus: function(t) {
            this.status.seekPercent = t.seekPercent, this.status.currentPercentRelative = t.currentPercentRelative, this.status.currentPercentAbsolute = t.currentPercentAbsolute, this.status.currentTime = t.currentTime, this.status.duration = t.duration, this.status.remaining = t.duration - t.currentTime, this.status.videoWidth = t.videoWidth, this.status.videoHeight = t.videoHeight, this.status.readyState = 4, this.status.networkState = 0, this.status.playbackRate = 1, this.status.ended = !1
        }, _updateButtons: function(t) {
            t === e ? t = !this.status.paused : this.status.paused = !t, this.css.jq.play.length && this.css.jq.pause.length && (t ? (this.css.jq.play.hide(), this.css.jq.pause.show()) : (this.css.jq.play.show(), this.css.jq.pause.hide())), this.css.jq.restoreScreen.length && this.css.jq.fullScreen.length && (this.status.noFullWindow ? (this.css.jq.fullScreen.hide(), this.css.jq.restoreScreen.hide()) : this.options.fullWindow ? (this.css.jq.fullScreen.hide(), this.css.jq.restoreScreen.show()) : (this.css.jq.fullScreen.show(), this.css.jq.restoreScreen.hide())), this.css.jq.repeat.length && this.css.jq.repeatOff.length && (this.options.loop ? (this.css.jq.repeat.hide(), this.css.jq.repeatOff.show()) : (this.css.jq.repeat.show(), this.css.jq.repeatOff.hide()))
        }, _updateInterface: function() {
            this.css.jq.seekBar.length && this.css.jq.seekBar.width(this.status.seekPercent + "%"), this.css.jq.playBar.length && (this.options.smoothPlayBar ? this.css.jq.playBar.stop().animate({width: this.status.currentPercentAbsolute + "%"}, 250, "linear") : this.css.jq.playBar.width(this.status.currentPercentRelative + "%"));
            var t = "";
            this.css.jq.currentTime.length && (t = this._convertTime(this.status.currentTime), t !== this.css.jq.currentTime.text() && this.css.jq.currentTime.text(this._convertTime(this.status.currentTime)));
            var t = "", t = this.status.duration, e = this.status.remaining;
            this.css.jq.duration.length && ("string" == typeof this.status.media.duration ? t = this.status.media.duration : ("number" == typeof this.status.media.duration && (t = this.status.media.duration, e = t - this.status.currentTime), t = this.options.remainingDuration ? (e > 0 ? "-" : "") + this._convertTime(e) : this._convertTime(t)), t !== this.css.jq.duration.text() && this.css.jq.duration.text(t))
        }, _convertTime: n.prototype.time, _seeking: function() {
            this.css.jq.seekBar.length && this.css.jq.seekBar.addClass("jp-seeking-bg")
        }, _seeked: function() {
            this.css.jq.seekBar.length && this.css.jq.seekBar.removeClass("jp-seeking-bg")
        }, _resetGate: function() {
            this.html.audio.gate = !1, this.html.video.gate = !1, this.flash.gate = !1
        }, _resetActive: function() {
            this.html.active = !1, this.flash.active = !1
        }, _escapeHtml: function(t) {
            return t.split("&").join("&amp;").split("<").join("&lt;").split(">").join("&gt;").split('"').join("&quot;")
        }, _qualifyURL: function(t) {
            var e = document.createElement("div");
            return e.innerHTML = '<a href="' + this._escapeHtml(t) + '">x</a>', e.firstChild.href
        }, _absoluteMediaUrls: function(e) {
            var n = this;
            return t.each(e, function(t, i) {
                i && n.format[t] && (e[t] = n._qualifyURL(i))
            }), e
        }, setMedia: function(e) {
            var n = this, i = !1, s = this.status.media.poster !== e.poster;
            this._resetMedia(), this._resetGate(), this._resetActive(), this.androidFix.setMedia = !1, this.androidFix.play = !1, this.androidFix.pause = !1, e = this._absoluteMediaUrls(e), t.each(this.formats, function(s, o) {
                var r = "video" === n.format[o].media;
                return t.each(n.solutions, function(s, a) {
                    if (n[a].support[o] && n._validString(e[o])) {
                        var l = "html" === a;
                        return r ? (l ? (n.html.video.gate = !0, n._html_setVideo(e), n.html.active = !0) : (n.flash.gate = !0, n._flash_setVideo(e), n.flash.active = !0), n.css.jq.videoPlay.length && n.css.jq.videoPlay.show(), n.status.video = !0) : (l ? (n.html.audio.gate = !0, n._html_setAudio(e), n.html.active = !0, t.jPlayer.platform.android && (n.androidFix.setMedia = !0)) : (n.flash.gate = !0, n._flash_setAudio(e), n.flash.active = !0), n.css.jq.videoPlay.length && n.css.jq.videoPlay.hide(), n.status.video = !1), i = !0, !1
                    }
                }), i ? !1 : void 0
            }), i ? (this.status.nativeVideoControls && this.html.video.gate || !this._validString(e.poster) || (s ? this.htmlElement.poster.src = e.poster : this.internal.poster.jq.show()), this.css.jq.title.length && "string" == typeof e.title && (this.css.jq.title.html(e.title), this.htmlElement.audio && this.htmlElement.audio.setAttribute("title", e.title), this.htmlElement.video && this.htmlElement.video.setAttribute("title", e.title)), this.status.srcSet = !0, this.status.media = t.extend({}, e), this._updateButtons(!1), this._updateInterface(), this._trigger(t.jPlayer.event.setmedia)) : this._error({type: t.jPlayer.error.NO_SUPPORT, context: "{supplied:'" + this.options.supplied + "'}", message: t.jPlayer.errorMsg.NO_SUPPORT, hint: t.jPlayer.errorHint.NO_SUPPORT})
        }, _resetMedia: function() {
            this._resetStatus(), this._updateButtons(!1), this._updateInterface(), this._seeked(), this.internal.poster.jq.hide(), clearTimeout(this.internal.htmlDlyCmdId), this.html.active ? this._html_resetMedia() : this.flash.active && this._flash_resetMedia()
        }, clearMedia: function() {
            this._resetMedia(), this.html.active ? this._html_clearMedia() : this.flash.active && this._flash_clearMedia(), this._resetGate(), this._resetActive()
        }, load: function() {
            this.status.srcSet ? this.html.active ? this._html_load() : this.flash.active && this._flash_load() : this._urlNotSetError("load")
        }, focus: function() {
            this.options.keyEnabled && (t.jPlayer.focus = this)
        }, play: function(t) {
            t = "number" == typeof t ? t : 0 / 0, this.status.srcSet ? (this.focus(), this.html.active ? this._html_play(t) : this.flash.active && this._flash_play(t)) : this._urlNotSetError("play")
        }, videoPlay: function() {
            this.play()
        }, pause: function(t) {
            t = "number" == typeof t ? t : 0 / 0, this.status.srcSet ? this.html.active ? this._html_pause(t) : this.flash.active && this._flash_pause(t) : this._urlNotSetError("pause")
        }, tellOthers: function(e, n) {
            var i = this, s = "function" == typeof n, o = Array.prototype.slice.call(arguments);
            "string" == typeof e && (s && o.splice(1, 1), t.each(this.instances, function() {
                i.element !== this && (s && !n.call(this.data("jPlayer"), i) || this.jPlayer.apply(this, o))
            }))
        }, pauseOthers: function(t) {
            this.tellOthers("pause", function() {
                return this.status.srcSet
            }, t)
        }, stop: function() {
            this.status.srcSet ? this.html.active ? this._html_pause(0) : this.flash.active && this._flash_pause(0) : this._urlNotSetError("stop")
        }, playHead: function(t) {
            t = this._limitValue(t, 0, 100), this.status.srcSet ? this.html.active ? this._html_playHead(t) : this.flash.active && this._flash_playHead(t) : this._urlNotSetError("playHead")
        }, _muted: function(t) {
            this.mutedWorker(t), this.options.globalVolume && this.tellOthers("mutedWorker", function() {
                return this.options.globalVolume
            }, t)
        }, mutedWorker: function(e) {
            this.options.muted = e, this.html.used && this._html_setProperty("muted", e), this.flash.used && this._flash_mute(e), this.html.video.gate || this.html.audio.gate || (this._updateMute(e), this._updateVolume(this.options.volume), this._trigger(t.jPlayer.event.volumechange))
        }, mute: function(t) {
            t = t === e ? !0 : !!t, this._muted(t)
        }, unmute: function(t) {
            t = t === e ? !0 : !!t, this._muted(!t)
        }, _updateMute: function(t) {
            t === e && (t = this.options.muted), this.css.jq.mute.length && this.css.jq.unmute.length && (this.status.noVolume ? (this.css.jq.mute.hide(), this.css.jq.unmute.hide()) : t ? (this.css.jq.mute.hide(), this.css.jq.unmute.show()) : (this.css.jq.mute.show(), this.css.jq.unmute.hide()))
        }, volume: function(t) {
            this.volumeWorker(t), this.options.globalVolume && this.tellOthers("volumeWorker", function() {
                return this.options.globalVolume
            }, t)
        }, volumeWorker: function(e) {
            e = this._limitValue(e, 0, 1), this.options.volume = e, this.html.used && this._html_setProperty("volume", e), this.flash.used && this._flash_volume(e), this.html.video.gate || this.html.audio.gate || (this._updateVolume(e), this._trigger(t.jPlayer.event.volumechange))
        }, volumeBar: function(e) {
            if (this.css.jq.volumeBar.length) {
                var n = t(e.currentTarget), i = n.offset(), s = e.pageX - i.left, o = n.width();
                e = n.height() - e.pageY + i.top, n = n.height(), this.options.verticalVolume ? this.volume(e / n) : this.volume(s / o)
            }
            this.options.muted && this._muted(!1)
        }, _updateVolume: function(t) {
            t === e && (t = this.options.volume), t = this.options.muted ? 0 : t, this.status.noVolume ? (this.css.jq.volumeBar.length && this.css.jq.volumeBar.hide(), this.css.jq.volumeBarValue.length && this.css.jq.volumeBarValue.hide(), this.css.jq.volumeMax.length && this.css.jq.volumeMax.hide()) : (this.css.jq.volumeBar.length && this.css.jq.volumeBar.show(), this.css.jq.volumeBarValue.length && (this.css.jq.volumeBarValue.show(), this.css.jq.volumeBarValue[this.options.verticalVolume ? "height" : "width"](100 * t + "%")), this.css.jq.volumeMax.length && this.css.jq.volumeMax.show())
        }, volumeMax: function() {
            this.volume(1), this.options.muted && this._muted(!1)
        }, _cssSelectorAncestor: function(e) {
            var n = this;
            this.options.cssSelectorAncestor = e, this._removeUiClass(), this.ancestorJq = e ? t(e) : [], e && 1 !== this.ancestorJq.length && this._warning({type: t.jPlayer.warning.CSS_SELECTOR_COUNT, context: e, message: t.jPlayer.warningMsg.CSS_SELECTOR_COUNT + this.ancestorJq.length + " found for cssSelectorAncestor.", hint: t.jPlayer.warningHint.CSS_SELECTOR_COUNT}), this._addUiClass(), t.each(this.options.cssSelector, function(t, e) {
                n._cssSelector(t, e)
            }), this._updateInterface(), this._updateButtons(), this._updateAutohide(), this._updateVolume(), this._updateMute()
        }, _cssSelector: function(e, n) {
            var i = this;
            "string" == typeof n ? t.jPlayer.prototype.options.cssSelector[e] ? (this.css.jq[e] && this.css.jq[e].length && this.css.jq[e].unbind(".jPlayer"), this.options.cssSelector[e] = n, this.css.cs[e] = this.options.cssSelectorAncestor + " " + n, this.css.jq[e] = n ? t(this.css.cs[e]) : [], this.css.jq[e].length && this[e] && this.css.jq[e].bind("click.jPlayer", function(n) {
                n.preventDefault(), i[e](n), t(this).blur()
            }), n && 1 !== this.css.jq[e].length && this._warning({type: t.jPlayer.warning.CSS_SELECTOR_COUNT, context: this.css.cs[e], message: t.jPlayer.warningMsg.CSS_SELECTOR_COUNT + this.css.jq[e].length + " found for " + e + " method.", hint: t.jPlayer.warningHint.CSS_SELECTOR_COUNT})) : this._warning({type: t.jPlayer.warning.CSS_SELECTOR_METHOD, context: e, message: t.jPlayer.warningMsg.CSS_SELECTOR_METHOD, hint: t.jPlayer.warningHint.CSS_SELECTOR_METHOD}) : this._warning({type: t.jPlayer.warning.CSS_SELECTOR_STRING, context: n, message: t.jPlayer.warningMsg.CSS_SELECTOR_STRING, hint: t.jPlayer.warningHint.CSS_SELECTOR_STRING})
        }, duration: function() {
            this.options.toggleDuration && this._setOption("remainingDuration", !this.options.remainingDuration)
        }, seekBar: function(e) {
            if (this.css.jq.seekBar.length) {
                var n = t(e.currentTarget), i = n.offset();
                e = e.pageX - i.left, n = n.width(), this.playHead(100 * e / n)
            }
        }, playbackRate: function(t) {
            this._setOption("playbackRate", t)
        }, playbackRateBar: function(e) {
            if (this.css.jq.playbackRateBar.length) {
                var n = t(e.currentTarget), i = n.offset(), s = e.pageX - i.left, o = n.width();
                e = n.height() - e.pageY + i.top, n = n.height(), this.playbackRate((this.options.verticalPlaybackRate ? e / n : s / o) * (this.options.maxPlaybackRate - this.options.minPlaybackRate) + this.options.minPlaybackRate)
            }
        }, _updatePlaybackRate: function() {
            var t = (this.options.playbackRate - this.options.minPlaybackRate) / (this.options.maxPlaybackRate - this.options.minPlaybackRate);
            this.status.playbackRateEnabled ? (this.css.jq.playbackRateBar.length && this.css.jq.playbackRateBar.show(), this.css.jq.playbackRateBarValue.length && (this.css.jq.playbackRateBarValue.show(), this.css.jq.playbackRateBarValue[this.options.verticalPlaybackRate ? "height" : "width"](100 * t + "%"))) : (this.css.jq.playbackRateBar.length && this.css.jq.playbackRateBar.hide(), this.css.jq.playbackRateBarValue.length && this.css.jq.playbackRateBarValue.hide())
        }, repeat: function() {
            this._loop(!0)
        }, repeatOff: function() {
            this._loop(!1)
        }, _loop: function(e) {
            this.options.loop !== e && (this.options.loop = e, this._updateButtons(), this._trigger(t.jPlayer.event.repeat))
        }, option: function(n, i) {
            var s = n;
            if (0 === arguments.length)
                return t.extend(!0, {}, this.options);
            if ("string" == typeof n) {
                var o = n.split(".");
                if (i === e) {
                    for (var s = t.extend(!0, {}, this.options), r = 0; r < o.length; r++) {
                        if (s[o[r]] === e)
                            return this._warning({type: t.jPlayer.warning.OPTION_KEY, context: n, message: t.jPlayer.warningMsg.OPTION_KEY, hint: t.jPlayer.warningHint.OPTION_KEY}), e;
                        s = s[o[r]]
                    }
                    return s
                }
                for (var r = s = {}, a = 0; a < o.length; a++)
                    a < o.length - 1 ? (r[o[a]] = {}, r = r[o[a]]) : r[o[a]] = i
            }
            return this._setOptions(s), this
        }, _setOptions: function(e) {
            var n = this;
            return t.each(e, function(t, e) {
                n._setOption(t, e)
            }), this
        }, _setOption: function(e, n) {
            var i = this;
            switch (e) {
                case"volume":
                    this.volume(n);
                    break;
                case"muted":
                    this._muted(n);
                    break;
                case"globalVolume":
                    this.options[e] = n;
                    break;
                case"cssSelectorAncestor":
                    this._cssSelectorAncestor(n);
                    break;
                case"cssSelector":
                    t.each(n, function(t, e) {
                        i._cssSelector(t, e)
                    });
                    break;
                case"playbackRate":
                    this.options[e] = n = this._limitValue(n, this.options.minPlaybackRate, this.options.maxPlaybackRate), this.html.used && this._html_setProperty("playbackRate", n), this._updatePlaybackRate();
                    break;
                case"defaultPlaybackRate":
                    this.options[e] = n = this._limitValue(n, this.options.minPlaybackRate, this.options.maxPlaybackRate), this.html.used && this._html_setProperty("defaultPlaybackRate", n), this._updatePlaybackRate();
                    break;
                case"minPlaybackRate":
                    this.options[e] = n = this._limitValue(n, .1, this.options.maxPlaybackRate - .1), this._updatePlaybackRate();
                    break;
                case"maxPlaybackRate":
                    this.options[e] = n = this._limitValue(n, this.options.minPlaybackRate + .1, 16), this._updatePlaybackRate();
                    break;
                case"fullScreen":
                    if (this.options[e] !== n) {
                        var s = t.jPlayer.nativeFeatures.fullscreen.used.webkitVideo;
                        (!s || s && !this.status.waitForPlay) && (s || (this.options[e] = n), n ? this._requestFullscreen() : this._exitFullscreen(), s || this._setOption("fullWindow", n))
                    }
                    break;
                case"fullWindow":
                    this.options[e] !== n && (this._removeUiClass(), this.options[e] = n, this._refreshSize());
                    break;
                case"size":
                    this.options.fullWindow || this.options[e].cssClass === n.cssClass || this._removeUiClass(), this.options[e] = t.extend({}, this.options[e], n), this._refreshSize();
                    break;
                case"sizeFull":
                    this.options.fullWindow && this.options[e].cssClass !== n.cssClass && this._removeUiClass(), this.options[e] = t.extend({}, this.options[e], n), this._refreshSize();
                    break;
                case"autohide":
                    this.options[e] = t.extend({}, this.options[e], n), this._updateAutohide();
                    break;
                case"loop":
                    this._loop(n);
                    break;
                case"remainingDuration":
                    this.options[e] = n, this._updateInterface();
                    break;
                case"toggleDuration":
                    this.options[e] = n;
                    break;
                case"nativeVideoControls":
                    this.options[e] = t.extend({}, this.options[e], n), this.status.nativeVideoControls = this._uaBlocklist(this.options.nativeVideoControls), this._restrictNativeVideoControls(), this._updateNativeVideoControls();
                    break;
                case"noFullWindow":
                    this.options[e] = t.extend({}, this.options[e], n), this.status.nativeVideoControls = this._uaBlocklist(this.options.nativeVideoControls), this.status.noFullWindow = this._uaBlocklist(this.options.noFullWindow), this._restrictNativeVideoControls(), this._updateButtons();
                    break;
                case"noVolume":
                    this.options[e] = t.extend({}, this.options[e], n), this.status.noVolume = this._uaBlocklist(this.options.noVolume), this._updateVolume(), this._updateMute();
                    break;
                case"emulateHtml":
                    this.options[e] !== n && ((this.options[e] = n) ? this._emulateHtmlBridge() : this._destroyHtmlBridge());
                    break;
                case"timeFormat":
                    this.options[e] = t.extend({}, this.options[e], n);
                    break;
                case"keyEnabled":
                    this.options[e] = n, n || this !== t.jPlayer.focus || (t.jPlayer.focus = null);
                    break;
                case"keyBindings":
                    this.options[e] = t.extend(!0, {}, this.options[e], n);
                    break;
                case"audioFullScreen":
                    this.options[e] = n
            }
            return this
        }, _refreshSize: function() {
            this._setSize(), this._addUiClass(), this._updateSize(), this._updateButtons(), this._updateAutohide(), this._trigger(t.jPlayer.event.resize)
        }, _setSize: function() {
            this.options.fullWindow ? (this.status.width = this.options.sizeFull.width, this.status.height = this.options.sizeFull.height, this.status.cssClass = this.options.sizeFull.cssClass) : (this.status.width = this.options.size.width, this.status.height = this.options.size.height, this.status.cssClass = this.options.size.cssClass), this.element.css({width: this.status.width, height: this.status.height})
        }, _addUiClass: function() {
            this.ancestorJq.length && this.ancestorJq.addClass(this.status.cssClass)
        }, _removeUiClass: function() {
            this.ancestorJq.length && this.ancestorJq.removeClass(this.status.cssClass)
        }, _updateSize: function() {
            this.internal.poster.jq.css({width: this.status.width, height: this.status.height}), !this.status.waitForPlay && this.html.active && this.status.video || this.html.video.available && this.html.used && this.status.nativeVideoControls ? this.internal.video.jq.css({width: this.status.width, height: this.status.height}) : !this.status.waitForPlay && this.flash.active && this.status.video && this.internal.flash.jq.css({width: this.status.width, height: this.status.height})
        }, _updateAutohide: function() {
            var t = this, e = function() {
                t.css.jq.gui.fadeIn(t.options.autohide.fadeIn, function() {
                    clearTimeout(t.internal.autohideId), t.internal.autohideId = setTimeout(function() {
                        t.css.jq.gui.fadeOut(t.options.autohide.fadeOut)
                    }, t.options.autohide.hold)
                })
            };
            this.css.jq.gui.length && (this.css.jq.gui.stop(!0, !0), clearTimeout(this.internal.autohideId), this.element.unbind(".jPlayerAutohide"), this.css.jq.gui.unbind(".jPlayerAutohide"), this.status.nativeVideoControls ? this.css.jq.gui.hide() : this.options.fullWindow && this.options.autohide.full || !this.options.fullWindow && this.options.autohide.restored ? (this.element.bind("mousemove.jPlayer.jPlayerAutohide", e), this.css.jq.gui.bind("mousemove.jPlayer.jPlayerAutohide", e), this.css.jq.gui.hide()) : this.css.jq.gui.show())
        }, fullScreen: function() {
            this._setOption("fullScreen", !0)
        }, restoreScreen: function() {
            this._setOption("fullScreen", !1)
        }, _fullscreenAddEventListeners: function() {
            var e = this, n = t.jPlayer.nativeFeatures.fullscreen;
            n.api.fullscreenEnabled && n.event.fullscreenchange && ("function" != typeof this.internal.fullscreenchangeHandler && (this.internal.fullscreenchangeHandler = function() {
                e._fullscreenchange()
            }), document.addEventListener(n.event.fullscreenchange, this.internal.fullscreenchangeHandler, !1))
        }, _fullscreenRemoveEventListeners: function() {
            var e = t.jPlayer.nativeFeatures.fullscreen;
            this.internal.fullscreenchangeHandler && document.removeEventListener(e.event.fullscreenchange, this.internal.fullscreenchangeHandler, !1)
        }, _fullscreenchange: function() {
            this.options.fullScreen && !t.jPlayer.nativeFeatures.fullscreen.api.fullscreenElement() && this._setOption("fullScreen", !1)
        }, _requestFullscreen: function() {
            var e = this.ancestorJq.length ? this.ancestorJq[0] : this.element[0], n = t.jPlayer.nativeFeatures.fullscreen;
            n.used.webkitVideo && (e = this.htmlElement.video), n.api.fullscreenEnabled && n.api.requestFullscreen(e)
        }, _exitFullscreen: function() {
            var e, n = t.jPlayer.nativeFeatures.fullscreen;
            n.used.webkitVideo && (e = this.htmlElement.video), n.api.fullscreenEnabled && n.api.exitFullscreen(e)
        }, _html_initMedia: function(e) {
            var n = t(this.htmlElement.media).empty();
            t.each(e.track || [], function(t, e) {
                var i = document.createElement("track");
                i.setAttribute("kind", e.kind ? e.kind : ""), i.setAttribute("src", e.src ? e.src : ""), i.setAttribute("srclang", e.srclang ? e.srclang : ""), i.setAttribute("label", e.label ? e.label : ""), e.def && i.setAttribute("default", e.def), n.append(i)
            }), this.htmlElement.media.src = this.status.src, "none" !== this.options.preload && this._html_load(), this._trigger(t.jPlayer.event.timeupdate)
        }, _html_setFormat: function(e) {
            var n = this;
            t.each(this.formats, function(t, i) {
                return n.html.support[i] && e[i] ? (n.status.src = e[i], n.status.format[i] = !0, n.status.formatType = i, !1) : void 0
            })
        }, _html_setAudio: function(t) {
            this._html_setFormat(t), this.htmlElement.media = this.htmlElement.audio, this._html_initMedia(t)
        }, _html_setVideo: function(t) {
            this._html_setFormat(t), this.status.nativeVideoControls && (this.htmlElement.video.poster = this._validString(t.poster) ? t.poster : ""), this.htmlElement.media = this.htmlElement.video, this._html_initMedia(t)
        }, _html_resetMedia: function() {
            this.htmlElement.media && (this.htmlElement.media.id !== this.internal.video.id || this.status.nativeVideoControls || this.internal.video.jq.css({width: "0px", height: "0px"}), this.htmlElement.media.pause())
        }, _html_clearMedia: function() {
            this.htmlElement.media && (this.htmlElement.media.src = "about:blank", this.htmlElement.media.load())
        }, _html_load: function() {
            this.status.waitForLoad && (this.status.waitForLoad = !1, this.htmlElement.media.load()), clearTimeout(this.internal.htmlDlyCmdId)
        }, _html_play: function(t) {
            var e = this, n = this.htmlElement.media;
            if (this.androidFix.pause = !1, this._html_load(), this.androidFix.setMedia)
                this.androidFix.play = !0, this.androidFix.time = t;
            else if (isNaN(t))
                n.play();
            else {
                this.internal.cmdsIgnored && n.play();
                try {
                    if (n.seekable && !("object" == typeof n.seekable && 0 < n.seekable.length))
                        throw 1;
                    n.currentTime = t, n.play()
                } catch (i) {
                    return this.internal.htmlDlyCmdId = setTimeout(function() {
                        e.play(t)
                    }, 250), void 0
                }
            }
            this._html_checkWaitForPlay()
        }, _html_pause: function(t) {
            var e = this, n = this.htmlElement.media;
            if (this.androidFix.play = !1, t > 0 ? this._html_load() : clearTimeout(this.internal.htmlDlyCmdId), n.pause(), this.androidFix.setMedia)
                this.androidFix.pause = !0, this.androidFix.time = t;
            else if (!isNaN(t))
                try {
                    if (n.seekable && !("object" == typeof n.seekable && 0 < n.seekable.length))
                        throw 1;
                    n.currentTime = t
                } catch (i) {
                    return this.internal.htmlDlyCmdId = setTimeout(function() {
                        e.pause(t)
                    }, 250), void 0
                }
            t > 0 && this._html_checkWaitForPlay()
        }, _html_playHead: function(t) {
            var e = this, n = this.htmlElement.media;
            this._html_load();
            try {
                if ("object" == typeof n.seekable && 0 < n.seekable.length)
                    n.currentTime = t * n.seekable.end(n.seekable.length - 1) / 100;
                else {
                    if (!(0 < n.duration) || isNaN(n.duration))
                        throw"e";
                    n.currentTime = t * n.duration / 100
                }
            } catch (i) {
                return this.internal.htmlDlyCmdId = setTimeout(function() {
                    e.playHead(t)
                }, 250), void 0
            }
            this.status.waitForLoad || this._html_checkWaitForPlay()
        }, _html_checkWaitForPlay: function() {
            this.status.waitForPlay && (this.status.waitForPlay = !1, this.css.jq.videoPlay.length && this.css.jq.videoPlay.hide(), this.status.video && (this.internal.poster.jq.hide(), this.internal.video.jq.css({width: this.status.width, height: this.status.height})))
        }, _html_setProperty: function(t, e) {
            this.html.audio.available && (this.htmlElement.audio[t] = e), this.html.video.available && (this.htmlElement.video[t] = e)
        }, _flash_setAudio: function(e) {
            var n = this;
            try {
                t.each(this.formats, function(t, i) {
                    if (n.flash.support[i] && e[i]) {
                        switch (i) {
                            case"m4a":
                            case"fla":
                                n._getMovie().fl_setAudio_m4a(e[i]);
                                break;
                            case"mp3":
                                n._getMovie().fl_setAudio_mp3(e[i]);
                                break;
                            case"rtmpa":
                                n._getMovie().fl_setAudio_rtmp(e[i])
                        }
                        return n.status.src = e[i], n.status.format[i] = !0, n.status.formatType = i, !1
                    }
                }), "auto" === this.options.preload && (this._flash_load(), this.status.waitForLoad = !1)
            } catch (i) {
                this._flashError(i)
            }
        }, _flash_setVideo: function(e) {
            var n = this;
            try {
                t.each(this.formats, function(t, i) {
                    if (n.flash.support[i] && e[i]) {
                        switch (i) {
                            case"m4v":
                            case"flv":
                                n._getMovie().fl_setVideo_m4v(e[i]);
                                break;
                            case"rtmpv":
                                n._getMovie().fl_setVideo_rtmp(e[i])
                        }
                        return n.status.src = e[i], n.status.format[i] = !0, n.status.formatType = i, !1
                    }
                }), "auto" === this.options.preload && (this._flash_load(), this.status.waitForLoad = !1)
            } catch (i) {
                this._flashError(i)
            }
        }, _flash_resetMedia: function() {
            this.internal.flash.jq.css({width: "0px", height: "0px"}), this._flash_pause(0 / 0)
        }, _flash_clearMedia: function() {
            try {
                this._getMovie().fl_clearMedia()
            } catch (t) {
                this._flashError(t)
            }
        }, _flash_load: function() {
            try {
                this._getMovie().fl_load()
            } catch (t) {
                this._flashError(t)
            }
            this.status.waitForLoad = !1
        }, _flash_play: function(t) {
            try {
                this._getMovie().fl_play(t)
            } catch (e) {
                this._flashError(e)
            }
            this.status.waitForLoad = !1, this._flash_checkWaitForPlay()
        }, _flash_pause: function(t) {
            try {
                this._getMovie().fl_pause(t)
            } catch (e) {
                this._flashError(e)
            }
            t > 0 && (this.status.waitForLoad = !1, this._flash_checkWaitForPlay())
        }, _flash_playHead: function(t) {
            try {
                this._getMovie().fl_play_head(t)
            } catch (e) {
                this._flashError(e)
            }
            this.status.waitForLoad || this._flash_checkWaitForPlay()
        }, _flash_checkWaitForPlay: function() {
            this.status.waitForPlay && (this.status.waitForPlay = !1, this.css.jq.videoPlay.length && this.css.jq.videoPlay.hide(), this.status.video && (this.internal.poster.jq.hide(), this.internal.flash.jq.css({width: this.status.width, height: this.status.height})))
        }, _flash_volume: function(t) {
            try {
                this._getMovie().fl_volume(t)
            } catch (e) {
                this._flashError(e)
            }
        }, _flash_mute: function(t) {
            try {
                this._getMovie().fl_mute(t)
            } catch (e) {
                this._flashError(e)
            }
        }, _getMovie: function() {
            return document[this.internal.flash.id]
        }, _getFlashPluginVersion: function() {
            var t, e = 0;
            if (window.ActiveXObject)
                try {
                    if (t = new ActiveXObject("ShockwaveFlash.ShockwaveFlash")) {
                        var n = t.GetVariable("$version");
                        n && (n = n.split(" ")[1].split(","), e = parseInt(n[0], 10) + "." + parseInt(n[1], 10))
                    }
                } catch (i) {
                }
            else
                navigator.plugins && 0 < navigator.mimeTypes.length && (t = navigator.plugins["Shockwave Flash"]) && (e = navigator.plugins["Shockwave Flash"].description.replace(/.*\s(\d+\.\d+).*/, "$1"));
            return 1 * e
        }, _checkForFlash: function(t) {
            var e = !1;
            return this._getFlashPluginVersion() >= t && (e = !0), e
        }, _validString: function(t) {
            return t && "string" == typeof t
        }, _limitValue: function(t, e, n) {
            return e > t ? e : t > n ? n : t
        }, _urlNotSetError: function(e) {
            this._error({type: t.jPlayer.error.URL_NOT_SET, context: e, message: t.jPlayer.errorMsg.URL_NOT_SET, hint: t.jPlayer.errorHint.URL_NOT_SET})
        }, _flashError: function(e) {
            var n;
            n = this.internal.ready ? "FLASH_DISABLED" : "FLASH", this._error({type: t.jPlayer.error[n], context: this.internal.flash.swf, message: t.jPlayer.errorMsg[n] + e.message, hint: t.jPlayer.errorHint[n]}), this.internal.flash.jq.css({width: "1px", height: "1px"})
        }, _error: function(e) {
            this._trigger(t.jPlayer.event.error, e), this.options.errorAlerts && this._alert("Error!" + (e.message ? "\n" + e.message : "") + (e.hint ? "\n" + e.hint : "") + "\nContext: " + e.context)
        }, _warning: function(n) {
            this._trigger(t.jPlayer.event.warning, e, n), this.options.warningAlerts && this._alert("Warning!" + (n.message ? "\n" + n.message : "") + (n.hint ? "\n" + n.hint : "") + "\nContext: " + n.context)
        }, _alert: function(t) {
            t = "jPlayer " + this.version.script + " : id='" + this.internal.self.id + "' : " + t, this.options.consoleAlerts ? window.console && window.console.log && window.console.log(t) : alert(t)
        }, _emulateHtmlBridge: function() {
            var e = this;
            t.each(t.jPlayer.emulateMethods.split(/\s+/g), function(t, n) {
                e.internal.domNode[n] = function(t) {
                    e[n](t)
                }
            }), t.each(t.jPlayer.event, function(n, i) {
                var s = !0;
                t.each(t.jPlayer.reservedEvent.split(/\s+/g), function(t, e) {
                    return e === n ? s = !1 : void 0
                }), s && e.element.bind(i + ".jPlayer.jPlayerHtml", function() {
                    e._emulateHtmlUpdate();
                    var t = document.createEvent("Event");
                    t.initEvent(n, !1, !0), e.internal.domNode.dispatchEvent(t)
                })
            })
        }, _emulateHtmlUpdate: function() {
            var e = this;
            t.each(t.jPlayer.emulateStatus.split(/\s+/g), function(t, n) {
                e.internal.domNode[n] = e.status[n]
            }), t.each(t.jPlayer.emulateOptions.split(/\s+/g), function(t, n) {
                e.internal.domNode[n] = e.options[n]
            })
        }, _destroyHtmlBridge: function() {
            var e = this;
            this.element.unbind(".jPlayerHtml"), t.each((t.jPlayer.emulateMethods + " " + t.jPlayer.emulateStatus + " " + t.jPlayer.emulateOptions).split(/\s+/g), function(t, n) {
                delete e.internal.domNode[n]
            })
        }}, t.jPlayer.error = {FLASH: "e_flash", FLASH_DISABLED: "e_flash_disabled", NO_SOLUTION: "e_no_solution", NO_SUPPORT: "e_no_support", URL: "e_url", URL_NOT_SET: "e_url_not_set", VERSION: "e_version"}, t.jPlayer.errorMsg = {FLASH: "jPlayer's Flash fallback is not configured correctly, or a command was issued before the jPlayer Ready event. Details: ", FLASH_DISABLED: "jPlayer's Flash fallback has been disabled by the browser due to the CSS rules you have used. Details: ", NO_SOLUTION: "No solution can be found by jPlayer in this browser. Neither HTML nor Flash can be used.", NO_SUPPORT: "It is not possible to play any media format provided in setMedia() on this browser using your current options.", URL: "Media URL could not be loaded.", URL_NOT_SET: "Attempt to issue media playback commands, while no media url is set.", VERSION: "jPlayer " + t.jPlayer.prototype.version.script + " needs Jplayer.swf version " + t.jPlayer.prototype.version.needFlash + " but found "}, t.jPlayer.errorHint = {FLASH: "Check your swfPath option and that Jplayer.swf is there.", FLASH_DISABLED: "Check that you have not display:none; the jPlayer entity or any ancestor.", NO_SOLUTION: "Review the jPlayer options: support and supplied.", NO_SUPPORT: "Video or audio formats defined in the supplied option are missing.", URL: "Check media URL is valid.", URL_NOT_SET: "Use setMedia() to set the media URL.", VERSION: "Update jPlayer files."}, t.jPlayer.warning = {CSS_SELECTOR_COUNT: "e_css_selector_count", CSS_SELECTOR_METHOD: "e_css_selector_method", CSS_SELECTOR_STRING: "e_css_selector_string", OPTION_KEY: "e_option_key"}, t.jPlayer.warningMsg = {CSS_SELECTOR_COUNT: "The number of css selectors found did not equal one: ", CSS_SELECTOR_METHOD: "The methodName given in jPlayer('cssSelector') is not a valid jPlayer method.", CSS_SELECTOR_STRING: "The methodCssSelector given in jPlayer('cssSelector') is not a String or is empty.", OPTION_KEY: "The option requested in jPlayer('option') is undefined."}, t.jPlayer.warningHint = {CSS_SELECTOR_COUNT: "Check your css selector and the ancestor.", CSS_SELECTOR_METHOD: "Check your method name.", CSS_SELECTOR_STRING: "Check your css selector is a string.", OPTION_KEY: "Check your option name."}
}), /*! jquery.kinetic - v1.8.2 - 2013-03-23 http://the-taylors.org/jquery.kinetic 
 * Copyright (c) 2013 Dave Taylor; Licensed MIT */
        function(t) {
            "use strict";
            var e = {cursor: "move", decelerate: !0, triggerHardware: !1, y: !0, x: !0, slowdown: .9, maxvelocity: 40, throttleFPS: 60, movingClass: {up: "kinetic-moving-up", down: "kinetic-moving-down", left: "kinetic-moving-left", right: "kinetic-moving-right"}, deceleratingClass: {up: "kinetic-decelerating-up", down: "kinetic-decelerating-down", left: "kinetic-decelerating-left", right: "kinetic-decelerating-right"}}, n = "kinetic-settings", i = "kinetic-active";
            window.requestAnimationFrame || (window.requestAnimationFrame = function() {
                return window.webkitRequestAnimationFrame || window.mozRequestAnimationFrame || window.oRequestAnimationFrame || window.msRequestAnimationFrame || function(t) {
                    window.setTimeout(t, 1e3 / 60)
                }
            }()), t.support = t.support || {}, t.extend(t.support, {touch: "ontouchend"in document});
            var s = function() {
                return!1
            }, o = function(t, e) {
                return 0 === Math.floor(Math.abs(t)) ? 0 : t * e
            }, r = function(t, e) {
                var n = t;
                return t > 0 ? t > e && (n = e) : 0 - e > t && (n = 0 - e), n
            }, a = function(t, e) {
                this.removeClass(t.movingClass.up).removeClass(t.movingClass.down).removeClass(t.movingClass.left).removeClass(t.movingClass.right).removeClass(t.deceleratingClass.up).removeClass(t.deceleratingClass.down).removeClass(t.deceleratingClass.left).removeClass(t.deceleratingClass.right), t.velocity > 0 && this.addClass(e.right), 0 > t.velocity && this.addClass(e.left), t.velocityY > 0 && this.addClass(e.down), 0 > t.velocityY && this.addClass(e.up)
            }, l = function(t, e) {
                e.velocity = 0, e.velocityY = 0, e.decelerate = !0, "function" == typeof e.stopped && e.stopped.call(t, e)
            }, u = function(t, e) {
                var n = t[0];
                e.x && n.scrollWidth > 0 ? (n.scrollLeft = e.scrollLeft = n.scrollLeft + e.velocity, Math.abs(e.velocity) > 0 && (e.velocity = e.decelerate ? o(e.velocity, e.slowdown) : e.velocity)) : e.velocity = 0, e.y && n.scrollHeight > 0 ? (n.scrollTop = e.scrollTop = n.scrollTop + e.velocityY, Math.abs(e.velocityY) > 0 && (e.velocityY = e.decelerate ? o(e.velocityY, e.slowdown) : e.velocityY)) : e.velocityY = 0, a.call(t, e, e.deceleratingClass), "function" == typeof e.moved && e.moved.call(t, e), Math.abs(e.velocity) > 0 || Math.abs(e.velocityY) > 0 ? window.requestAnimationFrame(function() {
                    u(t, e)
                }) : l(t, e)
            }, c = function(e) {
                var i = t.kinetic.callMethods[e], s = Array.prototype.slice.call(arguments);
                i && this.each(function() {
                    var e = s.slice(1), o = t(this).data(n);
                    e.unshift(o), i.apply(this, e)
                })
            }, h = function(e, n) {
                e[0], t.support.touch ? e.bind("touchstart", n.events.touchStart).bind("touchend", n.events.inputEnd).bind("touchmove", n.events.touchMove) : e.mousedown(n.events.inputDown).mouseup(n.events.inputEnd).mousemove(n.events.inputMove), e.click(n.events.inputClick).scroll(n.events.scroll).bind("selectstart", s).bind("dragstart", n.events.dragStart)
            }, d = function(e, n) {
                e[0], t.support.touch ? e.unbind("touchstart", n.events.touchStart).unbind("touchend", n.events.inputEnd).unbind("touchmove", n.events.touchMove) : e.unbind("mousedown", n.events.inputDown).unbind("mouseup", n.events.inputEnd).unbind("mousemove", n.events.inputMove).unbind("scroll", n.events.scroll), e.unbind("click", n.events.inputClick).unbind("selectstart", s), e.unbind("dragstart", n.events.dragStart)
            }, p = function(s) {
                this.addClass(i).each(function() {
                    var i = this, o = t(this);
                    if (!o.data(n)) {
                        var l, c, d, p, f = t.extend({}, e, s), m = !1, v = !1, g = !1, y = 1e3 / f.throttleFPS;
                        f.velocity = 0, f.velocityY = 0;
                        var b = function() {
                            l = !1, c = !1, g = !1
                        };
                        t(document).mouseup(b).click(b);
                        var w = function() {
                            f.velocity = r(m - l, f.maxvelocity), f.velocityY = r(v - c, f.maxvelocity)
                        }, x = function(e) {
                            return t.isFunction(f.filterTarget) ? f.filterTarget.call(i, e) !== !1 : !0
                        }, _ = function(t, e) {
                            g = !0, f.velocity = m = 0, f.velocityY = v = 0, l = t, c = e
                        }, S = function() {
                            l && m && f.decelerate === !1 && (f.decelerate = !0, w(), l = m = g = !1, u(o, f))
                        }, k = function(e, n) {
                            (!d || new Date > new Date(d.getTime() + y)) && (d = new Date, g && (l || c) && (p && (t(p).blur(), p = null, o.focus()), f.decelerate = !1, f.velocity = f.velocityY = 0, o[0].scrollLeft = f.scrollLeft = f.x ? o[0].scrollLeft - (e - l) : o[0].scrollLeft, o[0].scrollTop = f.scrollTop = f.y ? o[0].scrollTop - (n - c) : o[0].scrollTop, m = l, v = c, l = e, c = n, w(), a.call(o, f, f.movingClass), "function" == typeof f.moved && f.moved.call(o, f)))
                        };
                        f.events = {touchStart: function(t) {
                                var e;
                                x(t.target) && (e = t.originalEvent.touches[0], _(e.clientX, e.clientY), t.stopPropagation())
                            }, touchMove: function(t) {
                                var e;
                                g && (e = t.originalEvent.touches[0], k(e.clientX, e.clientY), t.preventDefault && t.preventDefault())
                            }, inputDown: function(t) {
                                x(t.target) && (_(t.clientX, t.clientY), p = t.target, "IMG" === t.target.nodeName && t.preventDefault(), t.stopPropagation())
                            }, inputEnd: function(t) {
                                S(), p = null, t.preventDefault && t.preventDefault()
                            }, inputMove: function(t) {
                                g && (k(t.clientX, t.clientY), t.preventDefault && t.preventDefault())
                            }, scroll: function(t) {
                                "function" == typeof f.moved && f.moved.call(o, f), t.preventDefault && t.preventDefault()
                            }, inputClick: function(t) {
                                return Math.abs(f.velocity) > 0 ? (t.preventDefault(), !1) : void 0
                            }, dragStart: function() {
                                return p ? !1 : void 0
                            }}, h(o, f), o.data(n, f).css("cursor", f.cursor), f.triggerHardware && o.css({"-webkit-transform": "translate3d(0,0,0)", "-webkit-perspective": "1000", "-webkit-backface-visibility": "hidden"})
                    }
                })
            };
            t.kinetic = {settingsKey: n, callMethods: {start: function(e, n) {
                        var i = t(this);
                        e = t.extend(e, n), e && (e.decelerate = !1, u(i, e))
                    }, end: function(e) {
                        t(this), e && (e.decelerate = !0)
                    }, stop: function(e) {
                        var n = t(this);
                        l(n, e)
                    }, detach: function(e) {
                        var n = t(this);
                        d(n, e), n.removeClass(i).css("cursor", "")
                    }, attach: function(e) {
                        var n = t(this);
                        h(n, e), n.addClass(i).css("cursor", "move")
                    }}}, t.fn.kinetic = function(t) {
                return"string" == typeof t ? c.apply(this, arguments) : p.call(this, t), this
            }
        }(window.jQuery || window.Zepto), /*! Copyright (c) 2013 Brandon Aaron (http://brandon.aaron.sh)
         * Licensed under the MIT License (LICENSE.txt).
         *
         * Version: 3.1.12
         *
         * Requires: jQuery 1.2.2+
         */
        function(t) {
            "function" == typeof define && define.amd ? define(["jquery"], t) : "object" == typeof exports ? module.exports = t : t(jQuery)
        }(function(t) {
    function e(e) {
        var r = e || window.event, a = l.call(arguments, 1), u = 0, h = 0, d = 0, p = 0, f = 0, m = 0;
        if (e = t.event.fix(r), e.type = "mousewheel", "detail"in r && (d = -1 * r.detail), "wheelDelta"in r && (d = r.wheelDelta), "wheelDeltaY"in r && (d = r.wheelDeltaY), "wheelDeltaX"in r && (h = -1 * r.wheelDeltaX), "axis"in r && r.axis === r.HORIZONTAL_AXIS && (h = -1 * d, d = 0), u = 0 === d ? h : d, "deltaY"in r && (d = -1 * r.deltaY, u = d), "deltaX"in r && (h = r.deltaX, 0 === d && (u = -1 * h)), 0 !== d || 0 !== h) {
            if (1 === r.deltaMode) {
                var v = t.data(this, "mousewheel-line-height");
                u *= v, d *= v, h *= v
            } else if (2 === r.deltaMode) {
                var g = t.data(this, "mousewheel-page-height");
                u *= g, d *= g, h *= g
            }
            if (p = Math.max(Math.abs(d), Math.abs(h)), (!o || o > p) && (o = p, i(r, p) && (o /= 40)), i(r, p) && (u /= 40, h /= 40, d /= 40), u = Math[u >= 1 ? "floor" : "ceil"](u / o), h = Math[h >= 1 ? "floor" : "ceil"](h / o), d = Math[d >= 1 ? "floor" : "ceil"](d / o), c.settings.normalizeOffset && this.getBoundingClientRect) {
                var y = this.getBoundingClientRect();
                f = e.clientX - y.left, m = e.clientY - y.top
            }
            return e.deltaX = h, e.deltaY = d, e.deltaFactor = o, e.offsetX = f, e.offsetY = m, e.deltaMode = 0, a.unshift(e, u, h, d), s && clearTimeout(s), s = setTimeout(n, 200), (t.event.dispatch || t.event.handle).apply(this, a)
        }
    }
    function n() {
        o = null
    }
    function i(t, e) {
        return c.settings.adjustOldDeltas && "mousewheel" === t.type && e % 120 === 0
    }
    var s, o, r = ["wheel", "mousewheel", "DOMMouseScroll", "MozMousePixelScroll"], a = "onwheel"in document || document.documentMode >= 9 ? ["wheel"] : ["mousewheel", "DomMouseScroll", "MozMousePixelScroll"], l = Array.prototype.slice;
    if (t.event.fixHooks)
        for (var u = r.length; u; )
            t.event.fixHooks[r[--u]] = t.event.mouseHooks;
    var c = t.event.special.mousewheel = {version: "3.1.12", setup: function() {
            if (this.addEventListener)
                for (var n = a.length; n; )
                    this.addEventListener(a[--n], e, !1);
            else
                this.onmousewheel = e;
            t.data(this, "mousewheel-line-height", c.getLineHeight(this)), t.data(this, "mousewheel-page-height", c.getPageHeight(this))
        }, teardown: function() {
            if (this.removeEventListener)
                for (var n = a.length; n; )
                    this.removeEventListener(a[--n], e, !1);
            else
                this.onmousewheel = null;
            t.removeData(this, "mousewheel-line-height"), t.removeData(this, "mousewheel-page-height")
        }, getLineHeight: function(e) {
            var n = t(e), i = n["offsetParent"in t.fn ? "offsetParent" : "parent"]();
            return i.length || (i = t("body")), parseInt(i.css("fontSize"), 10) || parseInt(n.css("fontSize"), 10) || 16
        }, getPageHeight: function(e) {
            return t(e).height()
        }, settings: {adjustOldDeltas: !0, normalizeOffset: !0}};
    t.fn.extend({mousewheel: function(t) {
            return t ? this.bind("mousewheel", t) : this.trigger("mousewheel")
        }, unmousewheel: function(t) {
            return this.unbind("mousewheel", t)
        }})
}), /*
 * jQuery SmoothDivScroll 1.3
 *
 * Copyright (c) 2013 Thomas Kahn
 * Licensed under the GPL license.
 *
 * http://www.smoothdivscroll.com/
 */
        function(t) {
            t.widget("thomaskahn.smoothDivScroll", {options: {scrollingHotSpotLeftClass: "scrollingHotSpotLeft", scrollingHotSpotRightClass: "scrollingHotSpotRight", scrollingHotSpotLeftVisibleClass: "scrollingHotSpotLeftVisible", scrollingHotSpotRightVisibleClass: "scrollingHotSpotRightVisible", scrollableAreaClass: "scrollableArea", scrollWrapperClass: "scrollWrapper", hiddenOnStart: !1, getContentOnLoad: {}, countOnlyClass: "", startAtElementId: "", hotSpotScrolling: !0, hotSpotScrollingStep: 15, hotSpotScrollingInterval: 10, hotSpotMouseDownSpeedBooster: 3, visibleHotSpotBackgrounds: "hover", hotSpotsVisibleTime: 5e3, easingAfterHotSpotScrolling: !0, easingAfterHotSpotScrollingDistance: 10, easingAfterHotSpotScrollingDuration: 300, easingAfterHotSpotScrollingFunction: "easeOutQuart", mousewheelScrolling: "", mousewheelScrollingStep: 70, easingAfterMouseWheelScrolling: !0, easingAfterMouseWheelScrollingDuration: 300, easingAfterMouseWheelScrollingFunction: "easeOutQuart", manualContinuousScrolling: !1, autoScrollingMode: "", autoScrollingDirection: "endlessLoopRight", autoScrollingStep: 1, autoScrollingInterval: 10, touchScrolling: !1, scrollToAnimationDuration: 1e3, scrollToEasingFunction: "easeOutQuart"}, _create: function() {
                    var e = this, n = this.options, i = this.element;
                    i.data("scrollWrapper", i.find("." + n.scrollWrapperClass)), i.data("scrollingHotSpotRight", i.find("." + n.scrollingHotSpotRightClass)), i.data("scrollingHotSpotLeft", i.find("." + n.scrollingHotSpotLeftClass)), i.data("scrollableArea", i.find("." + n.scrollableAreaClass)), i.data("scrollingHotSpotRight").length > 0 && i.data("scrollingHotSpotRight").detach(), i.data("scrollingHotSpotLeft").length > 0 && i.data("scrollingHotSpotLeft").detach(), 0 === i.data("scrollableArea").length && 0 === i.data("scrollWrapper").length ? (i.wrapInner("<div class='" + n.scrollableAreaClass + "'>").wrapInner("<div class='" + n.scrollWrapperClass + "'>"), i.data("scrollWrapper", i.find("." + n.scrollWrapperClass)), i.data("scrollableArea", i.find("." + n.scrollableAreaClass))) : 0 === i.data("scrollWrapper").length ? (i.wrapInner("<div class='" + n.scrollWrapperClass + "'>"), i.data("scrollWrapper", i.find("." + n.scrollWrapperClass))) : 0 === i.data("scrollableArea").length && (i.data("scrollWrapper").wrapInner("<div class='" + n.scrollableAreaClass + "'>"), i.data("scrollableArea", i.find("." + n.scrollableAreaClass))), 0 === i.data("scrollingHotSpotRight").length ? (i.prepend("<div class='" + n.scrollingHotSpotRightClass + "'></div>"), i.data("scrollingHotSpotRight", i.find("." + n.scrollingHotSpotRightClass))) : i.prepend(i.data("scrollingHotSpotRight")), 0 === i.data("scrollingHotSpotLeft").length ? (i.prepend("<div class='" + n.scrollingHotSpotLeftClass + "'></div>"), i.data("scrollingHotSpotLeft", i.find("." + n.scrollingHotSpotLeftClass))) : i.prepend(i.data("scrollingHotSpotLeft")), i.data("speedBooster", 1), i.data("scrollXPos", 0), i.data("hotSpotWidth", i.data("scrollingHotSpotLeft").innerWidth()), i.data("scrollableAreaWidth", 0), i.data("startingPosition", 0), i.data("rightScrollingInterval", null), i.data("leftScrollingInterval", null), i.data("autoScrollingInterval", null), i.data("hideHotSpotBackgroundsInterval", null), i.data("previousScrollLeft", 0), i.data("pingPongDirection", "right"), i.data("getNextElementWidth", !0), i.data("swapAt", null), i.data("startAtElementHasNotPassed", !0), i.data("swappedElement", null), i.data("originalElements", i.data("scrollableArea").children(n.countOnlyClass)), i.data("visible", !0), i.data("enabled", !0), i.data("scrollableAreaHeight", i.data("scrollableArea").height()), i.data("scrollerOffset", i.offset()), n.touchScrolling && i.data("enabled") && i.data("scrollWrapper").kinetic({y: !1, moved: function() {
                            n.manualContinuousScrolling && (i.data("scrollWrapper").scrollLeft() <= 0 ? e._checkContinuousSwapLeft() : e._checkContinuousSwapRight()), e._trigger("touchMoved")
                        }, stopped: function() {
                            i.data("scrollWrapper").stop(!0, !1), e.stopAutoScrolling(), e._trigger("touchStopped")
                        }}), i.data("scrollingHotSpotRight").bind("mousemove", function(e) {
                        if (n.hotSpotScrolling) {
                            var s = e.pageX - t(this).offset().left;
                            i.data("scrollXPos", Math.round(s / i.data("hotSpotWidth") * n.hotSpotScrollingStep)), (1 / 0 === i.data("scrollXPos") || i.data("scrollXPos") < 1) && i.data("scrollXPos", 1)
                        }
                    }), i.data("scrollingHotSpotRight").bind("mouseover", function() {
                        n.hotSpotScrolling && (i.data("scrollWrapper").stop(!0, !1), e.stopAutoScrolling(), i.data("rightScrollingInterval", setInterval(function() {
                            i.data("scrollXPos") > 0 && i.data("enabled") && (i.data("scrollWrapper").scrollLeft(i.data("scrollWrapper").scrollLeft() + i.data("scrollXPos") * i.data("speedBooster")), n.manualContinuousScrolling && e._checkContinuousSwapRight(), e._showHideHotSpots())
                        }, n.hotSpotScrollingInterval)), e._trigger("mouseOverRightHotSpot"))
                    }), i.data("scrollingHotSpotRight").bind("mouseout", function() {
                        n.hotSpotScrolling && (clearInterval(i.data("rightScrollingInterval")), i.data("scrollXPos", 0), n.easingAfterHotSpotScrolling && i.data("enabled") && i.data("scrollWrapper").animate({scrollLeft: i.data("scrollWrapper").scrollLeft() + n.easingAfterHotSpotScrollingDistance}, {duration: n.easingAfterHotSpotScrollingDuration, easing: n.easingAfterHotSpotScrollingFunction}))
                    }), i.data("scrollingHotSpotRight").bind("mousedown", function() {
                        i.data("speedBooster", n.hotSpotMouseDownSpeedBooster)
                    }), t("body").bind("mouseup", function() {
                        i.data("speedBooster", 1)
                    }), i.data("scrollingHotSpotLeft").bind("mousemove", function(e) {
                        if (n.hotSpotScrolling) {
                            var s = i.data("hotSpotWidth") - (e.pageX - t(this).offset().left);
                            i.data("scrollXPos", Math.round(s / i.data("hotSpotWidth") * n.hotSpotScrollingStep)), (1 / 0 === i.data("scrollXPos") || i.data("scrollXPos") < 1) && i.data("scrollXPos", 1)
                        }
                    }), i.data("scrollingHotSpotLeft").bind("mouseover", function() {
                        n.hotSpotScrolling && (i.data("scrollWrapper").stop(!0, !1), e.stopAutoScrolling(), i.data("leftScrollingInterval", setInterval(function() {
                            i.data("scrollXPos") > 0 && i.data("enabled") && (i.data("scrollWrapper").scrollLeft(i.data("scrollWrapper").scrollLeft() - i.data("scrollXPos") * i.data("speedBooster")), n.manualContinuousScrolling && e._checkContinuousSwapLeft(), e._showHideHotSpots())
                        }, n.hotSpotScrollingInterval)), e._trigger("mouseOverLeftHotSpot"))
                    }), i.data("scrollingHotSpotLeft").bind("mouseout", function() {
                        n.hotSpotScrolling && (clearInterval(i.data("leftScrollingInterval")), i.data("scrollXPos", 0), n.easingAfterHotSpotScrolling && i.data("enabled") && i.data("scrollWrapper").animate({scrollLeft: i.data("scrollWrapper").scrollLeft() - n.easingAfterHotSpotScrollingDistance}, {duration: n.easingAfterHotSpotScrollingDuration, easing: n.easingAfterHotSpotScrollingFunction}))
                    }), i.data("scrollingHotSpotLeft").bind("mousedown", function() {
                        i.data("speedBooster", n.hotSpotMouseDownSpeedBooster)
                    }), i.data("scrollableArea").mousewheel(function(t, s, o, r) {
                        if (i.data("enabled") && n.mousewheelScrolling.length > 0) {
                            var a;
                            "vertical" === n.mousewheelScrolling && 0 !== r ? (e.stopAutoScrolling(), t.preventDefault(), a = Math.round(n.mousewheelScrollingStep * r * -1), e.move(a)) : "horizontal" === n.mousewheelScrolling && 0 !== o ? (e.stopAutoScrolling(), t.preventDefault(), a = Math.round(n.mousewheelScrollingStep * o * -1), e.move(a)) : "allDirections" === n.mousewheelScrolling && (e.stopAutoScrolling(), t.preventDefault(), a = Math.round(n.mousewheelScrollingStep * s * -1), e.move(a))
                        }
                    }), n.mousewheelScrolling && i.data("scrollingHotSpotLeft").add(i.data("scrollingHotSpotRight")).mousewheel(function(t) {
                        t.preventDefault()
                    }), t(window).bind("resize", function() {
                        e._showHideHotSpots(), e._trigger("windowResized")
                    }), jQuery.isEmptyObject(n.getContentOnLoad) || e[n.getContentOnLoad.method](n.getContentOnLoad.content, n.getContentOnLoad.manipulationMethod, n.getContentOnLoad.addWhere, n.getContentOnLoad.filterTag), n.hiddenOnStart && e.hide(), t(window).load(function() {
                        if (n.hiddenOnStart || e.recalculateScrollableArea(), n.autoScrollingMode.length > 0 && !n.hiddenOnStart && e.startAutoScrolling(), "always" !== n.autoScrollingMode)
                            switch (n.visibleHotSpotBackgrounds) {
                                case"always":
                                    e.showHotSpotBackgrounds();
                                    break;
                                case"onStart":
                                    e.showHotSpotBackgrounds(), i.data("hideHotSpotBackgroundsInterval", setTimeout(function() {
                                        e.hideHotSpotBackgrounds(250)
                                    }, n.hotSpotsVisibleTime));
                                    break;
                                case"hover":
                                    i.mouseenter(function(t) {
                                        n.hotSpotScrolling && (t.stopPropagation(), e.showHotSpotBackgrounds(250))
                                    }).mouseleave(function(t) {
                                        n.hotSpotScrolling && (t.stopPropagation(), e.hideHotSpotBackgrounds(250))
                                    })
                            }
                        e._showHideHotSpots(), e._trigger("setupComplete")
                    })
                }, _init: function() {
                    {
                        var t = this;
                        this.element
                    }
                    t.recalculateScrollableArea(), t._showHideHotSpots(), t._trigger("initializationComplete")
                }, _setOption: function(t, e) {
                    var n = this, i = this.options, s = this.element;
                    i[t] = e, "hotSpotScrolling" === t ? e === !0 ? n._showHideHotSpots() : (s.data("scrollingHotSpotLeft").hide(), s.data("scrollingHotSpotRight").hide()) : "autoScrollingStep" === t || "easingAfterHotSpotScrollingDistance" === t || "easingAfterHotSpotScrollingDuration" === t || "easingAfterMouseWheelScrollingDuration" === t ? i[t] = parseInt(e, 10) : "autoScrollingInterval" === t && (i[t] = parseInt(e, 10), n.startAutoScrolling())
                }, showHotSpotBackgrounds: function(t) {
                    var e = this, n = this.element, i = this.options;
                    void 0 !== t ? (n.data("scrollingHotSpotLeft").addClass(i.scrollingHotSpotLeftVisibleClass), n.data("scrollingHotSpotRight").addClass(i.scrollingHotSpotRightVisibleClass), n.data("scrollingHotSpotLeft").add(n.data("scrollingHotSpotRight")).fadeTo(t, .35)) : (n.data("scrollingHotSpotLeft").addClass(i.scrollingHotSpotLeftVisibleClass), n.data("scrollingHotSpotLeft").removeAttr("style"), n.data("scrollingHotSpotRight").addClass(i.scrollingHotSpotRightVisibleClass), n.data("scrollingHotSpotRight").removeAttr("style")), e._showHideHotSpots()
                }, hideHotSpotBackgrounds: function(t) {
                    var e = this.element, n = this.options;
                    void 0 !== t ? (e.data("scrollingHotSpotLeft").fadeTo(t, 0, function() {
                        e.data("scrollingHotSpotLeft").removeClass(n.scrollingHotSpotLeftVisibleClass)
                    }), e.data("scrollingHotSpotRight").fadeTo(t, 0, function() {
                        e.data("scrollingHotSpotRight").removeClass(n.scrollingHotSpotRightVisibleClass)
                    })) : (e.data("scrollingHotSpotLeft").removeClass(n.scrollingHotSpotLeftVisibleClass).removeAttr("style"), e.data("scrollingHotSpotRight").removeClass(n.scrollingHotSpotRightVisibleClass).removeAttr("style"))
                }, _showHideHotSpots: function() {
                    var t = this, e = this.element, n = this.options;
                    n.hotSpotScrolling ? n.hotSpotScrolling && "always" !== n.autoScrollingMode && null !== e.data("autoScrollingInterval") ? (e.data("scrollingHotSpotLeft").show(), e.data("scrollingHotSpotRight").show()) : "always" !== n.autoScrollingMode && n.hotSpotScrolling ? e.data("scrollableAreaWidth") <= e.data("scrollWrapper").innerWidth() ? (e.data("scrollingHotSpotLeft").hide(), e.data("scrollingHotSpotRight").hide()) : 0 === e.data("scrollWrapper").scrollLeft() ? (e.data("scrollingHotSpotLeft").hide(), e.data("scrollingHotSpotRight").show(), t._trigger("scrollerLeftLimitReached"), clearInterval(e.data("leftScrollingInterval")), e.data("leftScrollingInterval", null)) : e.data("scrollableAreaWidth") <= e.data("scrollWrapper").innerWidth() + e.data("scrollWrapper").scrollLeft() ? (e.data("scrollingHotSpotLeft").show(), e.data("scrollingHotSpotRight").hide(), t._trigger("scrollerRightLimitReached"), clearInterval(e.data("rightScrollingInterval")), e.data("rightScrollingInterval", null)) : (e.data("scrollingHotSpotLeft").show(), e.data("scrollingHotSpotRight").show()) : (e.data("scrollingHotSpotLeft").hide(), e.data("scrollingHotSpotRight").hide()) : (e.data("scrollingHotSpotLeft").hide(), e.data("scrollingHotSpotRight").hide())
                }, _setElementScrollPosition: function(e, n) {
                    var i = this.element, s = this.options, o = 0;
                    switch (e) {
                        case"first":
                            return i.data("scrollXPos", 0), !0;
                        case"start":
                            return"" !== s.startAtElementId && i.data("scrollableArea").has("#" + s.startAtElementId) ? (o = t("#" + s.startAtElementId).position().left, i.data("scrollXPos", o), !0) : !1;
                        case"last":
                            return i.data("scrollXPos", i.data("scrollableAreaWidth") - i.data("scrollWrapper").innerWidth()), !0;
                        case"number":
                            return isNaN(n) ? !1 : (o = i.data("scrollableArea").children(s.countOnlyClass).eq(n - 1).position().left, i.data("scrollXPos", o), !0);
                        case"id":
                            return n.length > 0 && i.data("scrollableArea").has("#" + n) ? (o = t("#" + n).position().left, i.data("scrollXPos", o), !0) : !1;
                        default:
                            return!1
                    }
                }, jumpToElement: function(t, e) {
                    var n = this, i = this.element;
                    if (i.data("enabled") && n._setElementScrollPosition(t, e))
                        switch (i.data("scrollWrapper").scrollLeft(i.data("scrollXPos")), n._showHideHotSpots(), t) {
                            case"first":
                                n._trigger("jumpedToFirstElement");
                                break;
                            case"start":
                                n._trigger("jumpedToStartElement");
                                break;
                            case"last":
                                n._trigger("jumpedToLastElement");
                                break;
                            case"number":
                                n._trigger("jumpedToElementNumber", null, {elementNumber: e});
                                break;
                            case"id":
                                n._trigger("jumpedToElementId", null, {elementId: e})
                        }
                }, scrollToElement: function(t, e) {
                    var n = this, i = this.element, s = this.options, o = !1;
                    i.data("enabled") && n._setElementScrollPosition(t, e) && (null !== i.data("autoScrollingInterval") && (n.stopAutoScrolling(), o = !0), i.data("scrollWrapper").stop(!0, !1), i.data("scrollWrapper").animate({scrollLeft: i.data("scrollXPos")}, {duration: s.scrollToAnimationDuration, easing: s.scrollToEasingFunction, complete: function() {
                            switch (o && n.startAutoScrolling(), n._showHideHotSpots(), t) {
                                case"first":
                                    n._trigger("scrolledToFirstElement");
                                    break;
                                case"start":
                                    n._trigger("scrolledToStartElement");
                                    break;
                                case"last":
                                    n._trigger("scrolledToLastElement");
                                    break;
                                case"number":
                                    n._trigger("scrolledToElementNumber", null, {elementNumber: e});
                                    break;
                                case"id":
                                    n._trigger("scrolledToElementId", null, {elementId: e})
                            }
                        }}))
                }, move: function(t) {
                    var e = this, n = this.element, i = this.options;
                    if (n.data("scrollWrapper").stop(!0, !0), 0 > t && n.data("scrollWrapper").scrollLeft() > 0 || t > 0 && n.data("scrollableAreaWidth") > n.data("scrollWrapper").innerWidth() + n.data("scrollWrapper").scrollLeft() || i.manualContinuousScrolling) {
                        var s = n.data("scrollableArea").width() - n.data("scrollWrapper").width(), o = n.data("scrollWrapper").scrollLeft() + t;
                        if (0 > o)
                            for (var r = function() {
                                n.data("swappedElement", n.data("scrollableArea").children(":last").detach()), n.data("scrollableArea").prepend(n.data("swappedElement")), n.data("scrollWrapper").scrollLeft(n.data("scrollWrapper").scrollLeft() + n.data("swappedElement").outerWidth(!0))
                            }; 0 > o; )
                                r(), o = n.data("scrollableArea").children(":first").outerWidth(!0) + o;
                        else if (o - s > 0)
                            for (var a = function() {
                                n.data("swappedElement", n.data("scrollableArea").children(":first").detach()), n.data("scrollableArea").append(n.data("swappedElement"));
                                var t = n.data("scrollWrapper").scrollLeft();
                                n.data("scrollWrapper").scrollLeft(t - n.data("swappedElement").outerWidth(!0))
                            }; o - s > 0; )
                                a(), o -= n.data("scrollableArea").children(":last").outerWidth(!0);
                        i.easingAfterMouseWheelScrolling ? n.data("scrollWrapper").animate({scrollLeft: n.data("scrollWrapper").scrollLeft() + t}, {duration: i.easingAfterMouseWheelScrollingDuration, easing: i.easingAfterMouseWheelFunction, complete: function() {
                                e._showHideHotSpots(), i.manualContinuousScrolling && (t > 0 ? e._checkContinuousSwapRight() : e._checkContinuousSwapLeft())
                            }}) : (n.data("scrollWrapper").scrollLeft(n.data("scrollWrapper").scrollLeft() + t), e._showHideHotSpots(), i.manualContinuousScrolling && (t > 0 ? e._checkContinuousSwapRight() : e._checkContinuousSwapLeft()))
                    }
                }, getFlickrContent: function(e, n) {
                    var i = this, s = this.element;
                    t.getJSON(e, function(e) {
                        function o(e, a) {
                            var p = e.media.m, f = p.replace("_m", l[a].letter), m = t("<img />").attr("src", f);
                            m.load(function() {
                                if (this.height < s.data("scrollableAreaHeight") ? a + 1 < l.length ? o(e, a + 1) : r(this) : r(this), d === h) {
                                    switch (n) {
                                        case"addFirst":
                                            s.data("scrollableArea").children(":first").before(u);
                                            break;
                                        case"addLast":
                                            s.data("scrollableArea").children(":last").after(u);
                                            break;
                                        default:
                                            s.data("scrollableArea").html(u)
                                    }
                                    i.recalculateScrollableArea(), i._showHideHotSpots(), i._trigger("addedFlickrContent", null, {addedElementIds: c})
                                }
                            })
                        }
                        function r(e) {
                            var n = s.data("scrollableAreaHeight") / e.height, i = Math.round(e.width * n), o = t(e).attr("src").split("/"), r = o.length - 1;
                            o = o[r].split("."), t(e).attr("id", o[0]), t(e).css({height: s.data("scrollableAreaHeight"), width: i}), c.push(o[0]), u.push(e), d++
                        }
                        var a, l = [{size: "small square", pixels: 75, letter: "_s"}, {size: "thumbnail", pixels: 100, letter: "_t"}, {size: "small", pixels: 240, letter: "_m"}, {size: "medium", pixels: 500, letter: ""}, {size: "medium 640", pixels: 640, letter: "_z"}, {size: "large", pixels: 1024, letter: "_b"}], u = [], c = [], h = e.items.length, d = 0;
                        a = s.data("scrollableAreaHeight") <= 75 ? 0 : s.data("scrollableAreaHeight") <= 100 ? 1 : s.data("scrollableAreaHeight") <= 240 ? 2 : s.data("scrollableAreaHeight") <= 500 ? 3 : s.data("scrollableAreaHeight") <= 640 ? 4 : 5, t.each(e.items, function(t, e) {
                            o(e, a)
                        })
                    })
                }, getAjaxContent: function(e, n, i) {
                    var s = this, o = this.element;
                    t.ajaxSetup({cache: !1}), t.get(e, function(r) {
                        var a;
                        switch (a = void 0 !== i ? i.length > 0 ? t("<div>").html(r).find(i) : e : r, n) {
                            case"addFirst":
                                o.data("scrollableArea").children(":first").before(a);
                                break;
                            case"addLast":
                                o.data("scrollableArea").children(":last").after(a);
                                break;
                            default:
                                o.data("scrollableArea").html(a)
                        }
                        s.recalculateScrollableArea(), s._showHideHotSpots(), s._trigger("addedAjaxContent")
                    })
                }, getHtmlContent: function(e, n, i) {
                    var s, o = this, r = this.element;
                    switch (s = void 0 !== i ? i.length > 0 ? t("<div>").html(e).find(i) : e : e, n) {
                        case"addFirst":
                            r.data("scrollableArea").children(":first").before(s);
                            break;
                        case"addLast":
                            r.data("scrollableArea").children(":last").after(s);
                            break;
                        default:
                            r.data("scrollableArea").html(s)
                    }
                    o.recalculateScrollableArea(), o._showHideHotSpots(), o._trigger("addedHtmlContent")
                }, recalculateScrollableArea: function() {
                    var e = 0, n = !1, i = this.options, s = this.element;
                    s.data("scrollableArea").children(i.countOnlyClass).each(function() {
                        i.startAtElementId.length > 0 && t(this).attr("id") === i.startAtElementId && (s.data("startingPosition", e), n = !0), e += t(this).outerWidth(!0)
                    }), n || s.data("startAtElementId", ""), s.data("scrollableAreaWidth", e), s.data("scrollableArea").width(s.data("scrollableAreaWidth")), s.data("scrollWrapper").scrollLeft(s.data("startingPosition")), s.data("scrollXPos", s.data("startingPosition"))
                }, getScrollerOffset: function() {
                    var t = this.element;
                    return t.data("scrollWrapper").scrollLeft()
                }, stopAutoScrolling: function() {
                    var t = this, e = this.element;
                    null !== e.data("autoScrollingInterval") && (clearInterval(e.data("autoScrollingInterval")), e.data("autoScrollingInterval", null), t._showHideHotSpots(), t._trigger("autoScrollingStopped"))
                }, startAutoScrolling: function() {
                    var t = this, e = this.element, n = this.options;
                    e.data("enabled") && (t._showHideHotSpots(), clearInterval(e.data("autoScrollingInterval")), e.data("autoScrollingInterval", null), t._trigger("autoScrollingStarted"), e.data("autoScrollingInterval", setInterval(function() {
                        if (!e.data("visible") || e.data("scrollableAreaWidth") <= e.data("scrollWrapper").innerWidth())
                            clearInterval(e.data("autoScrollingInterval")), e.data("autoScrollingInterval", null);
                        else
                            switch (e.data("previousScrollLeft", e.data("scrollWrapper").scrollLeft()), n.autoScrollingDirection) {
                                case"right":
                                    e.data("scrollWrapper").scrollLeft(e.data("scrollWrapper").scrollLeft() + n.autoScrollingStep), e.data("previousScrollLeft") === e.data("scrollWrapper").scrollLeft() && (t._trigger("autoScrollingRightLimitReached"), t.stopAutoScrolling());
                                    break;
                                case"left":
                                    e.data("scrollWrapper").scrollLeft(e.data("scrollWrapper").scrollLeft() - n.autoScrollingStep), e.data("previousScrollLeft") === e.data("scrollWrapper").scrollLeft() && (t._trigger("autoScrollingLeftLimitReached"), t.stopAutoScrolling());
                                    break;
                                case"backAndForth":
                                    "right" === e.data("pingPongDirection") ? e.data("scrollWrapper").scrollLeft(e.data("scrollWrapper").scrollLeft() + n.autoScrollingStep) : e.data("scrollWrapper").scrollLeft(e.data("scrollWrapper").scrollLeft() - n.autoScrollingStep), e.data("previousScrollLeft") === e.data("scrollWrapper").scrollLeft() && ("right" === e.data("pingPongDirection") ? (e.data("pingPongDirection", "left"), t._trigger("autoScrollingRightLimitReached")) : (e.data("pingPongDirection", "right"), t._trigger("autoScrollingLeftLimitReached")));
                                    break;
                                case"endlessLoopRight":
                                    e.data("scrollWrapper").scrollLeft(e.data("scrollWrapper").scrollLeft() + n.autoScrollingStep), t._checkContinuousSwapRight();
                                    break;
                                case"endlessLoopLeft":
                                    e.data("scrollWrapper").scrollLeft(e.data("scrollWrapper").scrollLeft() - n.autoScrollingStep), t._checkContinuousSwapLeft()
                            }
                    }, n.autoScrollingInterval)))
                }, _checkContinuousSwapRight: function() {
                    var e = this.element, n = this.options;
                    if (e.data("getNextElementWidth") && (n.startAtElementId.length > 0 && e.data("startAtElementHasNotPassed") ? (e.data("swapAt", t("#" + n.startAtElementId).outerWidth(!0)), e.data("startAtElementHasNotPassed", !1)) : e.data("swapAt", e.data("scrollableArea").children(":first").outerWidth(!0)), e.data("getNextElementWidth", !1)), e.data("swapAt") <= e.data("scrollWrapper").scrollLeft()) {
                        e.data("swappedElement", e.data("scrollableArea").children(":first").detach()), e.data("scrollableArea").append(e.data("swappedElement"));
                        var i = e.data("scrollWrapper").scrollLeft();
                        e.data("scrollWrapper").scrollLeft(i - e.data("swappedElement").outerWidth(!0)), e.data("getNextElementWidth", !0)
                    }
                }, _checkContinuousSwapLeft: function() {
                    var e = this.element, n = this.options;
                    e.data("getNextElementWidth") && (n.startAtElementId.length > 0 && e.data("startAtElementHasNotPassed") ? (e.data("swapAt", t("#" + n.startAtElementId).outerWidth(!0)), e.data("startAtElementHasNotPassed", !1)) : e.data("swapAt", e.data("scrollableArea").children(":first").outerWidth(!0)), e.data("getNextElementWidth", !1)), 0 === e.data("scrollWrapper").scrollLeft() && (e.data("swappedElement", e.data("scrollableArea").children(":last").detach()), e.data("scrollableArea").prepend(e.data("swappedElement")), e.data("scrollWrapper").scrollLeft(e.data("scrollWrapper").scrollLeft() + e.data("swappedElement").outerWidth(!0)), e.data("getNextElementWidth", !0))
                }, restoreOriginalElements: function() {
                    var t = this, e = this.element;
                    e.data("scrollableArea").html(e.data("originalElements")), t.recalculateScrollableArea(), t.jumpToElement("first")
                }, show: function() {
                    var t = this.element;
                    t.data("visible", !0), t.show()
                }, hide: function() {
                    var t = this.element;
                    t.data("visible", !1), t.hide()
                }, enable: function() {
                    var t = this.element;
                    this.options.touchScrolling && t.data("scrollWrapper").kinetic("attach"), t.data("enabled", !0)
                }, disable: function() {
                    var t = this, e = this.element;
                    t.stopAutoScrolling(), clearInterval(e.data("rightScrollingInterval")), clearInterval(e.data("leftScrollingInterval")), clearInterval(e.data("hideHotSpotBackgroundsInterval")), this.options.touchScrolling && e.data("scrollWrapper").kinetic("detach"), e.data("enabled", !1)
                }, destroy: function() {
                    var e = this, n = this.element;
                    e.stopAutoScrolling(), clearInterval(n.data("rightScrollingInterval")), clearInterval(n.data("leftScrollingInterval")), clearInterval(n.data("hideHotSpotBackgroundsInterval")), n.data("scrollingHotSpotRight").unbind("mouseover"), n.data("scrollingHotSpotRight").unbind("mouseout"), n.data("scrollingHotSpotRight").unbind("mousedown"), n.data("scrollingHotSpotLeft").unbind("mouseover"), n.data("scrollingHotSpotLeft").unbind("mouseout"), n.data("scrollingHotSpotLeft").unbind("mousedown"), n.unbind("mousenter"), n.unbind("mouseleave"), n.data("scrollingHotSpotRight").remove(), n.data("scrollingHotSpotLeft").remove(), n.data("scrollableArea").remove(), n.data("scrollWrapper").remove(), n.html(n.data("originalElements")), t.Widget.prototype.destroy.apply(this, arguments)
                }})
        }(jQuery), /*
         * Viewport - jQuery selectors for finding elements in viewport
         *
         * Copyright (c) 2008-2009 Mika Tuupola
         *
         * Licensed under the MIT license:
         *   http://www.opensource.org/licenses/mit-license.php
         *
         * Project home:
         *  http://www.appelsiini.net/projects/viewport
         *
         */
        function(t) {
            t.belowthefold = function(e, n) {
                var i = t(window).height() + t(window).scrollTop();
                return i <= t(e).offset().top - n.threshold
            }, t.abovethetop = function(e, n) {
                var i = t(window).scrollTop();
                return i >= t(e).offset().top + t(e).height() - n.threshold
            }, t.rightofscreen = function(e, n) {
                var i = t(window).width() + t(window).scrollLeft();
                return i <= t(e).offset().left - n.threshold
            }, t.leftofscreen = function(e, n) {
                var i = t(window).scrollLeft();
                return i >= t(e).offset().left + t(e).width() - n.threshold
            }, t.inviewport = function(e, n) {
                return!(t.rightofscreen(e, n) || t.leftofscreen(e, n) || t.belowthefold(e, n) || t.abovethetop(e, n))
            }, t.extend(t.expr[":"], {"below-the-fold": function(e) {
                    return t.belowthefold(e, {threshold: 0})
                }, "above-the-top": function(e) {
                    return t.abovethetop(e, {threshold: 0})
                }, "left-of-screen": function(e) {
                    return t.leftofscreen(e, {threshold: 0})
                }, "right-of-screen": function(e) {
                    return t.rightofscreen(e, {threshold: 0})
                }, "in-viewport": function(e) {
                    return t.inviewport(e, {threshold: 0})
                }})
        }(jQuery), window.Modernizr = function(t, e, n) {
    function i(t) {
        f.cssText = t
    }
    function s(t, e) {
        return typeof t === e
    }
    var o, r, a, l = "2.7.2", u = {}, c = !0, h = e.documentElement, d = "modernizr", p = e.createElement(d), f = p.style, m = ({}.toString, " -webkit- -moz- -o- -ms- ".split(" ")), v = {svg: "http://www.w3.org/2000/svg"}, g = {}, y = [], b = y.slice, w = function(t, n, i, s) {
        var o, r, a, l, u = e.createElement("div"), c = e.body, p = c || e.createElement("body");
        if (parseInt(i, 10))
            for (; i--; )
                a = e.createElement("div"), a.id = s ? s[i] : d + (i + 1), u.appendChild(a);
        return o = ["&#173;", '<style id="s', d, '">', t, "</style>"].join(""), u.id = d, (c ? u : p).innerHTML += o, p.appendChild(u), c || (p.style.background = "", p.style.overflow = "hidden", l = h.style.overflow, h.style.overflow = "hidden", h.appendChild(p)), r = n(u, t), c ? u.parentNode.removeChild(u) : (p.parentNode.removeChild(p), h.style.overflow = l), !!r
    }, x = function(e) {
        var n = t.matchMedia || t.msMatchMedia;
        if (n)
            return n(e).matches;
        var i;
        return w("@media " + e + " { #" + d + " { position: absolute; } }", function(e) {
            i = "absolute" == (t.getComputedStyle ? getComputedStyle(e, null) : e.currentStyle).position
        }), i
    }, _ = {}.hasOwnProperty;
    a = s(_, "undefined") || s(_.call, "undefined") ? function(t, e) {
        return e in t && s(t.constructor.prototype[e], "undefined")
    } : function(t, e) {
        return _.call(t, e)
    }, Function.prototype.bind || (Function.prototype.bind = function(t) {
        var e = this;
        if ("function" != typeof e)
            throw new TypeError;
        var n = b.call(arguments, 1), i = function() {
            if (this instanceof i) {
                var s = function() {
                };
                s.prototype = e.prototype;
                var o = new s, r = e.apply(o, n.concat(b.call(arguments)));
                return Object(r) === r ? r : o
            }
            return e.apply(t, n.concat(b.call(arguments)))
        };
        return i
    }), g.touch = function() {
        var n;
        return"ontouchstart"in t || t.DocumentTouch && e instanceof DocumentTouch ? n = !0 : w(["@media (", m.join("touch-enabled),("), d, ")", "{#modernizr{top:9px;position:absolute}}"].join(""), function(t) {
            n = 9 === t.offsetTop
        }), n
    }, g.svg = function() {
        return!!e.createElementNS && !!e.createElementNS(v.svg, "svg").createSVGRect
    };
    for (var S in g)
        a(g, S) && (r = S.toLowerCase(), u[r] = g[S](), y.push((u[r] ? "" : "no-") + r));
    return u.addTest = function(t, e) {
        if ("object" == typeof t)
            for (var i in t)
                a(t, i) && u.addTest(i, t[i]);
        else {
            if (t = t.toLowerCase(), u[t] !== n)
                return u;
            e = "function" == typeof e ? e() : e, "undefined" != typeof c && c && (h.className += " unity-" + (e ? "" : "no-") + t), u[t] = e
        }
        return u
    }, i(""), p = o = null, u._version = l, u._prefixes = m, u.mq = x, u.testStyles = w, h.className = h.className.replace(/(^|\s)no-js(\s|$)/, "$1$2") + (c ? " unity-js unity-" + y.join(" unity-") : ""), u
}(this, this.document), function(t, e, n) {
    function i(t) {
        return"[object Function]" == v.call(t)
    }
    function s(t) {
        return"string" == typeof t
    }
    function o() {
    }
    function r(t) {
        return!t || "loaded" == t || "complete" == t || "uninitialized" == t
    }
    function a() {
        var t = g.shift();
        y = 1, t ? t.t ? f(function() {
            ("c" == t.t ? d.injectCss : d.injectJs)(t.s, 0, t.a, t.x, t.e, 1)
        }, 0) : (t(), a()) : y = 0
    }
    function l(t, n, i, s, o, l, u) {
        function c(e) {
            if (!p && r(h.readyState) && (b.r = p = 1, !y && a(), h.onload = h.onreadystatechange = null, e)) {
                "img" != t && f(function() {
                    x.removeChild(h)
                }, 50);
                for (var i in j[n])
                    j[n].hasOwnProperty(i) && j[n][i].onload()
            }
        }
        var u = u || d.errorTimeout, h = e.createElement(t), p = 0, v = 0, b = {t: i, s: n, e: o, a: l, x: u};
        1 === j[n] && (v = 1, j[n] = []), "object" == t ? h.data = n : (h.src = n, h.type = t), h.width = h.height = "0", h.onerror = h.onload = h.onreadystatechange = function() {
            c.call(this, v)
        }, g.splice(s, 0, b), "img" != t && (v || 2 === j[n] ? (x.insertBefore(h, w ? null : m), f(c, u)) : j[n].push(h))
    }
    function u(t, e, n, i, o) {
        return y = 0, e = e || "j", s(t) ? l("c" == e ? S : _, t, e, this.i++, n, i, o) : (g.splice(this.i++, 0, t), 1 == g.length && a()), this
    }
    function c() {
        var t = d;
        return t.loader = {load: u, i: 0}, t
    }
    var h, d, p = e.documentElement, f = t.setTimeout, m = e.getElementsByTagName("script")[0], v = {}.toString, g = [], y = 0, b = "MozAppearance"in p.style, w = b && !!e.createRange().compareNode, x = w ? p : m.parentNode, p = t.opera && "[object Opera]" == v.call(t.opera), p = !!e.attachEvent && !p, _ = b ? "object" : p ? "script" : "img", S = p ? "script" : _, k = Array.isArray || function(t) {
        return"[object Array]" == v.call(t)
    }, C = [], j = {}, T = {timeout: function(t, e) {
            return e.length && (t.timeout = e[0]), t
        }};
    d = function(t) {
        function e(t) {
            var e, n, i, t = t.split("!"), s = C.length, o = t.pop(), r = t.length, o = {url: o, origUrl: o, prefixes: t};
            for (n = 0; r > n; n++)
                i = t[n].split("="), (e = T[i.shift()]) && (o = e(o, i));
            for (n = 0; s > n; n++)
                o = C[n](o);
            return o
        }
        function r(t, s, o, r, a) {
            var l = e(t), u = l.autoCallback;
            l.url.split(".").pop().split("?").shift(), l.bypass || (s && (s = i(s) ? s : s[t] || s[r] || s[t.split("/").pop().split("?")[0]]), l.instead ? l.instead(t, s, o, r, a) : (j[l.url] ? l.noexec = !0 : j[l.url] = 1, o.load(l.url, l.forceCSS || !l.forceJS && "css" == l.url.split(".").pop().split("?").shift() ? "c" : n, l.noexec, l.attrs, l.timeout), (i(s) || i(u)) && o.load(function() {
                c(), s && s(l.origUrl, a, r), u && u(l.origUrl, a, r), j[l.url] = 2
            })))
        }
        function a(t, e) {
            function n(t, n) {
                if (t) {
                    if (s(t))
                        n || (h = function() {
                            var t = [].slice.call(arguments);
                            d.apply(this, t), p()
                        }), r(t, h, e, 0, u);
                    else if (Object(t) === t)
                        for (l in a = function() {
                            var e, n = 0;
                            for (e in t)
                                t.hasOwnProperty(e) && n++;
                            return n
                        }(), t)
                            t.hasOwnProperty(l) && (!n && !--a && (i(h) ? h = function() {
                                var t = [].slice.call(arguments);
                                d.apply(this, t), p()
                            } : h[l] = function(t) {
                                return function() {
                                    var e = [].slice.call(arguments);
                                    t && t.apply(this, e), p()
                                }
                            }(d[l])), r(t[l], h, e, l, u))
                } else
                    !n && p()
            }
            var a, l, u = !!t.test, c = t.load || t.both, h = t.callback || o, d = h, p = t.complete || o;
            n(u ? t.yep : t.nope, !!c), c && n(c)
        }
        var l, u, h = this.yepnope.loader;
        if (s(t))
            r(t, 0, h, 0);
        else if (k(t))
            for (l = 0; l < t.length; l++)
                u = t[l], s(u) ? r(u, 0, h, 0) : k(u) ? d(u) : Object(u) === u && a(u, h);
        else
            Object(t) === t && a(t, h)
    }, d.addPrefix = function(t, e) {
        T[t] = e
    }, d.addFilter = function(t) {
        C.push(t)
    }, d.errorTimeout = 1e4, null == e.readyState && e.addEventListener && (e.readyState = "loading", e.addEventListener("DOMContentLoaded", h = function() {
        e.removeEventListener("DOMContentLoaded", h, 0), e.readyState = "complete"
    }, 0)), t.yepnope = c(), t.yepnope.executeStack = a, t.yepnope.injectJs = function(t, n, i, s, l, u) {
        var c, h, p = e.createElement("script"), s = s || d.errorTimeout;
        p.src = t;
        for (h in i)
            p.setAttribute(h, i[h]);
        n = u ? a : n || o, p.onreadystatechange = p.onload = function() {
            !c && r(p.readyState) && (c = 1, n(), p.onload = p.onreadystatechange = null)
        }, f(function() {
            c || (c = 1, n(1))
        }, s), l ? p.onload() : m.parentNode.insertBefore(p, m)
    }, t.yepnope.injectCss = function(t, n, i, s, r, l) {
        var u, s = e.createElement("link"), n = l ? a : n || o;
        s.href = t, s.rel = "stylesheet", s.type = "text/css";
        for (u in i)
            s.setAttribute(u, i[u]);
        r || (m.parentNode.insertBefore(s, m), f(n, 0))
    }
}(this, document), Modernizr.load = function() {
    yepnope.apply(window, [].slice.call(arguments, 0))
}, // Copyright (c) 2013 Adobe Systems Incorporated. All rights reserved.
        !function(t) {
    var e, n, i = "0.4.2", s = "hasOwnProperty", o = /[\.\/]/, r = /\s*,\s*/, a = "*", l = function(t, e) {
        return t - e
    }, u = {n: {}}, c = function() {
        for (var t = 0, e = this.length; e > t; t++)
            if ("undefined" != typeof this[t])
                return this[t]
    }, h = function() {
        for (var t = this.length; --t; )
            if ("undefined" != typeof this[t])
                return this[t]
    }, d = function(t, i) {
        t = String(t);
        var s, o = n, r = Array.prototype.slice.call(arguments, 2), a = d.listeners(t), u = 0, p = [], f = {}, m = [], v = e;
        m.firstDefined = c, m.lastDefined = h, e = t, n = 0;
        for (var g = 0, y = a.length; y > g; g++)
            "zIndex"in a[g] && (p.push(a[g].zIndex), a[g].zIndex < 0 && (f[a[g].zIndex] = a[g]));
        for (p.sort(l); p[u] < 0; )
            if (s = f[p[u++]], m.push(s.apply(i, r)), n)
                return n = o, m;
        for (g = 0; y > g; g++)
            if (s = a[g], "zIndex"in s)
                if (s.zIndex == p[u]) {
                    if (m.push(s.apply(i, r)), n)
                        break;
                    do
                        if (u++, s = f[p[u]], s && m.push(s.apply(i, r)), n)
                            break;
                    while (s)
                } else
                    f[s.zIndex] = s;
            else if (m.push(s.apply(i, r)), n)
                break;
        return n = o, e = v, m
    };
    d._events = u, d.listeners = function(t) {
        var e, n, i, s, r, l, c, h, d = t.split(o), p = u, f = [p], m = [];
        for (s = 0, r = d.length; r > s; s++) {
            for (h = [], l = 0, c = f.length; c > l; l++)
                for (p = f[l].n, n = [p[d[s]], p[a]], i = 2; i--; )
                    e = n[i], e && (h.push(e), m = m.concat(e.f || []));
            f = h
        }
        return m
    }, d.on = function(t, e) {
        if (t = String(t), "function" != typeof e)
            return function() {
            };
        for (var n = t.split(r), i = 0, s = n.length; s > i; i++)
            !function(t) {
                for (var n, i = t.split(o), s = u, r = 0, a = i.length; a > r; r++)
                    s = s.n, s = s.hasOwnProperty(i[r]) && s[i[r]] || (s[i[r]] = {n: {}});
                for (s.f = s.f || [], r = 0, a = s.f.length; a > r; r++)
                    if (s.f[r] == e) {
                        n = !0;
                        break
                    }
                !n && s.f.push(e)
            }(n[i]);
        return function(t) {
            +t == +t && (e.zIndex = +t)
        }
    }, d.f = function(t) {
        var e = [].slice.call(arguments, 1);
        return function() {
            d.apply(null, [t, null].concat(e).concat([].slice.call(arguments, 0)))
        }
    }, d.stop = function() {
        n = 1
    }, d.nt = function(t) {
        return t ? new RegExp("(?:\\.|\\/|^)" + t + "(?:\\.|\\/|$)").test(e) : e
    }, d.nts = function() {
        return e.split(o)
    }, d.off = d.unbind = function(t, e) {
        if (!t)
            return void(d._events = u = {n: {}});
        var n = t.split(r);
        if (n.length > 1)
            for (var i = 0, l = n.length; l > i; i++)
                d.off(n[i], e);
        else {
            n = t.split(o);
            var c, h, p, i, l, f, m, v = [u];
            for (i = 0, l = n.length; l > i; i++)
                for (f = 0; f < v.length; f += p.length - 2) {
                    if (p = [f, 1], c = v[f].n, n[i] != a)
                        c[n[i]] && p.push(c[n[i]]);
                    else
                        for (h in c)
                            c[s](h) && p.push(c[h]);
                    v.splice.apply(v, p)
                }
            for (i = 0, l = v.length; l > i; i++)
                for (c = v[i]; c.n; ) {
                    if (e) {
                        if (c.f) {
                            for (f = 0, m = c.f.length; m > f; f++)
                                if (c.f[f] == e) {
                                    c.f.splice(f, 1);
                                    break
                                }
                            !c.f.length && delete c.f
                        }
                        for (h in c.n)
                            if (c.n[s](h) && c.n[h].f) {
                                var g = c.n[h].f;
                                for (f = 0, m = g.length; m > f; f++)
                                    if (g[f] == e) {
                                        g.splice(f, 1);
                                        break
                                    }
                                !g.length && delete c.n[h].f
                            }
                    } else {
                        delete c.f;
                        for (h in c.n)
                            c.n[s](h) && c.n[h].f && delete c.n[h].f
                    }
                    c = c.n
                }
        }
    }, d.once = function(t, e) {
        var n = function() {
            return d.unbind(t, n), e.apply(this, arguments)
        };
        return d.on(t, n)
    }, d.version = i, d.toString = function() {
        return"You are running Eve " + i
    }, "undefined" != typeof module && module.exports ? module.exports = d : "function" == typeof define && define.amd ? define("eve", [], function() {
        return d
    }) : t.eve = d
}(this), function(t, e) {
    "function" == typeof define && define.amd ? define(["eve"], function(n) {
        return e(t, n)
    }) : e(t, t.eve)
}(this, function(t, e) {
    var n = function(e) {
        var n = {}, i = t.requestAnimationFrame || t.webkitRequestAnimationFrame || t.mozRequestAnimationFrame || t.oRequestAnimationFrame || t.msRequestAnimationFrame || function(t) {
            setTimeout(t, 16)
        }, s = Array.isArray || function(t) {
            return t instanceof Array || "[object Array]" == Object.prototype.toString.call(t)
        }, o = 0, r = "M" + (+new Date).toString(36), a = function() {
            return r + (o++).toString(36)
        }, l = Date.now || function() {
            return+new Date
        }, u = function(t) {
            var e = this;
            if (null == t)
                return e.s;
            var n = e.s - t;
            e.b += e.dur * n, e.B += e.dur * n, e.s = t
        }, c = function(t) {
            var e = this;
            return null == t ? e.spd : void(e.spd = t)
        }, h = function(t) {
            var e = this;
            return null == t ? e.dur : (e.s = e.s * t / e.dur, void(e.dur = t))
        }, d = function() {
            var t = this;
            delete n[t.id], t.update(), e("mina.stop." + t.id, t)
        }, p = function() {
            var t = this;
            t.pdif || (delete n[t.id], t.update(), t.pdif = t.get() - t.b)
        }, f = function() {
            var t = this;
            t.pdif && (t.b = t.get() - t.pdif, delete t.pdif, n[t.id] = t)
        }, m = function() {
            var t, e = this;
            if (s(e.start)) {
                t = [];
                for (var n = 0, i = e.start.length; i > n; n++)
                    t[n] = +e.start[n] + (e.end[n] - e.start[n]) * e.easing(e.s)
            } else
                t = +e.start + (e.end - e.start) * e.easing(e.s);
            e.set(t)
        }, v = function() {
            var t = 0;
            for (var s in n)
                if (n.hasOwnProperty(s)) {
                    var o = n[s], r = o.get();
                    t++, o.s = (r - o.b) / (o.dur / o.spd), o.s >= 1 && (delete n[s], o.s = 1, t--, function(t) {
                        setTimeout(function() {
                            e("mina.finish." + t.id, t)
                        })
                    }(o)), o.update()
                }
            t && i(v)
        }, g = function(t, e, s, o, r, l, y) {
            var b = {id: a(), start: t, end: e, b: s, s: 0, dur: o - s, spd: 1, get: r, set: l, easing: y || g.linear, status: u, speed: c, duration: h, stop: d, pause: p, resume: f, update: m};
            n[b.id] = b;
            var w, x = 0;
            for (w in n)
                if (n.hasOwnProperty(w) && (x++, 2 == x))
                    break;
            return 1 == x && i(v), b
        };
        return g.time = l, g.getById = function(t) {
            return n[t] || null
        }, g.linear = function(t) {
            return t
        }, g.easeout = function(t) {
            return Math.pow(t, 1.7)
        }, g.easein = function(t) {
            return Math.pow(t, .48)
        }, g.easeinout = function(t) {
            if (1 == t)
                return 1;
            if (0 == t)
                return 0;
            var e = .48 - t / 1.04, n = Math.sqrt(.1734 + e * e), i = n - e, s = Math.pow(Math.abs(i), 1 / 3) * (0 > i ? -1 : 1), o = -n - e, r = Math.pow(Math.abs(o), 1 / 3) * (0 > o ? -1 : 1), a = s + r + .5;
            return 3 * (1 - a) * a * a + a * a * a
        }, g.backin = function(t) {
            if (1 == t)
                return 1;
            var e = 1.70158;
            return t * t * ((e + 1) * t - e)
        }, g.backout = function(t) {
            if (0 == t)
                return 0;
            t -= 1;
            var e = 1.70158;
            return t * t * ((e + 1) * t + e) + 1
        }, g.elastic = function(t) {
            return t == !!t ? t : Math.pow(2, -10 * t) * Math.sin(2 * (t - .075) * Math.PI / .3) + 1
        }, g.bounce = function(t) {
            var e, n = 7.5625, i = 2.75;
            return 1 / i > t ? e = n * t * t : 2 / i > t ? (t -= 1.5 / i, e = n * t * t + .75) : 2.5 / i > t ? (t -= 2.25 / i, e = n * t * t + .9375) : (t -= 2.625 / i, e = n * t * t + .984375), e
        }, t.mina = g, g
    }("undefined" == typeof e ? function() {
    } : e), i = function() {
        function i(t, e) {
            if (t) {
                if (t.tagName)
                    return k(t);
                if (o(t, "array") && i.set)
                    return i.set.apply(i, t);
                if (t instanceof w)
                    return t;
                if (null == e)
                    return t = C.doc.querySelector(t), k(t)
            }
            return t = null == t ? "100%" : t, e = null == e ? "100%" : e, new S(t, e)
        }
        function s(t, e) {
            if (e) {
                if ("#text" == t && (t = C.doc.createTextNode(e.text || "")), "string" == typeof t && (t = s(t)), "string" == typeof e)
                    return"xlink:" == e.substring(0, 6) ? t.getAttributeNS(Y, e.substring(6)) : "xml:" == e.substring(0, 4) ? t.getAttributeNS(G, e.substring(4)) : t.getAttribute(e);
                for (var n in e)
                    if (e[j](n)) {
                        var i = T(e[n]);
                        i ? "xlink:" == n.substring(0, 6) ? t.setAttributeNS(Y, n.substring(6), i) : "xml:" == n.substring(0, 4) ? t.setAttributeNS(G, n.substring(4), i) : t.setAttribute(n, i) : t.removeAttribute(n)
                    }
            } else
                t = C.doc.createElementNS(G, t);
            return t
        }
        function o(t, e) {
            return e = T.prototype.toLowerCase.call(e), "finite" == e ? isFinite(t) : "array" == e && (t instanceof Array || Array.isArray && Array.isArray(t)) ? !0 : "null" == e && null === t || e == typeof t && null !== t || "object" == e && t === Object(t) || q.call(t).slice(8, -1).toLowerCase() == e
        }
        function r(t) {
            if ("function" == typeof t || Object(t) !== t)
                return t;
            var e = new t.constructor;
            for (var n in t)
                t[j](n) && (e[n] = r(t[n]));
            return e
        }
        function a(t, e) {
            for (var n = 0, i = t.length; i > n; n++)
                if (t[n] === e)
                    return t.push(t.splice(n, 1)[0])
        }
        function l(t, e, n) {
            function i() {
                var s = Array.prototype.slice.call(arguments, 0), o = s.join("â€"), r = i.cache = i.cache || {}, l = i.count = i.count || [];
                return r[j](o) ? (a(l, o), n ? n(r[o]) : r[o]) : (l.length >= 1e3 && delete r[l.shift()], l.push(o), r[o] = t.apply(e, s), n ? n(r[o]) : r[o])
            }
            return i
        }
        function u(t, e, n, i, s, o) {
            if (null == s) {
                var r = t - n, a = e - i;
                return r || a ? (180 + 180 * A.atan2(-a, -r) / F + 360) % 360 : 0
            }
            return u(t, e, s, o) - u(n, i, s, o)
        }
        function c(t) {
            return t % 360 * F / 180
        }
        function h(t) {
            return 180 * t / F % 360
        }
        function d(t) {
            var e = [];
            return t = t.replace(/(?:^|\s)(\w+)\(([^)]+)\)/g, function(t, n, i) {
                return i = i.split(/\s*,\s*|\s+/), "rotate" == n && 1 == i.length && i.push(0, 0), "scale" == n && (i.length > 2 ? i = i.slice(0, 2) : 2 == i.length && i.push(0, 0), 1 == i.length && i.push(i[0], 0, 0)), e.push("skewX" == n ? ["m", 1, 0, A.tan(c(i[0])), 1, 0, 0] : "skewY" == n ? ["m", 1, A.tan(c(i[0])), 0, 1, 0, 0] : [n.charAt(0)].concat(i)), t
            }), e
        }
        function p(t, e) {
            var n = se(t), s = new i.Matrix;
            if (n)
                for (var o = 0, r = n.length; r > o; o++) {
                    var a, l, u, c, h, d = n[o], p = d.length, f = T(d[0]).toLowerCase(), m = d[0] != f, v = m ? s.invert() : 0;
                    "t" == f && 2 == p ? s.translate(d[1], 0) : "t" == f && 3 == p ? m ? (a = v.x(0, 0), l = v.y(0, 0), u = v.x(d[1], d[2]), c = v.y(d[1], d[2]), s.translate(u - a, c - l)) : s.translate(d[1], d[2]) : "r" == f ? 2 == p ? (h = h || e, s.rotate(d[1], h.x + h.width / 2, h.y + h.height / 2)) : 4 == p && (m ? (u = v.x(d[2], d[3]), c = v.y(d[2], d[3]), s.rotate(d[1], u, c)) : s.rotate(d[1], d[2], d[3])) : "s" == f ? 2 == p || 3 == p ? (h = h || e, s.scale(d[1], d[p - 1], h.x + h.width / 2, h.y + h.height / 2)) : 4 == p ? m ? (u = v.x(d[2], d[3]), c = v.y(d[2], d[3]), s.scale(d[1], d[1], u, c)) : s.scale(d[1], d[1], d[2], d[3]) : 5 == p && (m ? (u = v.x(d[3], d[4]), c = v.y(d[3], d[4]), s.scale(d[1], d[2], u, c)) : s.scale(d[1], d[2], d[3], d[4])) : "m" == f && 7 == p && s.add(d[1], d[2], d[3], d[4], d[5], d[6])
                }
            return s
        }
        function f(t, e) {
            if (null == e) {
                var n = !0;
                if (e = t.node.getAttribute("linearGradient" == t.type || "radialGradient" == t.type ? "gradientTransform" : "pattern" == t.type ? "patternTransform" : "transform"), !e)
                    return new i.Matrix;
                e = d(e)
            } else
                e = i._.rgTransform.test(e) ? T(e).replace(/\.{3}|\u2026/g, t._.transform || M) : d(e), o(e, "array") && (e = i.path ? i.path.toString.call(e) : T(e)), t._.transform = e;
            var s = p(e, t.getBBox(1));
            return n ? s : void(t.matrix = s)
        }
        function m(t) {
            var e = t.node.ownerSVGElement && k(t.node.ownerSVGElement) || t.node.parentNode && k(t.node.parentNode) || i.select("svg") || i(0, 0), n = e.select("defs"), s = null == n ? !1 : n.node;
            return s || (s = _("defs", e.node).node), s
        }
        function v(t) {
            return t.node.ownerSVGElement && k(t.node.ownerSVGElement) || i.select("svg")
        }
        function y(t, e, n) {
            function i(t) {
                if (null == t)
                    return M;
                if (t == +t)
                    return t;
                s(u, {width: t});
                try {
                    return u.getBBox().width
                } catch (e) {
                    return 0
                }
            }
            function o(t) {
                if (null == t)
                    return M;
                if (t == +t)
                    return t;
                s(u, {height: t});
                try {
                    return u.getBBox().height
                } catch (e) {
                    return 0
                }
            }
            function r(i, s) {
                null == e ? l[i] = s(t.attr(i) || 0) : i == e && (l = s(null == n ? t.attr(i) || 0 : n))
            }
            var a = v(t).node, l = {}, u = a.querySelector(".svg---mgr");
            switch (u || (u = s("rect"), s(u, {
                    x: -9e9, y: -9e9, width: 10, height: 10, "class": "svg---mgr", fill: "none"}), a.appendChild(u)), t.type){case"rect":
                    r("rx", i), r("ry", o);
                case"image":
                    r("width", i), r("height", o);
                case"text":
                    r("x", i), r("y", o);
                    break;
                case"circle":
                    r("cx", i), r("cy", o), r("r", i);
                    break;
                case"ellipse":
                    r("cx", i), r("cy", o), r("rx", i), r("ry", o);
                    break;
                case"line":
                    r("x1", i), r("x2", i), r("y1", o), r("y2", o);
                    break;
                case"marker":
                    r("refX", i), r("markerWidth", i), r("refY", o), r("markerHeight", o);
                    break;
                case"radialGradient":
                    r("fx", i), r("fy", o);
                    break;
                case"tspan":
                    r("dx", i), r("dy", o);
                    break;
                default:
                    r(e, i)
            }
            return a.removeChild(u), l
        }
        function b(t) {
            o(t, "array") || (t = Array.prototype.slice.call(arguments, 0));
            for (var e = 0, n = 0, i = this.node; this[e]; )
                delete this[e++];
            for (e = 0; e < t.length; e++)
                "set" == t[e].type ? t[e].forEach(function(t) {
                    i.appendChild(t.node)
                }) : i.appendChild(t[e].node);
            var s = i.childNodes;
            for (e = 0; e < s.length; e++)
                this[n++] = k(s[e]);
            return this
        }
        function w(t) {
            if (t.snap in Q)
                return Q[t.snap];
            var e, n = this.id = X();
            try {
                e = t.ownerSVGElement
            } catch (i) {
            }
            if (this.node = t, e && (this.paper = new S(e)), this.type = t.tagName, this.anims = {}, this._ = {transform: []}, t.snap = n, Q[n] = this, "g" == this.type && (this.add = b), this.type in{g: 1, mask: 1, pattern: 1})
                for (var s in S.prototype)
                    S.prototype[j](s) && (this[s] = S.prototype[s])
        }
        function x(t) {
            this.node = t
        }
        function _(t, e) {
            var n = s(t);
            e.appendChild(n);
            var i = k(n);
            return i
        }
        function S(t, e) {
            var n, i, o, r = S.prototype;
            if (t && "svg" == t.tagName) {
                if (t.snap in Q)
                    return Q[t.snap];
                var a = t.ownerDocument;
                n = new w(t), i = t.getElementsByTagName("desc")[0], o = t.getElementsByTagName("defs")[0], i || (i = s("desc"), i.appendChild(a.createTextNode("Created with Snap")), n.node.appendChild(i)), o || (o = s("defs"), n.node.appendChild(o)), n.defs = o;
                for (var l in r)
                    r[j](l) && (n[l] = r[l]);
                n.paper = n.root = n
            } else
                n = _("svg", C.doc.body), s(n.node, {height: e, version: 1.1, width: t, xmlns: G});
            return n
        }
        function k(t) {
            return t ? t instanceof w || t instanceof x ? t : t.tagName && "svg" == t.tagName.toLowerCase() ? new S(t) : t.tagName && "object" == t.tagName.toLowerCase() && "image/svg+xml" == t.type ? new S(t.contentDocument.getElementsByTagName("svg")[0]) : new w(t) : t
        }
        i.version = "0.3.0", i.toString = function() {
            return"Snap v" + this.version
        }, i._ = {};
        var C = {win: t, doc: t.document};
        i._.glob = C;
        var j = "hasOwnProperty", T = String, E = parseFloat, P = parseInt, A = Math, N = A.max, L = A.min, H = A.abs, F = (A.pow, A.PI), M = (A.round, ""), D = " ", q = Object.prototype.toString, O = /^\s*((#[a-f\d]{6})|(#[a-f\d]{3})|rgba?\(\s*([\d\.]+%?\s*,\s*[\d\.]+%?\s*,\s*[\d\.]+%?(?:\s*,\s*[\d\.]+%?)?)\s*\)|hsba?\(\s*([\d\.]+(?:deg|\xb0|%)?\s*,\s*[\d\.]+%?\s*,\s*[\d\.]+(?:%?\s*,\s*[\d\.]+)?%?)\s*\)|hsla?\(\s*([\d\.]+(?:deg|\xb0|%)?\s*,\s*[\d\.]+%?\s*,\s*[\d\.]+(?:%?\s*,\s*[\d\.]+)?%?)\s*\))\s*$/i, R = "	\n\f\r Â áš€á Žâ€€â€â€‚â€ƒâ€„â€…â€†â€‡â€ˆâ€‰â€Šâ€¯âŸã€€\u2028\u2029", I = (i._.separator = new RegExp("[," + R + "]+"), new RegExp("[" + R + "]", "g"), new RegExp("[" + R + "]*,[" + R + "]*")), B = {hs: 1, rg: 1}, W = new RegExp("([a-z])[" + R + ",]*((-?\\d*\\.?\\d*(?:e[\\-+]?\\d+)?[" + R + "]*,?[" + R + "]*)+)", "ig"), z = new RegExp("([rstm])[" + R + ",]*((-?\\d*\\.?\\d*(?:e[\\-+]?\\d+)?[" + R + "]*,?[" + R + "]*)+)", "ig"), $ = new RegExp("(-?\\d*\\.?\\d*(?:e[\\-+]?\\d+)?)[" + R + "]*,?[" + R + "]*", "ig"), V = 0, U = "S" + (+new Date).toString(36), X = function() {
            return U + (V++).toString(36)
        }, Y = "http://www.w3.org/1999/xlink", G = "http://www.w3.org/2000/svg", Q = {}, J = i.url = function(t) {
            return"url('#" + t + "')"
        };
        i._.$ = s, i._.id = X, i.format = function() {
            var t = /\{([^\}]+)\}/g, e = /(?:(?:^|\.)(.+?)(?=\[|\.|$|\()|\[('|")(.+?)\2\])(\(\))?/g, n = function(t, n, i) {
                var s = i;
                return n.replace(e, function(t, e, n, i, o) {
                    e = e || i, s && (e in s && (s = s[e]), "function" == typeof s && o && (s = s()))
                }), s = (null == s || s == i ? t : s) + ""
            };
            return function(e, i) {
                return T(e).replace(t, function(t, e) {
                    return n(t, e, i)
                })
            }
        }(), i._.clone = r, i._.cacher = l, i.rad = c, i.deg = h, i.angle = u, i.is = o, i.snapTo = function(t, e, n) {
            if (n = o(n, "finite") ? n : 10, o(t, "array")) {
                for (var i = t.length; i--; )
                    if (H(t[i] - e) <= n)
                        return t[i]
            } else {
                t = +t;
                var s = e % t;
                if (n > s)
                    return e - s;
                if (s > t - n)
                    return e - s + t
            }
            return e
        }, i.getRGB = l(function(t) {
            if (!t || (t = T(t)).indexOf("-") + 1)
                return{r: -1, g: -1, b: -1, hex: "none", error: 1, toString: ee};
            if ("none" == t)
                return{r: -1, g: -1, b: -1, hex: "none", toString: ee};
            if (!(B[j](t.toLowerCase().substring(0, 2)) || "#" == t.charAt()) && (t = K(t)), !t)
                return{r: -1, g: -1, b: -1, hex: "none", error: 1, toString: ee};
            var e, n, s, r, a, l, u = t.match(O);
            return u ? (u[2] && (s = P(u[2].substring(5), 16), n = P(u[2].substring(3, 5), 16), e = P(u[2].substring(1, 3), 16)), u[3] && (s = P((a = u[3].charAt(3)) + a, 16), n = P((a = u[3].charAt(2)) + a, 16), e = P((a = u[3].charAt(1)) + a, 16)), u[4] && (l = u[4].split(I), e = E(l[0]), "%" == l[0].slice(-1) && (e *= 2.55), n = E(l[1]), "%" == l[1].slice(-1) && (n *= 2.55), s = E(l[2]), "%" == l[2].slice(-1) && (s *= 2.55), "rgba" == u[1].toLowerCase().slice(0, 4) && (r = E(l[3])), l[3] && "%" == l[3].slice(-1) && (r /= 100)), u[5] ? (l = u[5].split(I), e = E(l[0]), "%" == l[0].slice(-1) && (e /= 100), n = E(l[1]), "%" == l[1].slice(-1) && (n /= 100), s = E(l[2]), "%" == l[2].slice(-1) && (s /= 100), ("deg" == l[0].slice(-3) || "Â°" == l[0].slice(-1)) && (e /= 360), "hsba" == u[1].toLowerCase().slice(0, 4) && (r = E(l[3])), l[3] && "%" == l[3].slice(-1) && (r /= 100), i.hsb2rgb(e, n, s, r)) : u[6] ? (l = u[6].split(I), e = E(l[0]), "%" == l[0].slice(-1) && (e /= 100), n = E(l[1]), "%" == l[1].slice(-1) && (n /= 100), s = E(l[2]), "%" == l[2].slice(-1) && (s /= 100), ("deg" == l[0].slice(-3) || "Â°" == l[0].slice(-1)) && (e /= 360), "hsla" == u[1].toLowerCase().slice(0, 4) && (r = E(l[3])), l[3] && "%" == l[3].slice(-1) && (r /= 100), i.hsl2rgb(e, n, s, r)) : (e = L(A.round(e), 255), n = L(A.round(n), 255), s = L(A.round(s), 255), r = L(N(r, 0), 1), u = {r: e, g: n, b: s, toString: ee}, u.hex = "#" + (16777216 | s | n << 8 | e << 16).toString(16).slice(1), u.opacity = o(r, "finite") ? r : 1, u)) : {r: -1, g: -1, b: -1, hex: "none", error: 1, toString: ee}
        }, i), i.hsb = l(function(t, e, n) {
            return i.hsb2rgb(t, e, n).hex
        }), i.hsl = l(function(t, e, n) {
            return i.hsl2rgb(t, e, n).hex
        }), i.rgb = l(function(t, e, n, i) {
            if (o(i, "finite")) {
                var s = A.round;
                return"rgba(" + [s(t), s(e), s(n), +i.toFixed(2)] + ")"
            }
            return"#" + (16777216 | n | e << 8 | t << 16).toString(16).slice(1)
        });
        var K = function(t) {
            var e = C.doc.getElementsByTagName("head")[0] || C.doc.getElementsByTagName("svg")[0], n = "rgb(255, 0, 0)";
            return(K = l(function(t) {
                if ("red" == t.toLowerCase())
                    return n;
                e.style.color = n, e.style.color = t;
                var i = C.doc.defaultView.getComputedStyle(e, M).getPropertyValue("color");
                return i == n ? null : i
            }))(t)
        }, Z = function() {
            return"hsb(" + [this.h, this.s, this.b] + ")"
        }, te = function() {
            return"hsl(" + [this.h, this.s, this.l] + ")"
        }, ee = function() {
            return 1 == this.opacity || null == this.opacity ? this.hex : "rgba(" + [this.r, this.g, this.b, this.opacity] + ")"
        }, ne = function(t, e, n) {
            if (null == e && o(t, "object") && "r"in t && "g"in t && "b"in t && (n = t.b, e = t.g, t = t.r), null == e && o(t, string)) {
                var s = i.getRGB(t);
                t = s.r, e = s.g, n = s.b
            }
            return(t > 1 || e > 1 || n > 1) && (t /= 255, e /= 255, n /= 255), [t, e, n]
        }, ie = function(t, e, n, s) {
            t = A.round(255 * t), e = A.round(255 * e), n = A.round(255 * n);
            var r = {r: t, g: e, b: n, opacity: o(s, "finite") ? s : 1, hex: i.rgb(t, e, n), toString: ee};
            return o(s, "finite") && (r.opacity = s), r
        };
        i.color = function(t) {
            var e;
            return o(t, "object") && "h"in t && "s"in t && "b"in t ? (e = i.hsb2rgb(t), t.r = e.r, t.g = e.g, t.b = e.b, t.opacity = 1, t.hex = e.hex) : o(t, "object") && "h"in t && "s"in t && "l"in t ? (e = i.hsl2rgb(t), t.r = e.r, t.g = e.g, t.b = e.b, t.opacity = 1, t.hex = e.hex) : (o(t, "string") && (t = i.getRGB(t)), o(t, "object") && "r"in t && "g"in t && "b"in t && !("error"in t) ? (e = i.rgb2hsl(t), t.h = e.h, t.s = e.s, t.l = e.l, e = i.rgb2hsb(t), t.v = e.b) : (t = {hex: "none"}, t.r = t.g = t.b = t.h = t.s = t.v = t.l = -1, t.error = 1)), t.toString = ee, t
        }, i.hsb2rgb = function(t, e, n, i) {
            o(t, "object") && "h"in t && "s"in t && "b"in t && (n = t.b, e = t.s, t = t.h, i = t.o), t *= 360;
            var s, r, a, l, u;
            return t = t % 360 / 60, u = n * e, l = u * (1 - H(t % 2 - 1)), s = r = a = n - u, t = ~~t, s += [u, l, 0, 0, l, u][t], r += [l, u, u, l, 0, 0][t], a += [0, 0, l, u, u, l][t], ie(s, r, a, i)
        }, i.hsl2rgb = function(t, e, n, i) {
            o(t, "object") && "h"in t && "s"in t && "l"in t && (n = t.l, e = t.s, t = t.h), (t > 1 || e > 1 || n > 1) && (t /= 360, e /= 100, n /= 100), t *= 360;
            var s, r, a, l, u;
            return t = t % 360 / 60, u = 2 * e * (.5 > n ? n : 1 - n), l = u * (1 - H(t % 2 - 1)), s = r = a = n - u / 2, t = ~~t, s += [u, l, 0, 0, l, u][t], r += [l, u, u, l, 0, 0][t], a += [0, 0, l, u, u, l][t], ie(s, r, a, i)
        }, i.rgb2hsb = function(t, e, n) {
            n = ne(t, e, n), t = n[0], e = n[1], n = n[2];
            var i, s, o, r;
            return o = N(t, e, n), r = o - L(t, e, n), i = 0 == r ? null : o == t ? (e - n) / r : o == e ? (n - t) / r + 2 : (t - e) / r + 4, i = (i + 360) % 6 * 60 / 360, s = 0 == r ? 0 : r / o, {h: i, s: s, b: o, toString: Z}
        }, i.rgb2hsl = function(t, e, n) {
            n = ne(t, e, n), t = n[0], e = n[1], n = n[2];
            var i, s, o, r, a, l;
            return r = N(t, e, n), a = L(t, e, n), l = r - a, i = 0 == l ? null : r == t ? (e - n) / l : r == e ? (n - t) / l + 2 : (t - e) / l + 4, i = (i + 360) % 6 * 60 / 360, o = (r + a) / 2, s = 0 == l ? 0 : .5 > o ? l / (2 * o) : l / (2 - 2 * o), {h: i, s: s, l: o, toString: te}
        }, i.parsePathString = function(t) {
            if (!t)
                return null;
            var e = i.path(t);
            if (e.arr)
                return i.path.clone(e.arr);
            var n = {a: 7, c: 6, o: 2, h: 1, l: 2, m: 2, r: 4, q: 4, s: 4, t: 2, v: 1, u: 3, z: 0}, s = [];
            return o(t, "array") && o(t[0], "array") && (s = i.path.clone(t)), s.length || T(t).replace(W, function(t, e, i) {
                var o = [], r = e.toLowerCase();
                if (i.replace($, function(t, e) {
                    e && o.push(+e)
                }), "m" == r && o.length > 2 && (s.push([e].concat(o.splice(0, 2))), r = "l", e = "m" == e ? "l" : "L"), "o" == r && 1 == o.length && s.push([e, o[0]]), "r" == r)
                    s.push([e].concat(o));
                else
                    for (; o.length >= n[r] && (s.push([e].concat(o.splice(0, n[r]))), n[r]); )
                        ;
            }), s.toString = i.path.toString, e.arr = i.path.clone(s), s
        };
        var se = i.parseTransformString = function(t) {
            if (!t)
                return null;
            var e = [];
            return o(t, "array") && o(t[0], "array") && (e = i.path.clone(t)), e.length || T(t).replace(z, function(t, n, i) {
                var s = [];
                n.toLowerCase(), i.replace($, function(t, e) {
                    e && s.push(+e)
                }), e.push([n].concat(s))
            }), e.toString = i.path.toString, e
        };
        i._.svgTransform2string = d, i._.rgTransform = new RegExp("^[a-z][" + R + "]*-?\\.?\\d", "i"), i._.transform2matrix = p, i._unit2px = y, C.doc.contains || C.doc.compareDocumentPosition ? function(t, e) {
            var n = 9 == t.nodeType ? t.documentElement : t, i = e && e.parentNode;
            return t == i || !(!i || 1 != i.nodeType || !(n.contains ? n.contains(i) : t.compareDocumentPosition && 16 & t.compareDocumentPosition(i)))
        } : function(t, e) {
            if (e)
                for (; e; )
                    if (e = e.parentNode, e == t)
                        return!0;
            return!1
        }, i._.getSomeDefs = m, i._.getSomeSVG = v, i.select = function(t) {
            return k(C.doc.querySelector(t))
        }, i.selectAll = function(t) {
            for (var e = C.doc.querySelectorAll(t), n = (i.set || Array)(), s = 0; s < e.length; s++)
                n.push(k(e[s]));
            return n
        }, setInterval(function() {
            for (var t in Q)
                if (Q[j](t)) {
                    var e = Q[t], n = e.node;
                    ("svg" != e.type && !n.ownerSVGElement || "svg" == e.type && (!n.parentNode || "ownerSVGElement"in n.parentNode && !n.ownerSVGElement)) && delete Q[t]
                }
        }, 1e4), function(t) {
            function r(t) {
                function e(t, e) {
                    var n = s(t.node, e);
                    n = n && n.match(r), n = n && n[2], n && "#" == n.charAt() && (n = n.substring(1), n && (l[n] = (l[n] || []).concat(function(n) {
                        var i = {};
                        i[e] = J(n), s(t.node, i)
                    })))
                }
                function n(t) {
                    var e = s(t.node, "xlink:href");
                    e && "#" == e.charAt() && (e = e.substring(1), e && (l[e] = (l[e] || []).concat(function(e) {
                        t.attr("xlink:href", "#" + e)
                    })))
                }
                for (var i, o = t.selectAll("*"), r = /^\s*url\(("|'|)(.*)\1\)\s*$/, a = [], l = {}, u = 0, c = o.length; c > u; u++) {
                    i = o[u], e(i, "fill"), e(i, "stroke"), e(i, "filter"), e(i, "mask"), e(i, "clip-path"), n(i);
                    var h = s(i.node, "id");
                    h && (s(i.node, {id: i.id}), a.push({old: h, id: i.id}))
                }
                for (u = 0, c = a.length; c > u; u++) {
                    var d = l[a[u].old];
                    if (d)
                        for (var p = 0, f = d.length; f > p; p++)
                            d[p](a[u].id)
                }
            }
            function a(t, e, n) {
                return function(i) {
                    var s = i.slice(t, e);
                    return 1 == s.length && (s = s[0]), n ? n(s) : s
                }
            }
            function l(t) {
                return function() {
                    var e = t ? "<" + this.type : "", n = this.node.attributes, i = this.node.childNodes;
                    if (t)
                        for (var s = 0, o = n.length; o > s; s++)
                            e += " " + n[s].name + '="' + n[s].value.replace(/"/g, '\\"') + '"';
                    if (i.length) {
                        for (t && (e += ">"), s = 0, o = i.length; o > s; s++)
                            3 == i[s].nodeType ? e += i[s].nodeValue : 1 == i[s].nodeType && (e += k(i[s]).toString());
                        t && (e += "</" + this.type + ">")
                    } else
                        t && (e += "/>");
                    return e
                }
            }
            t.attr = function(t, n) {
                var i = this;
                if (i.node, !t)
                    return i;
                if (o(t, "string")) {
                    if (!(arguments.length > 1))
                        return e("snap.util.getattr." + t, i).firstDefined();
                    var s = {};
                    s[t] = n, t = s
                }
                for (var r in t)
                    t[j](r) && e("snap.util.attr." + r, i, t[r]);
                return i
            }, t.getBBox = function(t) {
                if (!i.Matrix || !i.path)
                    return this.node.getBBox();
                var e = this, n = new i.Matrix;
                if (e.removed)
                    return i._.box();
                for (; "use" == e.type; )
                    if (t || (n = n.add(e.transform().localMatrix.translate(e.attr("x") || 0, e.attr("y") || 0))), e.original)
                        e = e.original;
                    else {
                        var s = e.attr("xlink:href");
                        e = e.original = e.node.ownerDocument.getElementById(s.substring(s.indexOf("#") + 1))
                    }
                var o = e._, r = i.path.get[e.type] || i.path.get.deflt;
                try {
                    return t ? (o.bboxwt = r ? i.path.getBBox(e.realPath = r(e)) : i._.box(e.node.getBBox()), i._.box(o.bboxwt)) : (e.realPath = r(e), e.matrix = e.transform().localMatrix, o.bbox = i.path.getBBox(i.path.map(e.realPath, n.add(e.matrix))), i._.box(o.bbox))
                } catch (a) {
                    return i._.box()
                }
            };
            var u = function() {
                return this.string
            };
            t.transform = function(t) {
                var e = this._;
                if (null == t) {
                    for (var n, o = this, r = new i.Matrix(this.node.getCTM()), a = f(this), l = [a], c = new i.Matrix, h = a.toTransformString(), d = T(a) == T(this.matrix) ? T(e.transform) : h; "svg" != o.type && (o = o.parent()); )
                        l.push(f(o));
                    for (n = l.length; n--; )
                        c.add(l[n]);
                    return{string: d, globalMatrix: r, totalMatrix: c, localMatrix: a, diffMatrix: r.clone().add(a.invert()), global: r.toTransformString(), total: c.toTransformString(), local: h, toString: u}
                }
                return t instanceof i.Matrix ? this.matrix = t : f(this, t), this.node && ("linearGradient" == this.type || "radialGradient" == this.type ? s(this.node, {gradientTransform: this.matrix}) : "pattern" == this.type ? s(this.node, {patternTransform: this.matrix}) : s(this.node, {transform: this.matrix})), this
            }, t.parent = function() {
                return k(this.node.parentNode)
            }, t.append = t.add = function(t) {
                if (t) {
                    if ("set" == t.type) {
                        var e = this;
                        return t.forEach(function(t) {
                            e.add(t)
                        }), this
                    }
                    t = k(t), this.node.appendChild(t.node), t.paper = this.paper
                }
                return this
            }, t.appendTo = function(t) {
                return t && (t = k(t), t.append(this)), this
            }, t.prepend = function(t) {
                if (t) {
                    if ("set" == t.type) {
                        var e, n = this;
                        return t.forEach(function(t) {
                            e ? e.after(t) : n.prepend(t), e = t
                        }), this
                    }
                    t = k(t);
                    var i = t.parent();
                    this.node.insertBefore(t.node, this.node.firstChild), this.add && this.add(), t.paper = this.paper, this.parent() && this.parent().add(), i && i.add()
                }
                return this
            }, t.prependTo = function(t) {
                return t = k(t), t.prepend(this), this
            }, t.before = function(t) {
                if ("set" == t.type) {
                    var e = this;
                    return t.forEach(function(t) {
                        var n = t.parent();
                        e.node.parentNode.insertBefore(t.node, e.node), n && n.add()
                    }), this.parent().add(), this
                }
                t = k(t);
                var n = t.parent();
                return this.node.parentNode.insertBefore(t.node, this.node), this.parent() && this.parent().add(), n && n.add(), t.paper = this.paper, this
            }, t.after = function(t) {
                t = k(t);
                var e = t.parent();
                return this.node.nextSibling ? this.node.parentNode.insertBefore(t.node, this.node.nextSibling) : this.node.parentNode.appendChild(t.node), this.parent() && this.parent().add(), e && e.add(), t.paper = this.paper, this
            }, t.insertBefore = function(t) {
                t = k(t);
                var e = this.parent();
                return t.node.parentNode.insertBefore(this.node, t.node), this.paper = t.paper, e && e.add(), t.parent() && t.parent().add(), this
            }, t.insertAfter = function(t) {
                t = k(t);
                var e = this.parent();
                return t.node.parentNode.insertBefore(this.node, t.node.nextSibling), this.paper = t.paper, e && e.add(), t.parent() && t.parent().add(), this
            }, t.remove = function() {
                var t = this.parent();
                return this.node.parentNode && this.node.parentNode.removeChild(this.node), delete this.paper, this.removed = !0, t && t.add(), this
            }, t.select = function(t) {
                return k(this.node.querySelector(t))
            }, t.selectAll = function(t) {
                for (var e = this.node.querySelectorAll(t), n = (i.set || Array)(), s = 0; s < e.length; s++)
                    n.push(k(e[s]));
                return n
            }, t.asPX = function(t, e) {
                return null == e && (e = this.attr(t)), +y(this, t, e)
            }, t.use = function() {
                var t, e = this.node.id;
                return e || (e = this.id, s(this.node, {id: e})), t = "linearGradient" == this.type || "radialGradient" == this.type || "pattern" == this.type ? _(this.type, this.node.parentNode) : _("use", this.node.parentNode), s(t.node, {"xlink:href": "#" + e}), t.original = this, t
            };
            var c = /\S+/g;
            t.addClass = function(t) {
                var e, n, i, s, o = (t || "").match(c) || [], r = this.node, a = r.className.baseVal, l = a.match(c) || [];
                if (o.length) {
                    for (e = 0; i = o[e++]; )
                        n = l.indexOf(i), ~n || l.push(i);
                    s = l.join(" "), a != s && (r.className.baseVal = s)
                }
                return this
            }, t.removeClass = function(t) {
                var e, n, i, s, o = (t || "").match(c) || [], r = this.node, a = r.className.baseVal, l = a.match(c) || [];
                if (l.length) {
                    for (e = 0; i = o[e++]; )
                        n = l.indexOf(i), ~n && l.splice(n, 1);
                    s = l.join(" "), a != s && (r.className.baseVal = s)
                }
                return this
            }, t.hasClass = function(t) {
                var e = this.node, n = e.className.baseVal, i = n.match(c) || [];
                return!!~i.indexOf(t)
            }, t.toggleClass = function(t, e) {
                if (null != e)
                    return e ? this.addClass(t) : this.removeClass(t);
                var n, i, s, o, r = (t || "").match(c) || [], a = this.node, l = a.className.baseVal, u = l.match(c) || [];
                for (n = 0; s = r[n++]; )
                    i = u.indexOf(s), ~i ? u.splice(i, 1) : u.push(s);
                return o = u.join(" "), l != o && (a.className.baseVal = o), this
            }, t.clone = function() {
                var t = k(this.node.cloneNode(!0));
                return s(t.node, "id") && s(t.node, {id: t.id}), r(t), t.insertAfter(this), t
            }, t.toDefs = function() {
                var t = m(this);
                return t.appendChild(this.node), this
            }, t.pattern = t.toPattern = function(t, e, n, i) {
                var r = _("pattern", m(this));
                return null == t && (t = this.getBBox()), o(t, "object") && "x"in t && (e = t.y, n = t.width, i = t.height, t = t.x), s(r.node, {x: t, y: e, width: n, height: i, patternUnits: "userSpaceOnUse", id: r.id, viewBox: [t, e, n, i].join(" ")}), r.node.appendChild(this.node), r
            }, t.marker = function(t, e, n, i, r, a) {
                var l = _("marker", m(this));
                return null == t && (t = this.getBBox()), o(t, "object") && "x"in t && (e = t.y, n = t.width, i = t.height, r = t.refX || t.cx, a = t.refY || t.cy, t = t.x), s(l.node, {viewBox: [t, e, n, i].join(D), markerWidth: n, markerHeight: i, orient: "auto", refX: r || 0, refY: a || 0, id: l.id}), l.node.appendChild(this.node), l
            };
            var h = function(t, e, i, s) {
                "function" != typeof i || i.length || (s = i, i = n.linear), this.attr = t, this.dur = e, i && (this.easing = i), s && (this.callback = s)
            };
            i._.Animation = h, i.animation = function(t, e, n, i) {
                return new h(t, e, n, i)
            }, t.inAnim = function() {
                var t = this, e = [];
                for (var n in t.anims)
                    t.anims[j](n) && !function(t) {
                        e.push({anim: new h(t._attrs, t.dur, t.easing, t._callback), mina: t, curStatus: t.status(), status: function(e) {
                                return t.status(e)
                            }, stop: function() {
                                t.stop()
                            }})
                    }(t.anims[n]);
                return e
            }, i.animate = function(t, i, s, o, r, a) {
                "function" != typeof r || r.length || (a = r, r = n.linear);
                var l = n.time(), u = n(t, i, l, l + o, n.time, s, r);
                return a && e.once("mina.finish." + u.id, a), u
            }, t.stop = function() {
                for (var t = this.inAnim(), e = 0, n = t.length; n > e; e++)
                    t[e].stop();
                return this
            }, t.animate = function(t, i, s, r) {
                "function" != typeof s || s.length || (r = s, s = n.linear), t instanceof h && (r = t.callback, s = t.easing, i = s.dur, t = t.attr);
                var l, u, c, d, p = [], f = [], m = {}, v = this;
                for (var g in t)
                    if (t[j](g)) {
                        v.equal ? (d = v.equal(g, T(t[g])), l = d.from, u = d.to, c = d.f) : (l = +v.attr(g), u = +t[g]);
                        var y = o(l, "array") ? l.length : 1;
                        m[g] = a(p.length, p.length + y, c), p = p.concat(l), f = f.concat(u)
                    }
                var b = n.time(), w = n(p, f, b, b + i, n.time, function(t) {
                    var e = {};
                    for (var n in m)
                        m[j](n) && (e[n] = m[n](t));
                    v.attr(e)
                }, s);
                return v.anims[w.id] = w, w._attrs = t, w._callback = r, e("snap.animcreated." + v.id, w), e.once("mina.finish." + w.id, function() {
                    delete v.anims[w.id], r && r.call(v)
                }), e.once("mina.stop." + w.id, function() {
                    delete v.anims[w.id]
                }), v
            };
            var d = {};
            t.data = function(t, n) {
                var s = d[this.id] = d[this.id] || {};
                if (0 == arguments.length)
                    return e("snap.data.get." + this.id, this, s, null), s;
                if (1 == arguments.length) {
                    if (i.is(t, "object")) {
                        for (var o in t)
                            t[j](o) && this.data(o, t[o]);
                        return this
                    }
                    return e("snap.data.get." + this.id, this, s[t], t), s[t]
                }
                return s[t] = n, e("snap.data.set." + this.id, this, n, t), this
            }, t.removeData = function(t) {
                return null == t ? d[this.id] = {} : d[this.id] && delete d[this.id][t], this
            }, t.outerSVG = t.toString = l(1), t.innerSVG = l()
        }(w.prototype), i.parse = function(t) {
            var e = C.doc.createDocumentFragment(), n = !0, i = C.doc.createElement("div");
            if (t = T(t), t.match(/^\s*<\s*svg(?:\s|>)/) || (t = "<svg>" + t + "</svg>", n = !1), i.innerHTML = t, t = i.getElementsByTagName("svg")[0])
                if (n)
                    e = t;
                else
                    for (; t.firstChild; )
                        e.appendChild(t.firstChild);
            return i.innerHTML = M, new x(e)
        }, x.prototype.select = w.prototype.select, x.prototype.selectAll = w.prototype.selectAll, i.fragment = function() {
            for (var t = Array.prototype.slice.call(arguments, 0), e = C.doc.createDocumentFragment(), n = 0, s = t.length; s > n; n++) {
                var o = t[n];
                o.node && o.node.nodeType && e.appendChild(o.node), o.nodeType && e.appendChild(o), "string" == typeof o && e.appendChild(i.parse(o).node)
            }
            return new x(e)
        }, i._.make = _, i._.wrap = k, S.prototype.el = function(t, e) {
            var n = _(t, this.node);
            return e && n.attr(e), n
        }, e.on("snap.util.getattr", function() {
            var t = e.nt();
            t = t.substring(t.lastIndexOf(".") + 1);
            var n = t.replace(/[A-Z]/g, function(t) {
                return"-" + t.toLowerCase()
            });
            return oe[j](n) ? this.node.ownerDocument.defaultView.getComputedStyle(this.node, null).getPropertyValue(n) : s(this.node, t)
        });
        var oe = {"alignment-baseline": 0, "baseline-shift": 0, clip: 0, "clip-path": 0, "clip-rule": 0, color: 0, "color-interpolation": 0, "color-interpolation-filters": 0, "color-profile": 0, "color-rendering": 0, cursor: 0, direction: 0, display: 0, "dominant-baseline": 0, "enable-background": 0, fill: 0, "fill-opacity": 0, "fill-rule": 0, filter: 0, "flood-color": 0, "flood-opacity": 0, font: 0, "font-family": 0, "font-size": 0, "font-size-adjust": 0, "font-stretch": 0, "font-style": 0, "font-variant": 0, "font-weight": 0, "glyph-orientation-horizontal": 0, "glyph-orientation-vertical": 0, "image-rendering": 0, kerning: 0, "letter-spacing": 0, "lighting-color": 0, marker: 0, "marker-end": 0, "marker-mid": 0, "marker-start": 0, mask: 0, opacity: 0, overflow: 0, "pointer-events": 0, "shape-rendering": 0, "stop-color": 0, "stop-opacity": 0, stroke: 0, "stroke-dasharray": 0, "stroke-dashoffset": 0, "stroke-linecap": 0, "stroke-linejoin": 0, "stroke-miterlimit": 0, "stroke-opacity": 0, "stroke-width": 0, "text-anchor": 0, "text-decoration": 0, "text-rendering": 0, "unicode-bidi": 0, visibility: 0, "word-spacing": 0, "writing-mode": 0};
        e.on("snap.util.attr", function(t) {
            var n = e.nt(), i = {};
            n = n.substring(n.lastIndexOf(".") + 1), i[n] = t;
            var o = n.replace(/-(\w)/gi, function(t, e) {
                return e.toUpperCase()
            }), r = n.replace(/[A-Z]/g, function(t) {
                return"-" + t.toLowerCase()
            });
            oe[j](r) ? this.node.style[o] = null == t ? M : t : s(this.node, i)
        }), function() {
        }(S.prototype), i.ajax = function(t, n, i, s) {
            var r = new XMLHttpRequest, a = X();
            if (r) {
                if (o(n, "function"))
                    s = i, i = n, n = null;
                else if (o(n, "object")) {
                    var l = [];
                    for (var u in n)
                        n.hasOwnProperty(u) && l.push(encodeURIComponent(u) + "=" + encodeURIComponent(n[u]));
                    n = l.join("&")
                }
                return r.open(n ? "POST" : "GET", t, !0), n && (r.setRequestHeader("X-Requested-With", "XMLHttpRequest"), r.setRequestHeader("Content-type", "application/x-www-form-urlencoded")), i && (e.once("snap.ajax." + a + ".0", i), e.once("snap.ajax." + a + ".200", i), e.once("snap.ajax." + a + ".304", i)), r.onreadystatechange = function() {
                    4 == r.readyState && e("snap.ajax." + a + "." + r.status, s, r)
                }, 4 == r.readyState ? r : (r.send(n), r)
            }
        }, i.load = function(t, e, n) {
            i.ajax(t, function(t) {
                var s = i.parse(t.responseText);
                n ? e.call(n, s) : e(s)
            })
        };
        var re = function(t) {
            var e = t.getBoundingClientRect(), n = t.ownerDocument, i = n.body, s = n.documentElement, o = s.clientTop || i.clientTop || 0, r = s.clientLeft || i.clientLeft || 0, a = e.top + (g.win.pageYOffset || s.scrollTop || i.scrollTop) - o, l = e.left + (g.win.pageXOffset || s.scrollLeft || i.scrollLeft) - r;
            return{y: a, x: l}
        };
        return i.getElementByPoint = function(t, e) {
            var n = this, i = (n.canvas, C.doc.elementFromPoint(t, e));
            if (C.win.opera && "svg" == i.tagName) {
                var s = re(i), o = i.createSVGRect();
                o.x = t - s.x, o.y = e - s.y, o.width = o.height = 1;
                var r = i.getIntersectionList(o, null);
                r.length && (i = r[r.length - 1])
            }
            return i ? k(i) : null
        }, i.plugin = function(t) {
            t(i, w, S, C, x)
        }, C.win.Snap = i, i
    }();
    return i.plugin(function(t) {
        function e(t, e, i, s, o, r) {
            return null == e && "[object SVGMatrix]" == n.call(t) ? (this.a = t.a, this.b = t.b, this.c = t.c, this.d = t.d, this.e = t.e, void(this.f = t.f)) : void(null != t ? (this.a = +t, this.b = +e, this.c = +i, this.d = +s, this.e = +o, this.f = +r) : (this.a = 1, this.b = 0, this.c = 0, this.d = 1, this.e = 0, this.f = 0))
        }
        var n = Object.prototype.toString, i = String, s = Math, o = "";
        !function(n) {
            function r(t) {
                return t[0] * t[0] + t[1] * t[1]
            }
            function a(t) {
                var e = s.sqrt(r(t));
                t[0] && (t[0] /= e), t[1] && (t[1] /= e)
            }
            n.add = function(t, n, i, s, o, r) {
                var a, l, u, c, h = [[], [], []], d = [[this.a, this.c, this.e], [this.b, this.d, this.f], [0, 0, 1]], p = [[t, i, o], [n, s, r], [0, 0, 1]];
                for (t && t instanceof e && (p = [[t.a, t.c, t.e], [t.b, t.d, t.f], [0, 0, 1]]), a = 0; 3 > a; a++)
                    for (l = 0; 3 > l; l++) {
                        for (c = 0, u = 0; 3 > u; u++)
                            c += d[a][u] * p[u][l];
                        h[a][l] = c
                    }
                return this.a = h[0][0], this.b = h[1][0], this.c = h[0][1], this.d = h[1][1], this.e = h[0][2], this.f = h[1][2], this
            }, n.invert = function() {
                var t = this, n = t.a * t.d - t.b * t.c;
                return new e(t.d / n, -t.b / n, -t.c / n, t.a / n, (t.c * t.f - t.d * t.e) / n, (t.b * t.e - t.a * t.f) / n)
            }, n.clone = function() {
                return new e(this.a, this.b, this.c, this.d, this.e, this.f)
            }, n.translate = function(t, e) {
                return this.add(1, 0, 0, 1, t, e)
            }, n.scale = function(t, e, n, i) {
                return null == e && (e = t), (n || i) && this.add(1, 0, 0, 1, n, i), this.add(t, 0, 0, e, 0, 0), (n || i) && this.add(1, 0, 0, 1, -n, -i), this
            }, n.rotate = function(e, n, i) {
                e = t.rad(e), n = n || 0, i = i || 0;
                var o = +s.cos(e).toFixed(9), r = +s.sin(e).toFixed(9);
                return this.add(o, r, -r, o, n, i), this.add(1, 0, 0, 1, -n, -i)
            }, n.x = function(t, e) {
                return t * this.a + e * this.c + this.e
            }, n.y = function(t, e) {
                return t * this.b + e * this.d + this.f
            }, n.get = function(t) {
                return+this[i.fromCharCode(97 + t)].toFixed(4)
            }, n.toString = function() {
                return"matrix(" + [this.get(0), this.get(1), this.get(2), this.get(3), this.get(4), this.get(5)].join() + ")"
            }, n.offset = function() {
                return[this.e.toFixed(4), this.f.toFixed(4)]
            }, n.determinant = function() {
                return this.a * this.d - this.b * this.c
            }, n.split = function() {
                var e = {};
                e.dx = this.e, e.dy = this.f;
                var n = [[this.a, this.c], [this.b, this.d]];
                e.scalex = s.sqrt(r(n[0])), a(n[0]), e.shear = n[0][0] * n[1][0] + n[0][1] * n[1][1], n[1] = [n[1][0] - n[0][0] * e.shear, n[1][1] - n[0][1] * e.shear], e.scaley = s.sqrt(r(n[1])), a(n[1]), e.shear /= e.scaley, this.determinant() < 0 && (e.scalex = -e.scalex);
                var i = -n[0][1], o = n[1][1];
                return 0 > o ? (e.rotate = t.deg(s.acos(o)), 0 > i && (e.rotate = 360 - e.rotate)) : e.rotate = t.deg(s.asin(i)), e.isSimple = !(+e.shear.toFixed(9) || e.scalex.toFixed(9) != e.scaley.toFixed(9) && e.rotate), e.isSuperSimple = !+e.shear.toFixed(9) && e.scalex.toFixed(9) == e.scaley.toFixed(9) && !e.rotate, e.noRotation = !+e.shear.toFixed(9) && !e.rotate, e
            }, n.toTransformString = function(t) {
                var e = t || this.split();
                return+e.shear.toFixed(9) ? "m" + [this.get(0), this.get(1), this.get(2), this.get(3), this.get(4), this.get(5)] : (e.scalex = +e.scalex.toFixed(4), e.scaley = +e.scaley.toFixed(4), e.rotate = +e.rotate.toFixed(4), (e.dx || e.dy ? "t" + [+e.dx.toFixed(4), +e.dy.toFixed(4)] : o) + (1 != e.scalex || 1 != e.scaley ? "s" + [e.scalex, e.scaley, 0, 0] : o) + (e.rotate ? "r" + [+e.rotate.toFixed(4), 0, 0] : o))
            }
        }(e.prototype), t.Matrix = e, t.matrix = function(t, n, i, s, o, r) {
            return new e(t, n, i, s, o, r)
        }
    }), i.plugin(function(t, n, i, s, o) {
        function r(i) {
            return function(s) {
                if (e.stop(), s instanceof o && 1 == s.node.childNodes.length && ("radialGradient" == s.node.firstChild.tagName || "linearGradient" == s.node.firstChild.tagName || "pattern" == s.node.firstChild.tagName) && (s = s.node.firstChild, p(this).appendChild(s), s = h(s)), s instanceof n)
                    if ("radialGradient" == s.type || "linearGradient" == s.type || "pattern" == s.type) {
                        s.node.id || m(s.node, {id: s.id});
                        var r = v(s.node.id)
                    } else
                        r = s.attr(i);
                else if (r = t.color(s), r.error) {
                    var a = t(p(this).ownerSVGElement).gradient(s);
                    a ? (a.node.id || m(a.node, {id: a.id}), r = v(a.node.id)) : r = s
                } else
                    r = g(r);
                var l = {};
                l[i] = r, m(this.node, l), this.node.style[i] = b
            }
        }
        function a(t) {
            e.stop(), t == +t && (t += "px"), this.node.style.fontSize = t
        }
        function l(t) {
            for (var e = [], n = t.childNodes, i = 0, s = n.length; s > i; i++) {
                var o = n[i];
                3 == o.nodeType && e.push(o.nodeValue), "tspan" == o.tagName && e.push(1 == o.childNodes.length && 3 == o.firstChild.nodeType ? o.firstChild.nodeValue : l(o))
            }
            return e
        }
        function u() {
            return e.stop(), this.node.style.fontSize
        }
        var c = t._.make, h = t._.wrap, d = t.is, p = t._.getSomeDefs, f = /^url\(#?([^)]+)\)$/, m = t._.$, v = t.url, g = String, y = t._.separator, b = "";
        e.on("snap.util.attr.mask", function(t) {
            if (t instanceof n || t instanceof o) {
                if (e.stop(), t instanceof o && 1 == t.node.childNodes.length && (t = t.node.firstChild, p(this).appendChild(t), t = h(t)), "mask" == t.type)
                    var i = t;
                else
                    i = c("mask", p(this)), i.node.appendChild(t.node);
                !i.node.id && m(i.node, {id: i.id}), m(this.node, {mask: v(i.id)})
            }
        }), function(t) {
            e.on("snap.util.attr.clip", t), e.on("snap.util.attr.clip-path", t), e.on("snap.util.attr.clipPath", t)
        }(function(t) {
            if (t instanceof n || t instanceof o) {
                if (e.stop(), "clipPath" == t.type)
                    var i = t;
                else
                    i = c("clipPath", p(this)), i.node.appendChild(t.node), !i.node.id && m(i.node, {id: i.id});
                m(this.node, {"clip-path": v(i.id)})
            }
        }), e.on("snap.util.attr.fill", r("fill")), e.on("snap.util.attr.stroke", r("stroke"));
        var w = /^([lr])(?:\(([^)]*)\))?(.*)$/i;
        e.on("snap.util.grad.parse", function(t) {
            t = g(t);
            var e = t.match(w);
            if (!e)
                return null;
            var n = e[1], i = e[2], s = e[3];
            return i = i.split(/\s*,\s*/).map(function(t) {
                return+t == t ? +t : t
            }), 1 == i.length && 0 == i[0] && (i = []), s = s.split("-"), s = s.map(function(t) {
                t = t.split(":");
                var e = {color: t[0]};
                return t[1] && (e.offset = parseFloat(t[1])), e
            }), {type: n, params: i, stops: s}
        }), e.on("snap.util.attr.d", function(n) {
            e.stop(), d(n, "array") && d(n[0], "array") && (n = t.path.toString.call(n)), n = g(n), n.match(/[ruo]/i) && (n = t.path.toAbsolute(n)), m(this.node, {d: n})
        })(-1), e.on("snap.util.attr.#text", function(t) {
            e.stop(), t = g(t);
            for (var n = s.doc.createTextNode(t); this.node.firstChild; )
                this.node.removeChild(this.node.firstChild);
            this.node.appendChild(n)
        })(-1), e.on("snap.util.attr.path", function(t) {
            e.stop(), this.attr({d: t})
        })(-1), e.on("snap.util.attr.class", function(t) {
            e.stop(), this.node.className.baseVal = t
        })(-1), e.on("snap.util.attr.viewBox", function(t) {
            var n;
            n = d(t, "object") && "x"in t ? [t.x, t.y, t.width, t.height].join(" ") : d(t, "array") ? t.join(" ") : t, m(this.node, {viewBox: n}), e.stop()
        })(-1), e.on("snap.util.attr.transform", function(t) {
            this.transform(t), e.stop()
        })(-1), e.on("snap.util.attr.r", function(t) {
            "rect" == this.type && (e.stop(), m(this.node, {rx: t, ry: t}))
        })(-1), e.on("snap.util.attr.textpath", function(t) {
            if (e.stop(), "text" == this.type) {
                var i, s, o;
                if (!t && this.textPath) {
                    for (s = this.textPath; s.node.firstChild; )
                        this.node.appendChild(s.node.firstChild);
                    return s.remove(), void delete this.textPath
                }
                if (d(t, "string")) {
                    var r = p(this), a = h(r.parentNode).path(t);
                    r.appendChild(a.node), i = a.id, a.attr({id: i})
                } else
                    t = h(t), t instanceof n && (i = t.attr("id"), i || (i = t.id, t.attr({id: i})));
                if (i)
                    if (s = this.textPath, o = this.node, s)
                        s.attr({"xlink:href": "#" + i});
                    else {
                        for (s = m("textPath", {"xlink:href":"#" + i}); o.firstChild; )
                            s.appendChild(o.firstChild);
                        o.appendChild(s), this.textPath = h(s)
                    }
            }
        })(-1), e.on("snap.util.attr.text", function(t) {
            if ("text" == this.type) {
                for (var n = this.node, i = function(t) {
                    var e = m("tspan");
                    if (d(t, "array"))
                        for (var n = 0; n < t.length; n++)
                            e.appendChild(i(t[n]));
                    else
                        e.appendChild(s.doc.createTextNode(t));
                    return e.normalize && e.normalize(), e
                }; n.firstChild; )
                    n.removeChild(n.firstChild);
                for (var o = i(t); o.firstChild; )
                    n.appendChild(o.firstChild)
            }
            e.stop()
        })(-1), e.on("snap.util.attr.fontSize", a)(-1), e.on("snap.util.attr.font-size", a)(-1), e.on("snap.util.getattr.transform", function() {
            return e.stop(), this.transform()
        })(-1), e.on("snap.util.getattr.textpath", function() {
            return e.stop(), this.textPath
        })(-1), function() {
            function n(n) {
                return function() {
                    e.stop();
                    var i = s.doc.defaultView.getComputedStyle(this.node, null).getPropertyValue("marker-" + n);
                    return"none" == i ? i : t(s.doc.getElementById(i.match(f)[1]))
                }
            }
            function i(t) {
                return function(n) {
                    e.stop();
                    var i = "marker" + t.charAt(0).toUpperCase() + t.substring(1);
                    if ("" == n || !n)
                        return void(this.node.style[i] = "none");
                    if ("marker" == n.type) {
                        var s = n.node.id;
                        return s || m(n.node, {id: n.id}), void(this.node.style[i] = v(s))
                    }
                }
            }
            e.on("snap.util.getattr.marker-end", n("end"))(-1), e.on("snap.util.getattr.markerEnd", n("end"))(-1), e.on("snap.util.getattr.marker-start", n("start"))(-1), e.on("snap.util.getattr.markerStart", n("start"))(-1), e.on("snap.util.getattr.marker-mid", n("mid"))(-1), e.on("snap.util.getattr.markerMid", n("mid"))(-1), e.on("snap.util.attr.marker-end", i("end"))(-1), e.on("snap.util.attr.markerEnd", i("end"))(-1), e.on("snap.util.attr.marker-start", i("start"))(-1), e.on("snap.util.attr.markerStart", i("start"))(-1), e.on("snap.util.attr.marker-mid", i("mid"))(-1), e.on("snap.util.attr.markerMid", i("mid"))(-1)
        }(), e.on("snap.util.getattr.r", function() {
            return"rect" == this.type && m(this.node, "rx") == m(this.node, "ry") ? (e.stop(), m(this.node, "rx")) : void 0
        })(-1), e.on("snap.util.getattr.text", function() {
            if ("text" == this.type || "tspan" == this.type) {
                e.stop();
                var t = l(this.node);
                return 1 == t.length ? t[0] : t
            }
        })(-1), e.on("snap.util.getattr.#text", function() {
            return this.node.textContent
        })(-1), e.on("snap.util.getattr.viewBox", function() {
            e.stop();
            var n = m(this.node, "viewBox");
            return n ? (n = n.split(y), t._.box(+n[0], +n[1], +n[2], +n[3])) : void 0
        })(-1), e.on("snap.util.getattr.points", function() {
            var t = m(this.node, "points");
            return e.stop(), t ? t.split(y) : void 0
        })(-1), e.on("snap.util.getattr.path", function() {
            var t = m(this.node, "d");
            return e.stop(), t
        })(-1), e.on("snap.util.getattr.class", function() {
            return this.node.className.baseVal
        })(-1), e.on("snap.util.getattr.fontSize", u)(-1), e.on("snap.util.getattr.font-size", u)(-1)
    }), i.plugin(function() {
        function t(t) {
            return t
        }
        function n(t) {
            return function(e) {
                return+e.toFixed(3) + t
            }
        }
        var i = {"+": function(t, e) {
                return t + e
            }, "-": function(t, e) {
                return t - e
            }, "/": function(t, e) {
                return t / e
            }, "*": function(t, e) {
                return t * e
            }}, s = String, o = /[a-z]+$/i, r = /^\s*([+\-\/*])\s*=\s*([\d.eE+\-]+)\s*([^\d\s]+)?\s*$/;
        e.on("snap.util.attr", function(t) {
            var n = s(t).match(r);
            if (n) {
                var a = e.nt(), l = a.substring(a.lastIndexOf(".") + 1), u = this.attr(l), c = {};
                e.stop();
                var h = n[3] || "", d = u.match(o), p = i[n[1]];
                if (d && d == h ? t = p(parseFloat(u), +n[2]) : (u = this.asPX(l), t = p(this.asPX(l), this.asPX(l, n[2] + h))), isNaN(u) || isNaN(t))
                    return;
                c[l] = t, this.attr(c)
            }
        })(-10), e.on("snap.util.equal", function(a, l) {
            var u = s(this.attr(a) || ""), c = s(l).match(r);
            if (c) {
                e.stop();
                var h = c[3] || "", d = u.match(o), p = i[c[1]];
                return d && d == h ? {from: parseFloat(u), to: p(parseFloat(u), +c[2]), f: n(d)} : (u = this.asPX(a), {from: u, to: p(u, this.asPX(a, c[2] + h)), f: t})
            }
        })(-10)
    }), i.plugin(function(t, n, i, s) {
        var o = i.prototype, r = t.is;
        o.rect = function(t, e, n, i, s, o) {
            var a;
            return null == o && (o = s), r(t, "object") && "[object Object]" == t ? a = t : null != t && (a = {x: t, y: e, width: n, height: i}, null != s && (a.rx = s, a.ry = o)), this.el("rect", a)
        }, o.circle = function(t, e, n) {
            var i;
            return r(t, "object") && "[object Object]" == t ? i = t : null != t && (i = {cx: t, cy: e, r: n}), this.el("circle", i)
        };
        var a = function() {
            function t() {
                this.parentNode.removeChild(this)
            }
            return function(e, n) {
                var i = s.doc.createElement("img"), o = s.doc.body;
                i.style.cssText = "position:absolute;left:-9999em;top:-9999em", i.onload = function() {
                    n.call(i), i.onload = i.onerror = null, o.removeChild(i)
                }, i.onerror = t, o.appendChild(i), i.src = e
            }
        }();
        o.image = function(e, n, i, s, o) {
            var l = this.el("image");
            if (r(e, "object") && "src"in e)
                l.attr(e);
            else if (null != e) {
                var u = {"xlink:href": e, preserveAspectRatio: "none"};
                null != n && null != i && (u.x = n, u.y = i), null != s && null != o ? (u.width = s, u.height = o) : a(e, function() {
                    t._.$(l.node, {width: this.offsetWidth, height: this.offsetHeight})
                }), t._.$(l.node, u)
            }
            return l
        }, o.ellipse = function(t, e, n, i) {
            var s;
            return r(t, "object") && "[object Object]" == t ? s = t : null != t && (s = {cx: t, cy: e, rx: n, ry: i}), this.el("ellipse", s)
        }, o.path = function(t) {
            var e;
            return r(t, "object") && !r(t, "array") ? e = t : t && (e = {d: t}), this.el("path", e)
        }, o.group = o.g = function(t) {
            var e = this.el("g");
            return 1 == arguments.length && t && !t.type ? e.attr(t) : arguments.length && e.add(Array.prototype.slice.call(arguments, 0)), e
        }, o.svg = function(t, e, n, i, s, o, a, l) {
            var u = {};
            return r(t, "object") && null == e ? u = t : (null != t && (u.x = t), null != e && (u.y = e), null != n && (u.width = n), null != i && (u.height = i), null != s && null != o && null != a && null != l && (u.viewBox = [s, o, a, l])), this.el("svg", u)
        }, o.mask = function(t) {
            var e = this.el("mask");
            return 1 == arguments.length && t && !t.type ? e.attr(t) : arguments.length && e.add(Array.prototype.slice.call(arguments, 0)), e
        }, o.ptrn = function(t, e, n, i, s, o, a, l) {
            if (r(t, "object"))
                var u = t;
            else
                arguments.length ? (u = {}, null != t && (u.x = t), null != e && (u.y = e), null != n && (u.width = n), null != i && (u.height = i), null != s && null != o && null != a && null != l && (u.viewBox = [s, o, a, l])) : u = {patternUnits: "userSpaceOnUse"};
            return this.el("pattern", u)
        }, o.use = function(t) {
            return null != t ? (make("use", this.node), t instanceof n && (t.attr("id") || t.attr({id: ID()}), t = t.attr("id")), this.el("use", {"xlink:href": t})) : n.prototype.use.call(this)
        }, o.text = function(t, e, n) {
            var i = {};
            return r(t, "object") ? i = t : null != t && (i = {x: t, y: e, text: n || ""}), this.el("text", i)
        }, o.line = function(t, e, n, i) {
            var s = {};
            return r(t, "object") ? s = t : null != t && (s = {x1: t, x2: n, y1: e, y2: i}), this.el("line", s)
        }, o.polyline = function(t) {
            arguments.length > 1 && (t = Array.prototype.slice.call(arguments, 0));
            var e = {};
            return r(t, "object") && !r(t, "array") ? e = t : null != t && (e = {points: t}), this.el("polyline", e)
        }, o.polygon = function(t) {
            arguments.length > 1 && (t = Array.prototype.slice.call(arguments, 0));
            var e = {};
            return r(t, "object") && !r(t, "array") ? e = t : null != t && (e = {points: t}), this.el("polygon", e)
        }, function() {
            function n() {
                return this.selectAll("stop")
            }
            function i(e, n) {
                var i = u("stop"), s = {offset: +n + "%"};
                return e = t.color(e), s["stop-color"] = e.hex, e.opacity < 1 && (s["stop-opacity"] = e.opacity), u(i, s), this.node.appendChild(i), this
            }
            function s() {
                if ("linearGradient" == this.type) {
                    var e = u(this.node, "x1") || 0, n = u(this.node, "x2") || 1, i = u(this.node, "y1") || 0, s = u(this.node, "y2") || 0;
                    return t._.box(e, i, math.abs(n - e), math.abs(s - i))
                }
                var o = this.node.cx || .5, r = this.node.cy || .5, a = this.node.r || 0;
                return t._.box(o - a, r - a, 2 * a, 2 * a)
            }
            function r(t, n) {
                function i(t, e) {
                    for (var n = (e - h) / (t - d), i = d; t > i; i++)
                        r[i].offset = +(+h + n * (i - d)).toFixed(2);
                    d = t, h = e
                }
                var s, o = e("snap.util.grad.parse", null, n).firstDefined();
                if (!o)
                    return null;
                o.params.unshift(t), s = "l" == o.type.toLowerCase() ? a.apply(0, o.params) : l.apply(0, o.params), o.type != o.type.toLowerCase() && u(s.node, {gradientUnits: "userSpaceOnUse"});
                var r = o.stops, c = r.length, h = 0, d = 0;
                c--;
                for (var p = 0; c > p; p++)
                    "offset"in r[p] && i(p, r[p].offset);
                for (r[c].offset = r[c].offset || 100, i(c, r[c].offset), p = 0; c >= p; p++) {
                    var f = r[p];
                    s.addStop(f.color, f.offset)
                }
                return s
            }
            function a(e, o, r, a, l) {
                var c = t._.make("linearGradient", e);
                return c.stops = n, c.addStop = i, c.getBBox = s, null != o && u(c.node, {x1: o, y1: r, x2: a, y2: l}), c
            }
            function l(e, o, r, a, l, c) {
                var h = t._.make("radialGradient", e);
                return h.stops = n, h.addStop = i, h.getBBox = s, null != o && u(h.node, {cx: o, cy: r, r: a}), null != l && null != c && u(h.node, {fx: l, fy: c}), h
            }
            var u = t._.$;
            o.gradient = function(t) {
                return r(this.defs, t)
            }, o.gradientLinear = function(t, e, n, i) {
                return a(this.defs, t, e, n, i)
            }, o.gradientRadial = function(t, e, n, i, s) {
                return l(this.defs, t, e, n, i, s)
            }, o.toString = function() {
                var e, n = this.node.ownerDocument, i = n.createDocumentFragment(), s = n.createElement("div"), o = this.node.cloneNode(!0);
                return i.appendChild(s), s.appendChild(o), t._.$(o, {xmlns: "http://www.w3.org/2000/svg"}), e = s.innerHTML, i.removeChild(i.firstChild), e
            }, o.clear = function() {
                for (var t, e = this.node.firstChild; e; )
                    t = e.nextSibling, "defs" != e.tagName ? e.parentNode.removeChild(e) : o.clear.call({node: e}), e = t
            }
        }()
    }), i.plugin(function(t, e) {
        function n(t) {
            var e = n.ps = n.ps || {};
            return e[t] ? e[t].sleep = 100 : e[t] = {sleep: 100}, setTimeout(function() {
                for (var n in e)
                    e[D](n) && n != t && (e[n].sleep--, !e[n].sleep && delete e[n])
            }), e[t]
        }
        function i(t, e, n, i) {
            return null == t && (t = e = n = i = 0), null == e && (e = t.y, n = t.width, i = t.height, t = t.x), {x: t, y: e, width: n, w: n, height: i, h: i, x2: t + n, y2: e + i, cx: t + n / 2, cy: e + i / 2, r1: R.min(n, i) / 2, r2: R.max(n, i) / 2, r0: R.sqrt(n * n + i * i) / 2, path: _(t, e, n, i), vb: [t, e, n, i].join(" ")}
        }
        function s() {
            return this.join(",").replace(q, "$1")
        }
        function o(t) {
            var e = M(t);
            return e.toString = s, e
        }
        function r(t, e, n, i, s, o, r, a, u) {
            return null == u ? p(t, e, n, i, s, o, r, a) : l(t, e, n, i, s, o, r, a, f(t, e, n, i, s, o, r, a, u))
        }
        function a(n, i) {
            function s(t) {
                return+(+t).toFixed(3)
            }
            return t._.cacher(function(t, o, a) {
                t instanceof e && (t = t.attr("d")), t = A(t);
                for (var u, c, h, d, p, f = "", m = {}, v = 0, g = 0, y = t.length; y > g; g++) {
                    if (h = t[g], "M" == h[0])
                        u = +h[1], c = +h[2];
                    else {
                        if (d = r(u, c, h[1], h[2], h[3], h[4], h[5], h[6]), v + d > o) {
                            if (i && !m.start) {
                                if (p = r(u, c, h[1], h[2], h[3], h[4], h[5], h[6], o - v), f += ["C" + s(p.start.x), s(p.start.y), s(p.m.x), s(p.m.y), s(p.x), s(p.y)], a)
                                    return f;
                                m.start = f, f = ["M" + s(p.x), s(p.y) + "C" + s(p.n.x), s(p.n.y), s(p.end.x), s(p.end.y), s(h[5]), s(h[6])].join(), v += d, u = +h[5], c = +h[6];
                                continue
                            }
                            if (!n && !i)
                                return p = r(u, c, h[1], h[2], h[3], h[4], h[5], h[6], o - v)
                        }
                        v += d, u = +h[5], c = +h[6]
                    }
                    f += h.shift() + h
                }
                return m.end = f, p = n ? v : i ? m : l(u, c, h[0], h[1], h[2], h[3], h[4], h[5], 1)
            }, null, t._.clone)
        }
        function l(t, e, n, i, s, o, r, a, l) {
            var u = 1 - l, c = z(u, 3), h = z(u, 2), d = l * l, p = d * l, f = c * t + 3 * h * l * n + 3 * u * l * l * s + p * r, m = c * e + 3 * h * l * i + 3 * u * l * l * o + p * a, v = t + 2 * l * (n - t) + d * (s - 2 * n + t), g = e + 2 * l * (i - e) + d * (o - 2 * i + e), y = n + 2 * l * (s - n) + d * (r - 2 * s + n), b = i + 2 * l * (o - i) + d * (a - 2 * o + i), w = u * t + l * n, x = u * e + l * i, _ = u * s + l * r, S = u * o + l * a, k = 90 - 180 * R.atan2(v - y, g - b) / I;
            return{x: f, y: m, m: {x: v, y: g}, n: {x: y, y: b}, start: {x: w, y: x}, end: {x: _, y: S}, alpha: k}
        }
        function u(e, n, s, o, r, a, l, u) {
            t.is(e, "array") || (e = [e, n, s, o, r, a, l, u]);
            var c = P.apply(null, e);
            return i(c.min.x, c.min.y, c.max.x - c.min.x, c.max.y - c.min.y)
        }
        function c(t, e, n) {
            return e >= t.x && e <= t.x + t.width && n >= t.y && n <= t.y + t.height
        }
        function h(t, e) {
            return t = i(t), e = i(e), c(e, t.x, t.y) || c(e, t.x2, t.y) || c(e, t.x, t.y2) || c(e, t.x2, t.y2) || c(t, e.x, e.y) || c(t, e.x2, e.y) || c(t, e.x, e.y2) || c(t, e.x2, e.y2) || (t.x < e.x2 && t.x > e.x || e.x < t.x2 && e.x > t.x) && (t.y < e.y2 && t.y > e.y || e.y < t.y2 && e.y > t.y)
        }
        function d(t, e, n, i, s) {
            var o = -3 * e + 9 * n - 9 * i + 3 * s, r = t * o + 6 * e - 12 * n + 6 * i;
            return t * r - 3 * e + 3 * n
        }
        function p(t, e, n, i, s, o, r, a, l) {
            null == l && (l = 1), l = l > 1 ? 1 : 0 > l ? 0 : l;
            for (var u = l / 2, c = 12, h = [-.1252, .1252, -.3678, .3678, -.5873, .5873, -.7699, .7699, -.9041, .9041, -.9816, .9816], p = [.2491, .2491, .2335, .2335, .2032, .2032, .1601, .1601, .1069, .1069, .0472, .0472], f = 0, m = 0; c > m; m++) {
                var v = u * h[m] + u, g = d(v, t, n, s, r), y = d(v, e, i, o, a), b = g * g + y * y;
                f += p[m] * R.sqrt(b)
            }
            return u * f
        }
        function f(t, e, n, i, s, o, r, a, l) {
            if (!(0 > l || p(t, e, n, i, s, o, r, a) < l)) {
                var u, c = 1, h = c / 2, d = c - h, f = .01;
                for (u = p(t, e, n, i, s, o, r, a, d); $(u - l) > f; )
                    h /= 2, d += (l > u ? 1 : -1) * h, u = p(t, e, n, i, s, o, r, a, d);
                return d
            }
        }
        function m(t, e, n, i, s, o, r, a) {
            if (!(W(t, n) < B(s, r) || B(t, n) > W(s, r) || W(e, i) < B(o, a) || B(e, i) > W(o, a))) {
                var l = (t * i - e * n) * (s - r) - (t - n) * (s * a - o * r), u = (t * i - e * n) * (o - a) - (e - i) * (s * a - o * r), c = (t - n) * (o - a) - (e - i) * (s - r);
                if (c) {
                    var h = l / c, d = u / c, p = +h.toFixed(2), f = +d.toFixed(2);
                    if (!(p < +B(t, n).toFixed(2) || p > +W(t, n).toFixed(2) || p < +B(s, r).toFixed(2) || p > +W(s, r).toFixed(2) || f < +B(e, i).toFixed(2) || f > +W(e, i).toFixed(2) || f < +B(o, a).toFixed(2) || f > +W(o, a).toFixed(2)))
                        return{x: h, y: d}
                }
            }
        }
        function v(t, e, n) {
            var i = u(t), s = u(e);
            if (!h(i, s))
                return n ? 0 : [];
            for (var o = p.apply(0, t), r = p.apply(0, e), a = ~~(o / 8), c = ~~(r / 8), d = [], f = [], v = {}, g = n ? 0 : [], y = 0; a + 1 > y; y++) {
                var b = l.apply(0, t.concat(y / a));
                d.push({x: b.x, y: b.y, t: y / a})
            }
            for (y = 0; c + 1 > y; y++)
                b = l.apply(0, e.concat(y / c)), f.push({x: b.x, y: b.y, t: y / c});
            for (y = 0; a > y; y++)
                for (var w = 0; c > w; w++) {
                    var x = d[y], _ = d[y + 1], S = f[w], k = f[w + 1], C = $(_.x - x.x) < .001 ? "y" : "x", j = $(k.x - S.x) < .001 ? "y" : "x", T = m(x.x, x.y, _.x, _.y, S.x, S.y, k.x, k.y);
                    if (T) {
                        if (v[T.x.toFixed(4)] == T.y.toFixed(4))
                            continue;
                        v[T.x.toFixed(4)] = T.y.toFixed(4);
                        var E = x.t + $((T[C] - x[C]) / (_[C] - x[C])) * (_.t - x.t), P = S.t + $((T[j] - S[j]) / (k[j] - S[j])) * (k.t - S.t);
                        E >= 0 && 1 >= E && P >= 0 && 1 >= P && (n ? g++ : g.push({x: T.x, y: T.y, t1: E, t2: P}))
                    }
                }
            return g
        }
        function g(t, e) {
            return b(t, e)
        }
        function y(t, e) {
            return b(t, e, 1)
        }
        function b(t, e, n) {
            t = A(t), e = A(e);
            for (var i, s, o, r, a, l, u, c, h, d, p = n ? 0 : [], f = 0, m = t.length; m > f; f++) {
                var g = t[f];
                if ("M" == g[0])
                    i = a = g[1], s = l = g[2];
                else {
                    "C" == g[0] ? (h = [i, s].concat(g.slice(1)), i = h[6], s = h[7]) : (h = [i, s, i, s, a, l, a, l], i = a, s = l);
                    for (var y = 0, b = e.length; b > y; y++) {
                        var w = e[y];
                        if ("M" == w[0])
                            o = u = w[1], r = c = w[2];
                        else {
                            "C" == w[0] ? (d = [o, r].concat(w.slice(1)), o = d[6], r = d[7]) : (d = [o, r, o, r, u, c, u, c], o = u, r = c);
                            var x = v(h, d, n);
                            if (n)
                                p += x;
                            else {
                                for (var _ = 0, S = x.length; S > _; _++)
                                    x[_].segment1 = f, x[_].segment2 = y, x[_].bez1 = h, x[_].bez2 = d;
                                p = p.concat(x)
                            }
                        }
                    }
                }
            }
            return p
        }
        function w(t, e, n) {
            var i = x(t);
            return c(i, e, n) && b(t, [["M", e, n], ["H", i.x2 + 10]], 1) % 2 == 1
        }
        function x(t) {
            var e = n(t);
            if (e.bbox)
                return M(e.bbox);
            if (!t)
                return i();
            t = A(t);
            for (var s, o = 0, r = 0, a = [], l = [], u = 0, c = t.length; c > u; u++)
                if (s = t[u], "M" == s[0])
                    o = s[1], r = s[2], a.push(o), l.push(r);
                else {
                    var h = P(o, r, s[1], s[2], s[3], s[4], s[5], s[6]);
                    a = a.concat(h.min.x, h.max.x), l = l.concat(h.min.y, h.max.y), o = s[5], r = s[6]
                }
            var d = B.apply(0, a), p = B.apply(0, l), f = W.apply(0, a), m = W.apply(0, l), v = i(d, p, f - d, m - p);
            return e.bbox = M(v), v
        }
        function _(t, e, n, i, o) {
            if (o)
                return[["M", +t + +o, e], ["l", n - 2 * o, 0], ["a", o, o, 0, 0, 1, o, o], ["l", 0, i - 2 * o], ["a", o, o, 0, 0, 1, -o, o], ["l", 2 * o - n, 0], ["a", o, o, 0, 0, 1, -o, -o], ["l", 0, 2 * o - i], ["a", o, o, 0, 0, 1, o, -o], ["z"]];
            var r = [["M", t, e], ["l", n, 0], ["l", 0, i], ["l", -n, 0], ["z"]];
            return r.toString = s, r
        }
        function S(t, e, n, i, o) {
            if (null == o && null == i && (i = n), t = +t, e = +e, n = +n, i = +i, null != o)
                var r = Math.PI / 180, a = t + n * Math.cos(-i * r), l = t + n * Math.cos(-o * r), u = e + n * Math.sin(-i * r), c = e + n * Math.sin(-o * r), h = [["M", a, u], ["A", n, n, 0, +(o - i > 180), 0, l, c]];
            else
                h = [["M", t, e], ["m", 0, -i], ["a", n, i, 0, 1, 1, 0, 2 * i], ["a", n, i, 0, 1, 1, 0, -2 * i], ["z"]];
            return h.toString = s, h
        }
        function k(e) {
            var i = n(e), r = String.prototype.toLowerCase;
            if (i.rel)
                return o(i.rel);
            t.is(e, "array") && t.is(e && e[0], "array") || (e = t.parsePathString(e));
            var a = [], l = 0, u = 0, c = 0, h = 0, d = 0;
            "M" == e[0][0] && (l = e[0][1], u = e[0][2], c = l, h = u, d++, a.push(["M", l, u]));
            for (var p = d, f = e.length; f > p; p++) {
                var m = a[p] = [], v = e[p];
                if (v[0] != r.call(v[0]))
                    switch (m[0] = r.call(v[0]), m[0]) {
                        case"a":
                            m[1] = v[1], m[2] = v[2], m[3] = v[3], m[4] = v[4], m[5] = v[5], m[6] = +(v[6] - l).toFixed(3), m[7] = +(v[7] - u).toFixed(3);
                            break;
                        case"v":
                            m[1] = +(v[1] - u).toFixed(3);
                            break;
                        case"m":
                            c = v[1], h = v[2];
                        default:
                            for (var g = 1, y = v.length; y > g; g++)
                                m[g] = +(v[g] - (g % 2 ? l : u)).toFixed(3)
                    }
                else {
                    m = a[p] = [], "m" == v[0] && (c = v[1] + l, h = v[2] + u);
                    for (var b = 0, w = v.length; w > b; b++)
                        a[p][b] = v[b]
                }
                var x = a[p].length;
                switch (a[p][0]) {
                    case"z":
                        l = c, u = h;
                        break;
                    case"h":
                        l += +a[p][x - 1];
                        break;
                    case"v":
                        u += +a[p][x - 1];
                        break;
                    default:
                        l += +a[p][x - 2], u += +a[p][x - 1]
                }
            }
            return a.toString = s, i.rel = o(a), a
        }
        function C(e) {
            var i = n(e);
            if (i.abs)
                return o(i.abs);
            if (F(e, "array") && F(e && e[0], "array") || (e = t.parsePathString(e)), !e || !e.length)
                return[["M", 0, 0]];
            var r, a = [], l = 0, u = 0, c = 0, h = 0, d = 0;
            "M" == e[0][0] && (l = +e[0][1], u = +e[0][2], c = l, h = u, d++, a[0] = ["M", l, u]);
            for (var p, f, m = 3 == e.length && "M" == e[0][0] && "R" == e[1][0].toUpperCase() && "Z" == e[2][0].toUpperCase(), v = d, g = e.length; g > v; v++) {
                if (a.push(p = []), f = e[v], r = f[0], r != r.toUpperCase())
                    switch (p[0] = r.toUpperCase(), p[0]) {
                        case"A":
                            p[1] = f[1], p[2] = f[2], p[3] = f[3], p[4] = f[4], p[5] = f[5], p[6] = +f[6] + l, p[7] = +f[7] + u;
                            break;
                        case"V":
                            p[1] = +f[1] + u;
                            break;
                        case"H":
                            p[1] = +f[1] + l;
                            break;
                        case"R":
                            for (var y = [l, u].concat(f.slice(1)), b = 2, w = y.length; w > b; b++)
                                y[b] = +y[b] + l, y[++b] = +y[b] + u;
                            a.pop(), a = a.concat(L(y, m));
                            break;
                        case"O":
                            a.pop(), y = S(l, u, f[1], f[2]), y.push(y[0]), a = a.concat(y);
                            break;
                        case"U":
                            a.pop(), a = a.concat(S(l, u, f[1], f[2], f[3])), p = ["U"].concat(a[a.length - 1].slice(-2));
                            break;
                        case"M":
                            c = +f[1] + l, h = +f[2] + u;
                        default:
                            for (b = 1, w = f.length; w > b; b++)
                                p[b] = +f[b] + (b % 2 ? l : u)
                    }
                else if ("R" == r)
                    y = [l, u].concat(f.slice(1)), a.pop(), a = a.concat(L(y, m)), p = ["R"].concat(f.slice(-2));
                else if ("O" == r)
                    a.pop(), y = S(l, u, f[1], f[2]), y.push(y[0]), a = a.concat(y);
                else if ("U" == r)
                    a.pop(), a = a.concat(S(l, u, f[1], f[2], f[3])), p = ["U"].concat(a[a.length - 1].slice(-2));
                else
                    for (var x = 0, _ = f.length; _ > x; x++)
                        p[x] = f[x];
                if (r = r.toUpperCase(), "O" != r)
                    switch (p[0]) {
                        case"Z":
                            l = +c, u = +h;
                            break;
                        case"H":
                            l = p[1];
                            break;
                        case"V":
                            u = p[1];
                            break;
                        case"M":
                            c = p[p.length - 2], h = p[p.length - 1];
                        default:
                            l = p[p.length - 2], u = p[p.length - 1]
                    }
            }
            return a.toString = s, i.abs = o(a), a
        }
        function j(t, e, n, i) {
            return[t, e, n, i, n, i]
        }
        function T(t, e, n, i, s, o) {
            var r = 1 / 3, a = 2 / 3;
            return[r * t + a * n, r * e + a * i, r * s + a * n, r * o + a * i, s, o]
        }
        function E(e, n, i, s, o, r, a, l, u, c) {
            var h, d = 120 * I / 180, p = I / 180 * (+o || 0), f = [], m = t._.cacher(function(t, e, n) {
                var i = t * R.cos(n) - e * R.sin(n), s = t * R.sin(n) + e * R.cos(n);
                return{x: i, y: s}
            });
            if (c)
                k = c[0], C = c[1], _ = c[2], S = c[3];
            else {
                h = m(e, n, -p), e = h.x, n = h.y, h = m(l, u, -p), l = h.x, u = h.y;
                var v = (R.cos(I / 180 * o), R.sin(I / 180 * o), (e - l) / 2), g = (n - u) / 2, y = v * v / (i * i) + g * g / (s * s);
                y > 1 && (y = R.sqrt(y), i = y * i, s = y * s);
                var b = i * i, w = s * s, x = (r == a ? -1 : 1) * R.sqrt($((b * w - b * g * g - w * v * v) / (b * g * g + w * v * v))), _ = x * i * g / s + (e + l) / 2, S = x * -s * v / i + (n + u) / 2, k = R.asin(((n - S) / s).toFixed(9)), C = R.asin(((u - S) / s).toFixed(9));
                k = _ > e ? I - k : k, C = _ > l ? I - C : C, 0 > k && (k = 2 * I + k), 0 > C && (C = 2 * I + C), a && k > C && (k -= 2 * I), !a && C > k && (C -= 2 * I)
            }
            var j = C - k;
            if ($(j) > d) {
                var T = C, P = l, A = u;
                C = k + d * (a && C > k ? 1 : -1), l = _ + i * R.cos(C), u = S + s * R.sin(C), f = E(l, u, i, s, o, 0, a, P, A, [C, T, _, S])
            }
            j = C - k;
            var N = R.cos(k), L = R.sin(k), H = R.cos(C), F = R.sin(C), M = R.tan(j / 4), D = 4 / 3 * i * M, q = 4 / 3 * s * M, O = [e, n], B = [e + D * L, n - q * N], W = [l + D * F, u - q * H], z = [l, u];
            if (B[0] = 2 * O[0] - B[0], B[1] = 2 * O[1] - B[1], c)
                return[B, W, z].concat(f);
            f = [B, W, z].concat(f).join().split(",");
            for (var V = [], U = 0, X = f.length; X > U; U++)
                V[U] = U % 2 ? m(f[U - 1], f[U], p).y : m(f[U], f[U + 1], p).x;
            return V
        }
        function P(t, e, n, i, s, o, r, a) {
            for (var l, u, c, h, d, p, f, m, v = [], g = [[], []], y = 0; 2 > y; ++y)
                if (0 == y ? (u = 6 * t - 12 * n + 6 * s, l = -3 * t + 9 * n - 9 * s + 3 * r, c = 3 * n - 3 * t) : (u = 6 * e - 12 * i + 6 * o, l = -3 * e + 9 * i - 9 * o + 3 * a, c = 3 * i - 3 * e), $(l) < 1e-12) {
                    if ($(u) < 1e-12)
                        continue;
                    h = -c / u, h > 0 && 1 > h && v.push(h)
                } else
                    f = u * u - 4 * c * l, m = R.sqrt(f), 0 > f || (d = (-u + m) / (2 * l), d > 0 && 1 > d && v.push(d), p = (-u - m) / (2 * l), p > 0 && 1 > p && v.push(p));
            for (var b, w = v.length, x = w; w--; )
                h = v[w], b = 1 - h, g[0][w] = b * b * b * t + 3 * b * b * h * n + 3 * b * h * h * s + h * h * h * r, g[1][w] = b * b * b * e + 3 * b * b * h * i + 3 * b * h * h * o + h * h * h * a;
            return g[0][x] = t, g[1][x] = e, g[0][x + 1] = r, g[1][x + 1] = a, g[0].length = g[1].length = x + 2, {min: {x: B.apply(0, g[0]), y: B.apply(0, g[1])}, max: {x: W.apply(0, g[0]), y: W.apply(0, g[1])}}
        }
        function A(t, e) {
            var i = !e && n(t);
            if (!e && i.curve)
                return o(i.curve);
            for (var s = C(t), r = e && C(e), a = {x: 0, y: 0, bx: 0, by: 0, X: 0, Y: 0, qx: null, qy: null}, l = {x: 0, y: 0, bx: 0, by: 0, X: 0, Y: 0, qx: null, qy: null}, u = (function(t, e, n) {
                var i, s;
                if (!t)
                    return["C", e.x, e.y, e.x, e.y, e.x, e.y];
                switch (!(t[0]in {
                        T: 1, Q: 1}) && (e.qx = e.qy = null), t[0]){case"M":
                        e.X = t[1], e.Y = t[2];
                        break;
                    case"A":
                        t = ["C"].concat(E.apply(0, [e.x, e.y].concat(t.slice(1))));
                        break;
                    case"S":
                        "C" == n || "S" == n ? (i = 2 * e.x - e.bx, s = 2 * e.y - e.by) : (i = e.x, s = e.y), t = ["C", i, s].concat(t.slice(1));
                        break;
                    case"T":
                        "Q" == n || "T" == n ? (e.qx = 2 * e.x - e.qx, e.qy = 2 * e.y - e.qy) : (e.qx = e.x, e.qy = e.y), t = ["C"].concat(T(e.x, e.y, e.qx, e.qy, t[1], t[2]));
                        break;
                    case"Q":
                        e.qx = t[1], e.qy = t[2], t = ["C"].concat(T(e.x, e.y, t[1], t[2], t[3], t[4]));
                        break;
                    case"L":
                        t = ["C"].concat(j(e.x, e.y, t[1], t[2]));
                        break;
                    case"H":
                        t = ["C"].concat(j(e.x, e.y, t[1], e.y));
                        break;
                    case"V":
                        t = ["C"].concat(j(e.x, e.y, e.x, t[1]));
                        break;
                    case"Z":
                        t = ["C"].concat(j(e.x, e.y, e.X, e.Y))
                }
                return t
            }), c = function(t, e) {
                if (t[e].length > 7) {
                    t[e].shift();
                    for (var n = t[e]; n.length; )
                        d[e] = "A", r && (p[e] = "A"), t.splice(e++, 0, ["C"].concat(n.splice(0, 6)));
                    t.splice(e, 1), g = W(s.length, r && r.length || 0)
                }
            }, h = function(t, e, n, i, o) {
                t && e && "M" == t[o][0] && "M" != e[o][0] && (e.splice(o, 0, ["M", i.x, i.y]), n.bx = 0, n.by = 0, n.x = t[o][1], n.y = t[o][2], g = W(s.length, r && r.length || 0))
            }, d = [], p = [], f = "", m = "", v = 0, g = W(s.length, r && r.length || 0); g > v; v++) {
                s[v] && (f = s[v][0]), "C" != f && (d[v] = f, v && (m = d[v - 1])), s[v] = u(s[v], a, m), "A" != d[v] && "C" == f && (d[v] = "C"), c(s, v), r && (r[v] && (f = r[v][0]), "C" != f && (p[v] = f, v && (m = p[v - 1])), r[v] = u(r[v], l, m), "A" != p[v] && "C" == f && (p[v] = "C"), c(r, v)), h(s, r, a, l, v), h(r, s, l, a, v);
                var y = s[v], b = r && r[v], w = y.length, x = r && b.length;
                a.x = y[w - 2], a.y = y[w - 1], a.bx = O(y[w - 4]) || a.x, a.by = O(y[w - 3]) || a.y, l.bx = r && (O(b[x - 4]) || l.x), l.by = r && (O(b[x - 3]) || l.y), l.x = r && b[x - 2], l.y = r && b[x - 1]
            }
            return r || (i.curve = o(s)), r ? [s, r] : s
        }
        function N(t, e) {
            if (!e)
                return t;
            var n, i, s, o, r, a, l;
            for (t = A(t), s = 0, r = t.length; r > s; s++)
                for (l = t[s], o = 1, a = l.length; a > o; o += 2)
                    n = e.x(l[o], l[o + 1]), i = e.y(l[o], l[o + 1]), l[o] = n, l[o + 1] = i;
            return t
        }
        function L(t, e) {
            for (var n = [], i = 0, s = t.length; s - 2 * !e > i; i += 2) {
                var o = [{x: +t[i - 2], y: +t[i - 1]}, {x: +t[i], y: +t[i + 1]}, {x: +t[i + 2], y: +t[i + 3]}, {x: +t[i + 4], y: +t[i + 5]}];
                e ? i ? s - 4 == i ? o[3] = {x: +t[0], y: +t[1]} : s - 2 == i && (o[2] = {x: +t[0], y: +t[1]}, o[3] = {x: +t[2], y: +t[3]}) : o[0] = {x: +t[s - 2], y: +t[s - 1]} : s - 4 == i ? o[3] = o[2] : i || (o[0] = {x: +t[i], y: +t[i + 1]}), n.push(["C", (-o[0].x + 6 * o[1].x + o[2].x) / 6, (-o[0].y + 6 * o[1].y + o[2].y) / 6, (o[1].x + 6 * o[2].x - o[3].x) / 6, (o[1].y + 6 * o[2].y - o[3].y) / 6, o[2].x, o[2].y])
            }
            return n
        }
        var H = e.prototype, F = t.is, M = t._.clone, D = "hasOwnProperty", q = /,?([a-z]),?/gi, O = parseFloat, R = Math, I = R.PI, B = R.min, W = R.max, z = R.pow, $ = R.abs, V = a(1), U = a(), X = a(0, 1), Y = t._unit2px, G = {path: function(t) {
                return t.attr("path")
            }, circle: function(t) {
                var e = Y(t);
                return S(e.cx, e.cy, e.r)
            }, ellipse: function(t) {
                var e = Y(t);
                return S(e.cx || 0, e.cy || 0, e.rx, e.ry)
            }, rect: function(t) {
                var e = Y(t);
                return _(e.x || 0, e.y || 0, e.width, e.height, e.rx, e.ry)
            }, image: function(t) {
                var e = Y(t);
                return _(e.x || 0, e.y || 0, e.width, e.height)
            }, line: function(t) {
                return"M" + [t.attr("x1") || 0, t.attr("y1") || 0, t.attr("x2"), t.attr("y2")]
            }, polyline: function(t) {
                return"M" + t.attr("points")
            }, polygon: function(t) {
                return"M" + t.attr("points") + "z"
            }, deflt: function(t) {
                var e = t.node.getBBox();
                return _(e.x, e.y, e.width, e.height)
            }};
        t.path = n, t.path.getTotalLength = V, t.path.getPointAtLength = U, t.path.getSubpath = function(t, e, n) {
            if (this.getTotalLength(t) - n < 1e-6)
                return X(t, e).end;
            var i = X(t, n, 1);
            return e ? X(i, e).end : i
        }, H.getTotalLength = function() {
            return this.node.getTotalLength ? this.node.getTotalLength() : void 0
        }, H.getPointAtLength = function(t) {
            return U(this.attr("d"), t)
        }, H.getSubpath = function(e, n) {
            return t.path.getSubpath(this.attr("d"), e, n)
        }, t._.box = i, t.path.findDotsAtSegment = l, t.path.bezierBBox = u, t.path.isPointInsideBBox = c, t.path.isBBoxIntersect = h, t.path.intersection = g, t.path.intersectionNumber = y, t.path.isPointInside = w, t.path.getBBox = x, t.path.get = G, t.path.toRelative = k, t.path.toAbsolute = C, t.path.toCubic = A, t.path.map = N, t.path.toString = s, t.path.clone = o
    }), i.plugin(function(t) {
        var i = Math.max, s = Math.min, o = function(t) {
            if (this.items = [], this.bindings = {}, this.length = 0, this.type = "set", t)
                for (var e = 0, n = t.length; n > e; e++)
                    t[e] && (this[this.items.length] = this.items[this.items.length] = t[e], this.length++)
        }, r = o.prototype;
        r.push = function() {
            for (var t, e, n = 0, i = arguments.length; i > n; n++)
                t = arguments[n], t && (e = this.items.length, this[e] = this.items[e] = t, this.length++);
            return this
        }, r.pop = function() {
            return this.length && delete this[this.length--], this.items.pop()
        }, r.forEach = function(t, e) {
            for (var n = 0, i = this.items.length; i > n; n++)
                if (t.call(e, this.items[n], n) === !1)
                    return this;
            return this
        }, r.animate = function(i, s, o, r) {
            "function" != typeof o || o.length || (r = o, o = n.linear), i instanceof t._.Animation && (r = i.callback, o = i.easing, s = o.dur, i = i.attr);
            var a = arguments;
            if (t.is(i, "array") && t.is(a[a.length - 1], "array"))
                var l = !0;
            var u, c = function() {
                u ? this.b = u : u = this.b
            }, h = 0, d = r && function() {
                h++ == this.length && r.call(this)
            };
            return this.forEach(function(t, n) {
                e.once("snap.animcreated." + t.id, c), l ? a[n] && t.animate.apply(t, a[n]) : t.animate(i, s, o, d)
            })
        }, r.remove = function() {
            for (; this.length; )
                this.pop().remove();
            return this
        }, r.bind = function(t, e, n) {
            var i = {};
            if ("function" == typeof e)
                this.bindings[t] = e;
            else {
                var s = n || t;
                this.bindings[t] = function(t) {
                    i[s] = t, e.attr(i)
                }
            }
            return this
        }, r.attr = function(t) {
            var e = {};
            for (var n in t)
                this.bindings[n] ? this.bindings[n](t[n]) : e[n] = t[n];
            for (var i = 0, s = this.items.length; s > i; i++)
                this.items[i].attr(e);
            return this
        }, r.clear = function() {
            for (; this.length; )
                this.pop()
        }, r.splice = function(t, e) {
            t = 0 > t ? i(this.length + t, 0) : t, e = i(0, s(this.length - t, e));
            var n, r = [], a = [], l = [];
            for (n = 2; n < arguments.length; n++)
                l.push(arguments[n]);
            for (n = 0; e > n; n++)
                a.push(this[t + n]);
            for (; n < this.length - t; n++)
                r.push(this[t + n]);
            var u = l.length;
            for (n = 0; n < u + r.length; n++)
                this.items[t + n] = this[t + n] = u > n ? l[n] : r[n - u];
            for (n = this.items.length = this.length -= e - u; this[n]; )
                delete this[n++];
            return new o(a)
        }, r.exclude = function(t) {
            for (var e = 0, n = this.length; n > e; e++)
                if (this[e] == t)
                    return this.splice(e, 1), !0;
            return!1
        }, r.insertAfter = function(t) {
            for (var e = this.items.length; e--; )
                this.items[e].insertAfter(t);
            return this
        }, r.getBBox = function() {
            for (var t = [], e = [], n = [], o = [], r = this.items.length; r--; )
                if (!this.items[r].removed) {
                    var a = this.items[r].getBBox();
                    t.push(a.x), e.push(a.y), n.push(a.x + a.width), o.push(a.y + a.height)
                }
            return t = s.apply(0, t), e = s.apply(0, e), n = i.apply(0, n), o = i.apply(0, o), {x: t, y: e, x2: n, y2: o, width: n - t, height: o - e, cx: t + (n - t) / 2, cy: e + (o - e) / 2}
        }, r.clone = function(t) {
            t = new o;
            for (var e = 0, n = this.items.length; n > e; e++)
                t.push(this.items[e].clone());
            return t
        }, r.toString = function() {
            return"Snapâ€˜s set"
        }, r.type = "set", t.set = function() {
            var t = new o;
            return arguments.length && t.push.apply(t, Array.prototype.slice.call(arguments, 0)), t
        }
    }), i.plugin(function(t, n) {
        function i(t) {
            var e = t[0];
            switch (e.toLowerCase()) {
                case"t":
                    return[e, 0, 0];
                case"m":
                    return[e, 1, 0, 0, 1, 0, 0];
                case"r":
                    return 4 == t.length ? [e, 0, t[2], t[3]] : [e, 0];
                case"s":
                    return 5 == t.length ? [e, 1, 1, t[3], t[4]] : 3 == t.length ? [e, 1, 1] : [e, 1]
            }
        }
        function s(e, n, s) {
            n = d(n).replace(/\.{3}|\u2026/g, e), e = t.parseTransformString(e) || [], n = t.parseTransformString(n) || [];
            for (var o, r, a, c, h = Math.max(e.length, n.length), p = [], f = [], m = 0; h > m; m++) {
                if (a = e[m] || i(n[m]), c = n[m] || i(a), a[0] != c[0] || "r" == a[0].toLowerCase() && (a[2] != c[2] || a[3] != c[3]) || "s" == a[0].toLowerCase() && (a[3] != c[3] || a[4] != c[4])) {
                    e = t._.transform2matrix(e, s()), n = t._.transform2matrix(n, s()), p = [["m", e.a, e.b, e.c, e.d, e.e, e.f]], f = [["m", n.a, n.b, n.c, n.d, n.e, n.f]];
                    break
                }
                for (p[m] = [], f[m] = [], o = 0, r = Math.max(a.length, c.length); r > o; o++)
                    o in a && (p[m][o] = a[o]), o in c && (f[m][o] = c[o])
            }
            return{from: u(p), to: u(f), f: l(p)}
        }
        function o(t) {
            return t
        }
        function r(t) {
            return function(e) {
                return+e.toFixed(3) + t
            }
        }
        function a(e) {
            return t.rgb(e[0], e[1], e[2])
        }
        function l(t) {
            var e, n, i, s, o, r, a = 0, l = [];
            for (e = 0, n = t.length; n > e; e++) {
                for (o = "[", r = ['"' + t[e][0] + '"'], i = 1, s = t[e].length; s > i; i++)
                    r[i] = "val[" + a++ + "]";
                o += r + "]", l[e] = o
            }
            return Function("val", "return Snap.path.toString.call([" + l + "])")
        }
        function u(t) {
            for (var e = [], n = 0, i = t.length; i > n; n++)
                for (var s = 1, o = t[n].length; o > s; s++)
                    e.push(t[n][s]);
            return e
        }
        var c = {}, h = /[a-z]+$/i, d = String;
        c.stroke = c.fill = "colour", n.prototype.equal = function(t, n) {
            return e("snap.util.equal", this, t, n).firstDefined()
        }, e.on("snap.util.equal", function(e, n) {
            var i, p, f = d(this.attr(e) || ""), m = this;
            if (f == +f && n == +n)
                return{from: +f, to: +n, f: o};
            if ("colour" == c[e])
                return i = t.color(f), p = t.color(n), {from: [i.r, i.g, i.b, i.opacity], to: [p.r, p.g, p.b, p.opacity], f: a};
            if ("transform" == e || "gradientTransform" == e || "patternTransform" == e)
                return n instanceof t.Matrix && (n = n.toTransformString()), t._.rgTransform.test(n) || (n = t._.svgTransform2string(n)), s(f, n, function() {
                    return m.getBBox(1)
                });
            if ("d" == e || "path" == e)
                return i = t.path.toCubic(f, n), {from: u(i[0]), to: u(i[1]), f: l(i[0])};
            if ("points" == e)
                return i = d(f).split(t._.separator), p = d(n).split(t._.separator), {from: i, to: p, f: function(t) {
                        return t
                    }};
            aUnit = f.match(h);
            var v = d(n).match(h);
            return aUnit && aUnit == v ? {from: parseFloat(f), to: parseFloat(n), f: r(aUnit)} : {from: this.asPX(e), to: this.asPX(e, n), f: o}
        })
    }), i.plugin(function(t, n, i, s) {
        for (var o = n.prototype, r = "hasOwnProperty", a = ("createTouch"in s.doc), l = ["click", "dblclick", "mousedown", "mousemove", "mouseout", "mouseover", "mouseup", "touchstart", "touchmove", "touchend", "touchcancel"], u = {mousedown: "touchstart", mousemove: "touchmove", mouseup: "touchend"}, c = (function(t, e) {
            var n = "y" == t ? "scrollTop" : "scrollLeft", i = e && e.node ? e.node.ownerDocument : s.doc;
            return i[n in i.documentElement ? "documentElement" : "body"][n]
        }), h = function() {
            this.returnValue = !1
        }, d = function() {
            return this.originalEvent.preventDefault()
        }, p = function() {
            this.cancelBubble = !0
        }, f = function() {
            return this.originalEvent.stopPropagation()
        }, m = function() {
            return s.doc.addEventListener ? function(t, e, n, i) {
                var s = a && u[e] ? u[e] : e, o = function(s) {
                    var o = c("y", i), l = c("x", i);
                    if (a && u[r](e))
                        for (var h = 0, p = s.targetTouches && s.targetTouches.length; p > h; h++)
                            if (s.targetTouches[h].target == t || t.contains(s.targetTouches[h].target)) {
                                var m = s;
                                s = s.targetTouches[h], s.originalEvent = m, s.preventDefault = d, s.stopPropagation = f;
                                break
                            }
                    var v = s.clientX + l, g = s.clientY + o;
                    return n.call(i, s, v, g)
                };
                return e !== s && t.addEventListener(e, o, !1), t.addEventListener(s, o, !1), function() {
                    return e !== s && t.removeEventListener(e, o, !1), t.removeEventListener(s, o, !1), !0
                }
            } : s.doc.attachEvent ? function(t, e, n, i) {
                var s = function(t) {
                    t = t || i.node.ownerDocument.window.event;
                    var e = c("y", i), s = c("x", i), o = t.clientX + s, r = t.clientY + e;
                    return t.preventDefault = t.preventDefault || h, t.stopPropagation = t.stopPropagation || p, n.call(i, t, o, r)
                };
                t.attachEvent("on" + e, s);
                var o = function() {
                    return t.detachEvent("on" + e, s), !0
                };
                return o
            } : void 0
        }(), v = [], g = function(t) {
            for (var n, i = t.clientX, s = t.clientY, o = c("y"), r = c("x"), l = v.length; l--; ) {
                if (n = v[l], a) {
                    for (var u, h = t.touches && t.touches.length; h--; )
                        if (u = t.touches[h], u.identifier == n.el._drag.id || n.el.node.contains(u.target)) {
                            i = u.clientX, s = u.clientY, (t.originalEvent ? t.originalEvent : t).preventDefault();
                            break
                        }
                } else
                    t.preventDefault();
                var d = n.el.node;
                d.nextSibling, d.parentNode, d.style.display, i += r, s += o, e("snap.drag.move." + n.el.id, n.move_scope || n.el, i - n.el._drag.x, s - n.el._drag.y, i, s, t)
            }
        }, y = function(n) {
            t.unmousemove(g).unmouseup(y);
            for (var i, s = v.length; s--; )
                i = v[s], i.el._drag = {}, e("snap.drag.end." + i.el.id, i.end_scope || i.start_scope || i.move_scope || i.el, n);
            v = []
        }, b = l.length; b--; )
            !function(e) {
                t[e] = o[e] = function(n, i) {
                    return t.is(n, "function") && (this.events = this.events || [], this.events.push({name: e, f: n, unbind: m(this.node || document, e, n, i || this)})), this
                }, t["un" + e] = o["un" + e] = function(t) {
                    for (var n = this.events || [], i = n.length; i--; )
                        if (n[i].name == e && (n[i].f == t || !t))
                            return n[i].unbind(), n.splice(i, 1), !n.length && delete this.events, this;
                    return this
                }
            }(l[b]);
        o.hover = function(t, e, n, i) {
            return this.mouseover(t, n).mouseout(e, i || n)
        }, o.unhover = function(t, e) {
            return this.unmouseover(t).unmouseout(e)
        };
        var w = [];
        o.drag = function(n, i, s, o, r, a) {
            function l(l, u, c) {
                (l.originalEvent || l).preventDefault(), this._drag.x = u, this._drag.y = c, this._drag.id = l.identifier, !v.length && t.mousemove(g).mouseup(y), v.push({el: this, move_scope: o, start_scope: r, end_scope: a}), i && e.on("snap.drag.start." + this.id, i), n && e.on("snap.drag.move." + this.id, n), s && e.on("snap.drag.end." + this.id, s), e("snap.drag.start." + this.id, r || o || this, u, c, l)
            }
            if (!arguments.length) {
                var u;
                return this.drag(function(t, e) {
                    this.attr({transform: u + (u ? "T" : "t") + [t, e]})
                }, function() {
                    u = this.transform().local
                })
            }
            return this._drag = {}, w.push({el: this, start: l}), this.mousedown(l), this
        }, o.undrag = function() {
            for (var n = w.length; n--; )
                w[n].el == this && (this.unmousedown(w[n].start), w.splice(n, 1), e.unbind("snap.drag.*." + this.id));
            return!w.length && t.unmousemove(g).unmouseup(y), this
        }
    }), i.plugin(function(t, n, i) {
        var s = (n.prototype, i.prototype), o = /^\s*url\((.+)\)/, r = String, a = t._.$;
        t.filter = {}, s.filter = function(e) {
            var i = this;
            "svg" != i.type && (i = i.paper);
            var s = t.parse(r(e)), o = t._.id(), l = (i.node.offsetWidth, i.node.offsetHeight, a("filter"));
            return a(l, {id: o, filterUnits: "userSpaceOnUse"}), l.appendChild(s.node), i.defs.appendChild(l), new n(l)
        }, e.on("snap.util.getattr.filter", function() {
            e.stop();
            var n = a(this.node, "filter");
            if (n) {
                var i = r(n).match(o);
                return i && t.select(i[1])
            }
        }), e.on("snap.util.attr.filter", function(i) {
            if (i instanceof n && "filter" == i.type) {
                e.stop();
                var s = i.node.id;
                s || (a(i.node, {id: i.id}), s = i.id), a(this.node, {filter: t.url(s)})
            }
            i && "none" != i || (e.stop(), this.node.removeAttribute("filter"))
        }), t.filter.blur = function(e, n) {
            null == e && (e = 2);
            var i = null == n ? e : [e, n];
            return t.format('<feGaussianBlur stdDeviation="{def}"/>', {def: i})
        }, t.filter.blur.toString = function() {
            return this()
        }, t.filter.shadow = function(e, n, i, s, o) {
            return"string" == typeof i && (s = i, o = s, i = 4), "string" != typeof s && (o = s, s = "#000"), s = s || "#000", null == i && (i = 4), null == o && (o = 1), null == e && (e = 0, n = 2), null == n && (n = e), s = t.color(s), t.format('<feGaussianBlur in="SourceAlpha" stdDeviation="{blur}"/><feOffset dx="{dx}" dy="{dy}" result="offsetblur"/><feFlood flood-color="{color}"/><feComposite in2="offsetblur" operator="in"/><feComponentTransfer><feFuncA type="linear" slope="{opacity}"/></feComponentTransfer><feMerge><feMergeNode/><feMergeNode in="SourceGraphic"/></feMerge>', {color: s, dx: e, dy: n, blur: i, opacity: o})
        }, t.filter.shadow.toString = function() {
            return this()
        }, t.filter.grayscale = function(e) {
            return null == e && (e = 1), t.format('<feColorMatrix type="matrix" values="{a} {b} {c} 0 0 {d} {e} {f} 0 0 {g} {b} {h} 0 0 0 0 0 1 0"/>', {a: .2126 + .7874 * (1 - e), b: .7152 - .7152 * (1 - e), c: .0722 - .0722 * (1 - e), d: .2126 - .2126 * (1 - e), e: .7152 + .2848 * (1 - e), f: .0722 - .0722 * (1 - e), g: .2126 - .2126 * (1 - e), h: .0722 + .9278 * (1 - e)})
        }, t.filter.grayscale.toString = function() {
            return this()
        }, t.filter.sepia = function(e) {
            return null == e && (e = 1), t.format('<feColorMatrix type="matrix" values="{a} {b} {c} 0 0 {d} {e} {f} 0 0 {g} {h} {i} 0 0 0 0 0 1 0"/>', {a: .393 + .607 * (1 - e), b: .769 - .769 * (1 - e), c: .189 - .189 * (1 - e), d: .349 - .349 * (1 - e), e: .686 + .314 * (1 - e), f: .168 - .168 * (1 - e), g: .272 - .272 * (1 - e), h: .534 - .534 * (1 - e), i: .131 + .869 * (1 - e)})
        }, t.filter.sepia.toString = function() {
            return this()
        }, t.filter.saturate = function(e) {
            return null == e && (e = 1), t.format('<feColorMatrix type="saturate" values="{amount}"/>', {amount: 1 - e})
        }, t.filter.saturate.toString = function() {
            return this()
        }, t.filter.hueRotate = function(e) {
            return e = e || 0, t.format('<feColorMatrix type="hueRotate" values="{angle}"/>', {angle: e})
        }, t.filter.hueRotate.toString = function() {
            return this()
        }, t.filter.invert = function(e) {
            return null == e && (e = 1), t.format('<feComponentTransfer><feFuncR type="table" tableValues="{amount} {amount2}"/><feFuncG type="table" tableValues="{amount} {amount2}"/><feFuncB type="table" tableValues="{amount} {amount2}"/></feComponentTransfer>', {amount: e, amount2: 1 - e})
        }, t.filter.invert.toString = function() {
            return this()
        }, t.filter.brightness = function(e) {
            return null == e && (e = 1), t.format('<feComponentTransfer><feFuncR type="linear" slope="{amount}"/><feFuncG type="linear" slope="{amount}"/><feFuncB type="linear" slope="{amount}"/></feComponentTransfer>', {amount: e})
        }, t.filter.brightness.toString = function() {
            return this()
        }, t.filter.contrast = function(e) {
            return null == e && (e = 1), t.format('<feComponentTransfer><feFuncR type="linear" slope="{amount}" intercept="{amount2}"/><feFuncG type="linear" slope="{amount}" intercept="{amount2}"/><feFuncB type="linear" slope="{amount}" intercept="{amount2}"/></feComponentTransfer>', {amount: e, amount2: .5 - e / 2})
        }, t.filter.contrast.toString = function() {
            return this()
        }
    }), i
}), /*! Video.js v4.6.1 Copyright 2014 Brightcove, Inc. https://github.com/videojs/video.js/blob/master/LICENSE */
        function() {
            function m() {
                return function() {
                }
            }
            function q(t) {
                return function() {
                    return this[t]
                }
            }
            function r(t) {
                return function() {
                    return t
                }
            }
            function u(t, e, n) {
                if ("string" == typeof t) {
                    if (0 === t.indexOf("#") && (t = t.slice(1)), u.Aa[t])
                        return u.Aa[t];
                    t = u.w(t)
                }
                if (!t || !t.nodeName)
                    throw new TypeError("The element or ID supplied is not valid. (videojs)");
                return t.player || new u.Player(t, e, n)
            }
            function D() {
            }
            function F(t, e) {
                var n = Array.prototype.slice.call(e);
                t ? n.unshift(t.toUpperCase() + ":") : t = "log", u.log.history.push(n), n.unshift("VIDEOJS:"), E[t].apply ? E[t].apply(E, n) : E[t](n.join(" "))
            }
            function G(t) {
                t.r("vjs-lock-showing")
            }
            function H(t, e, n, i) {
                return n !== b ? (t.b.style[e] = -1 !== ("" + n).indexOf("%") || -1 !== ("" + n).indexOf("px") ? n : "auto" === n ? "" : n + "px", i || t.k("resize"), t) : t.b ? (n = t.b.style[e], i = n.indexOf("px"), -1 !== i ? parseInt(n.slice(0, i), 10) : parseInt(t.b["offset" + u.$(e)], 10)) : 0
            }
            function I(t) {
                var e, n, i, s, o, r, a, u;
                e = 0, n = j, t.d("touchstart", function(t) {
                    1 === t.touches.length && (n = t.touches[0], e = (new Date).getTime(), s = f)
                }), t.d("touchmove", function(t) {
                    1 < t.touches.length ? s = l : n && (r = t.touches[0].pageX - n.pageX, a = t.touches[0].pageY - n.pageY, u = Math.sqrt(r * r + a * a), u > 22 && (s = l))
                }), o = function() {
                    s = l
                }, t.d("touchleave", o), t.d("touchcancel", o), t.d("touchend", function(t) {
                    n = j, s === f && (i = (new Date).getTime() - e, 250 > i && (t.preventDefault(), this.k("tap")))
                })
            }
            function J(t, e) {
                var n, i, s, o;
                return n = t.b, i = u.od(n), o = s = n.offsetWidth, n = t.handle, t.j.Wd ? (o = i.top, i = e.changedTouches ? e.changedTouches[0].pageY : e.pageY, n && (n = n.w().offsetHeight, o += n / 2, s -= n), Math.max(0, Math.min(1, (o - i + s) / s))) : (s = i.left, i = e.changedTouches ? e.changedTouches[0].pageX : e.pageX, n && (n = n.w().offsetWidth, s += n / 2, o -= n), Math.max(0, Math.min(1, (i - s) / o)))
            }
            function ca(t, e) {
                t.V(e), e.d("click", u.bind(t, function() {
                    G(this)
                }))
            }
            function L(t) {
                t.ra = f, t.za.o("vjs-lock-showing"), t.b.setAttribute("aria-pressed", f), t.O && 0 < t.O.length && t.O[0].w().focus()
            }
            function K(t) {
                t.ra = l, G(t.za), t.b.setAttribute("aria-pressed", l)
            }
            function da(t) {
                var e = {sources: [], tracks: []};
                if (u.l.B(e, u.Cb(t)), t.hasChildNodes()) {
                    var n, i, s, o;
                    for (t = t.childNodes, s = 0, o = t.length; o > s; s++)
                        n = t[s], i = n.nodeName.toLowerCase(), "source" === i ? e.sources.push(u.Cb(n)) : "track" === i && e.tracks.push(u.Cb(n))
                }
                return e
            }
            function R(t, e, n) {
                t.g && (t.ca = l, t.g.dispose(), t.Ib && (t.Ib = l, clearInterval(t.Ya)), t.Jb && S(t), t.g = l), "Html5" !== e && t.P && (u.f.nc(t.P), t.P = j), t.Ca = e, t.ca = l;
                var i = u.l.B({source: n, parentEl: t.b}, t.j[e.toLowerCase()]);
                n && (n.src == t.z.src && 0 < t.z.currentTime && (i.startTime = t.z.currentTime), t.z.src = n.src), t.g = new window.videojs[e](t, i), t.g.I(function() {
                    if (this.c.Ea(), !this.n.progressEvents) {
                        var t = this.c;
                        t.Ib = f, t.Ya = setInterval(u.bind(t, function() {
                            this.z.tb < this.buffered().end(0) ? this.k("progress") : 1 == this.bufferedPercent() && (clearInterval(this.Ya), this.k("progress"))
                        }), 500), t.g && t.g.W("progress", function() {
                            this.n.progressEvents = f;
                            var t = this.c;
                            t.Ib = l, clearInterval(t.Ya)
                        })
                    }
                    this.n.timeupdateEvents || (t = this.c, t.Jb = f, t.d("play", t.Kc), t.d("pause", t.Ba), t.g && t.g.W("timeupdate", function() {
                        this.n.timeupdateEvents = f, S(this.c)
                    }))
                })
            }
            function S(t) {
                t.Jb = l, t.Ba(), t.p("play", t.Kc), t.p("pause", t.Ba)
            }
            function T(t) {
                u.k(this.b, {type: "firstplay", target: this.b}) || (t.preventDefault(), t.stopPropagation(), t.stopImmediatePropagation())
            }
            function V(t, e, n) {
                if (t.g && !t.g.ca)
                    t.g.I(function() {
                        this[e](n)
                    });
                else
                    try {
                        t.g[e](n)
                    } catch (i) {
                        throw u.log(i), i
                    }
            }
            function U(t, e) {
                if (t.g && t.g.ca)
                    try {
                        return t.g[e]()
                    } catch (n) {
                        throw t.g[e] === b ? u.log("Video.js: " + e + " method not defined for " + t.Ca + " playback technology.", n) : "TypeError" == n.name ? (u.log("Video.js: " + e + " unavailable on " + t.Ca + " playback technology element.", n), t.g.ca = l) : u.log(n), n
                    }
            }
            function ea(t) {
                t.td = l, u.p(document, "keydown", t.qc), document.documentElement.style.overflow = t.kd, u.r(document.body, "vjs-full-window"), t.k("exitFullWindow")
            }
            function fa(t) {
                return t.m().g && t.m().g.n.playbackRate && t.m().options().playbackRates && 0 < t.m().options().playbackRates.length
            }
            function ha() {
                var t = u.media.bb[i];
                return function() {
                    throw Error('The "' + t + "\" method is not available on the playback technology's API")
                }
            }
            function ma() {
                var t = X[Y], e = t.charAt(0).toUpperCase() + t.slice(1);
                ka["set" + e] = function(e) {
                    return this.b.vjs_setProperty(t, e)
                }
            }
            function na(t) {
                ka[t] = function() {
                    return this.b.vjs_getProperty(t)
                }
            }
            function oa(t, e, n, i, s) {
                var o = t.Da = t.Da || [];
                s = s || {}, s.kind = e, s.label = n, s.language = i, e = u.$(e || "subtitles");
                var r = new window.videojs[e + "Track"](t, s);
                o.push(r), r.Qa() && t.I(function() {
                    setTimeout(function() {
                        r.show()
                    }, 0)
                })
            }
            function pa(t, e, n) {
                for (var i, s, o = t.Da, r = 0, a = o.length; a > r; r++)
                    i = o[r], i.id() === e ? (i.show(), s = i) : n && i.K() == n && 0 < i.mode() && i.disable();
                (e = s ? s.K() : n ? n : l) && t.k(e + "trackchange")
            }
            function qa(t) {
                0 === t.la && t.load(), 0 === t.ka && (t.c.d("timeupdate", u.bind(t, t.update, t.T)), t.c.d("ended", u.bind(t, t.reset, t.T)), ("captions" === t.H || "subtitles" === t.H) && t.c.ja("textTrackDisplay").V(t))
            }
            function ra(t) {
                var e = t.split(":");
                t = 0;
                var n, i, s;
                return 3 == e.length ? (n = e[0], i = e[1], e = e[2]) : (n = 0, i = e[0], e = e[1]), e = e.split(/\s+/), e = e.splice(0, 1)[0], e = e.split(/\.|,/), s = parseFloat(e[1]), e = e[0], t += 3600 * parseFloat(n), t += 60 * parseFloat(i), t += parseFloat(e), s && (t += s / 1e3), t
            }
            function $(t, e) {
                var n = t.split("."), i = sa;
                !(n[0]in i) && i.execScript && i.execScript("var " + n[0]);
                for (var s; n.length && (s = n.shift()); )
                    n.length || e === b ? i = i[s] ? i[s] : i[s] = {} : i[s] = e
            }
            var b = void 0, f = !0, j = null, l = !1, t;
            document.createElement("video"), document.createElement("audio"), document.createElement("track");
            var videojs = u;
            window.ke = window.le = u, u.Vb = "4.6", u.Pc = "https:" == document.location.protocol ? "https://" : "http://", u.options = {techOrder: ["html5", "flash"], html5: {}, flash: {}, width: 300, height: 150, defaultVolume: 0, playbackRates: [], children: {mediaLoader: {}, posterImage: {}, textTrackDisplay: {}, loadingSpinner: {}, bigPlayButton: {}, controlBar: {}, errorDisplay: {}}, notSupportedMessage: "No compatible source was found for this video."}, "GENERATED_CDN_VSN" !== u.Vb && (videojs.options.flash.swf = u.Pc + "vjs.zencdn.net/" + u.Vb + "/video-js.swf"), u.Aa = {}, "function" == typeof define && define.amd ? define([], function() {
                return videojs
            }) : "object" == typeof exports && "object" == typeof module && (module.exports = videojs), u.pa = u.CoreObject = m(), u.pa.extend = function(t) {
                var e, n;
                t = t || {}, e = t.init || t.h || this.prototype.init || this.prototype.h || m(), n = function() {
                    e.apply(this, arguments)
                }, n.prototype = u.l.create(this.prototype), n.prototype.constructor = n, n.extend = u.pa.extend, n.create = u.pa.create;
                for (var i in t)
                    t.hasOwnProperty(i) && (n.prototype[i] = t[i]);
                return n
            }, u.pa.create = function() {
                var t = u.l.create(this.prototype);
                return this.apply(t, arguments), t
            }, u.d = function(t, e, n) {
                var i = u.getData(t);
                i.D || (i.D = {}), i.D[e] || (i.D[e] = []), n.v || (n.v = u.v++), i.D[e].push(n), i.X || (i.disabled = l, i.X = function(e) {
                    if (!i.disabled) {
                        e = u.pc(e);
                        var n = i.D[e.type];
                        if (n)
                            for (var n = n.slice(0), s = 0, o = n.length; o > s && !e.wc(); s++)
                                n[s].call(t, e)
                    }
                }), 1 == i.D[e].length && (document.addEventListener ? t.addEventListener(e, i.X, l) : document.attachEvent && t.attachEvent("on" + e, i.X))
            }, u.p = function(t, e, n) {
                if (u.tc(t)) {
                    var i = u.getData(t);
                    if (i.D)
                        if (e) {
                            var s = i.D[e];
                            if (s) {
                                if (n) {
                                    if (n.v)
                                        for (i = 0; i < s.length; i++)
                                            s[i].v === n.v && s.splice(i--, 1)
                                } else
                                    i.D[e] = [];
                                u.kc(t, e)
                            }
                        } else
                            for (s in i.D)
                                e = s, i.D[e] = [], u.kc(t, e)
                }
            }, u.kc = function(t, e) {
                var n = u.getData(t);
                0 === n.D[e].length && (delete n.D[e], document.removeEventListener ? t.removeEventListener(e, n.X, l) : document.detachEvent && t.detachEvent("on" + e, n.X)), u.Fb(n.D) && (delete n.D, delete n.X, delete n.disabled), u.Fb(n) && u.Dc(t)
            }, u.pc = function(t) {
                function e() {
                    return f
                }
                function n() {
                    return l
                }
                if (!t || !t.Gb) {
                    var i = t || window.event;
                    t = {};
                    for (var s in i)
                        "layerX" !== s && "layerY" !== s && "keyboardEvent.keyLocation" !== s && ("returnValue" == s && i.preventDefault || (t[s] = i[s]));
                    if (t.target || (t.target = t.srcElement || document), t.relatedTarget = t.fromElement === t.target ? t.toElement : t.fromElement, t.preventDefault = function() {
                        i.preventDefault && i.preventDefault(), t.returnValue = l, t.sd = e, t.defaultPrevented = f
                    }, t.sd = n, t.defaultPrevented = l, t.stopPropagation = function() {
                        i.stopPropagation && i.stopPropagation(), t.cancelBubble = f, t.Gb = e
                    }, t.Gb = n, t.stopImmediatePropagation = function() {
                        i.stopImmediatePropagation && i.stopImmediatePropagation(), t.wc = e, t.stopPropagation()
                    }, t.wc = n, t.clientX != j) {
                        s = document.documentElement;
                        var o = document.body;
                        t.pageX = t.clientX + (s && s.scrollLeft || o && o.scrollLeft || 0) - (s && s.clientLeft || o && o.clientLeft || 0), t.pageY = t.clientY + (s && s.scrollTop || o && o.scrollTop || 0) - (s && s.clientTop || o && o.clientTop || 0)
                    }
                    t.which = t.charCode || t.keyCode, t.button != j && (t.button = 1 & t.button ? 0 : 4 & t.button ? 1 : 2 & t.button ? 2 : 0)
                }
                return t
            }, u.k = function(t, e) {
                var n = u.tc(t) ? u.getData(t) : {}, i = t.parentNode || t.ownerDocument;
                return"string" == typeof e && (e = {type: e, target: t}), e = u.pc(e), n.X && n.X.call(t, e), i && !e.Gb() && e.bubbles !== l ? u.k(i, e) : i || e.defaultPrevented || (n = u.getData(e.target), !e.target[e.type]) || (n.disabled = f, "function" == typeof e.target[e.type] && e.target[e.type](), n.disabled = l), !e.defaultPrevented
            }, u.W = function(t, e, n) {
                function i() {
                    u.p(t, e, i), n.apply(this, arguments)
                }
                i.v = n.v = n.v || u.v++, u.d(t, e, i)
            };
            var v = Object.prototype.hasOwnProperty;
            u.e = function(t, e) {
                var n, i;
                n = document.createElement(t || "div");
                for (i in e)
                    v.call(e, i) && (-1 !== i.indexOf("aria-") || "role" == i ? n.setAttribute(i, e[i]) : n[i] = e[i]);
                return n
            }, u.$ = function(t) {
                return t.charAt(0).toUpperCase() + t.slice(1)
            }, u.l = {}, u.l.create = Object.create || function(t) {
                function e() {
                }
                return e.prototype = t, new e
            }, u.l.wa = function(t, e, n) {
                for (var i in t)
                    v.call(t, i) && e.call(n || this, i, t[i])
            }, u.l.B = function(t, e) {
                if (!e)
                    return t;
                for (var n in e)
                    v.call(e, n) && (t[n] = e[n]);
                return t
            }, u.l.fd = function(t, e) {
                var n, i, s;
                t = u.l.copy(t);
                for (n in e)
                    v.call(e, n) && (i = t[n], s = e[n], t[n] = u.l.Sa(i) && u.l.Sa(s) ? u.l.fd(i, s) : e[n]);
                return t
            }, u.l.copy = function(t) {
                return u.l.B({}, t)
            }, u.l.Sa = function(t) {
                return!!t && "object" == typeof t && "[object Object]" === t.toString() && t.constructor === Object
            }, u.bind = function(t, e, n) {
                function i() {
                    return e.apply(t, arguments)
                }
                return e.v || (e.v = u.v++), i.v = n ? n + "_" + e.v : e.v, i
            }, u.ta = {}, u.v = 1, u.expando = "vdata" + (new Date).getTime(), u.getData = function(t) {
                var e = t[u.expando];
                return e || (e = t[u.expando] = u.v++, u.ta[e] = {}), u.ta[e]
            }, u.tc = function(t) {
                return t = t[u.expando], !(!t || u.Fb(u.ta[t]))
            }, u.Dc = function(t) {
                var e = t[u.expando];
                if (e) {
                    delete u.ta[e];
                    try {
                        delete t[u.expando]
                    } catch (n) {
                        t.removeAttribute ? t.removeAttribute(u.expando) : t[u.expando] = j
                    }
                }
            }, u.Fb = function(t) {
                for (var e in t)
                    if (t[e] !== j)
                        return l;
                return f
            }, u.o = function(t, e) {
                -1 == (" " + t.className + " ").indexOf(" " + e + " ") && (t.className = "" === t.className ? e : t.className + " " + e)
            }, u.r = function(t, e) {
                var n, i;
                if (-1 != t.className.indexOf(e)) {
                    for (n = t.className.split(" "), i = n.length - 1; i >= 0; i--)
                        n[i] === e && n.splice(i, 1);
                    t.className = n.join(" ")
                }
            }, u.A = u.e("video"), u.M = navigator.userAgent, u.Uc = /iPhone/i.test(u.M), u.Tc = /iPad/i.test(u.M), u.Vc = /iPod/i.test(u.M), u.Sc = u.Uc || u.Tc || u.Vc;
            var aa = u, w, x = u.M.match(/OS (\d+)_/i);
            w = x && x[1] ? x[1] : b, aa.$d = w, u.Rc = /Android/i.test(u.M);
            var ba = u, y, z = u.M.match(/Android (\d+)(?:\.(\d+))?(?:\.(\d+))*/i), A, B;
            z ? (A = z[1] && parseFloat(z[1]), B = z[2] && parseFloat(z[2]), y = A && B ? parseFloat(z[1] + "." + z[2]) : A ? A : j) : y = j, ba.Ub = y, u.Wc = u.Rc && /webkit/i.test(u.M) && 2.3 > u.Ub, u.Yb = /Firefox/i.test(u.M), u.ae = /Chrome/i.test(u.M), u.fc = !!("ontouchstart"in window || window.Qc && document instanceof window.Qc), u.Cb = function(t) {
                var e, n, i, s;
                if (e = {}, t && t.attributes && 0 < t.attributes.length) {
                    n = t.attributes;
                    for (var o = n.length - 1; o >= 0; o--)
                        i = n[o].name, s = n[o].value, ("boolean" == typeof t[i] || -1 !== ",autoplay,controls,loop,muted,default,".indexOf("," + i + ",")) && (s = s !== j ? f : l), e[i] = s
                }
                return e
            }, u.de = function(t, e) {
                var n = "";
                return document.defaultView && document.defaultView.getComputedStyle ? n = document.defaultView.getComputedStyle(t, "").getPropertyValue(e) : t.currentStyle && (n = t["client" + e.substr(0, 1).toUpperCase() + e.substr(1)] + "px"), n
            }, u.Eb = function(t, e) {
                e.firstChild ? e.insertBefore(t, e.firstChild) : e.appendChild(t)
            }, u.Na = {}, u.w = function(t) {
                return 0 === t.indexOf("#") && (t = t.slice(1)), document.getElementById(t)
            }, u.ya = function(t, e) {
                e = e || t;
                var n = Math.floor(t % 60), i = Math.floor(t / 60 % 60), s = Math.floor(t / 3600), o = Math.floor(e / 60 % 60), r = Math.floor(e / 3600);
                return(isNaN(t) || 1 / 0 === t) && (s = i = n = "-"), s = s > 0 || r > 0 ? s + ":" : "", s + (((s || o >= 10) && 10 > i ? "0" + i : i) + ":") + (10 > n ? "0" + n : n)
            }, u.bd = function() {
                document.body.focus(), document.onselectstart = r(l)
            }, u.Ud = function() {
                document.onselectstart = r(f)
            }, u.trim = function(t) {
                return(t + "").replace(/^\s+|\s+$/g, "")
            }, u.round = function(t, e) {
                return e || (e = 0), Math.round(t * Math.pow(10, e)) / Math.pow(10, e)
            }, u.zb = function(t, e) {
                return{length: 1, start: function() {
                        return t
                    }, end: function() {
                        return e
                    }}
            }, u.get = function(t, e, n, i) {
                var s, o, r, a;
                n = n || m(), "undefined" == typeof XMLHttpRequest && (window.XMLHttpRequest = function() {
                    try {
                        return new window.ActiveXObject("Msxml2.XMLHTTP.6.0")
                    } catch (t) {
                    }
                    try {
                        return new window.ActiveXObject("Msxml2.XMLHTTP.3.0")
                    } catch (e) {
                    }
                    try {
                        return new window.ActiveXObject("Msxml2.XMLHTTP")
                    } catch (n) {
                    }
                    throw Error("This browser does not support XMLHttpRequest.")
                }), o = new XMLHttpRequest, r = u.Gd(t), a = window.location, r.protocol + r.host === a.protocol + a.host || !window.XDomainRequest || "withCredentials"in o ? (s = "file:" == r.protocol || "file:" == a.protocol, o.onreadystatechange = function() {
                    4 === o.readyState && (200 === o.status || s && 0 === o.status ? e(o.responseText) : n(o.responseText))
                }) : (o = new window.XDomainRequest, o.onload = function() {
                    e(o.responseText)
                }, o.onerror = n, o.onprogress = m(), o.ontimeout = n);
                try {
                    o.open("GET", t, f), i && (o.withCredentials = f)
                } catch (l) {
                    return n(l), void 0
                }
                try {
                    o.send()
                } catch (c) {
                    n(c)
                }
            }, u.Ld = function(t) {
                try {
                    var e = window.localStorage || l;
                    e && (e.volume = t)
                } catch (n) {
                    22 == n.code || 1014 == n.code ? u.log("LocalStorage Full (VideoJS)", n) : 18 == n.code ? u.log("LocalStorage not allowed (VideoJS)", n) : u.log("LocalStorage Error (VideoJS)", n)
                }
            }, u.rc = function(t) {
                return t.match(/^https?:\/\//) || (t = u.e("div", {innerHTML: '<a href="' + t + '">x</a>'}).firstChild.href), t
            }, u.Gd = function(t) {
                var e, n, i, s;
                s = "protocol hostname port pathname search hash host".split(" "), n = u.e("a", {href: t}), (i = "" === n.host && "file:" !== n.protocol) && (e = u.e("div"), e.innerHTML = '<a href="' + t + '"></a>', n = e.firstChild, e.setAttribute("style", "display:none; position:absolute;"), document.body.appendChild(e)), t = {};
                for (var o = 0; o < s.length; o++)
                    t[s[o]] = n[s[o]];
                return i && document.body.removeChild(e), t
            };
            var E = window.console || {log: D, warn: D, error: D};
            u.log = function() {
                F(j, arguments)
            }, u.log.history = [], u.log.error = function() {
                F("error", arguments)
            }, u.log.warn = function() {
                F("warn", arguments)
            }, u.od = function(t) {
                var e, n;
                return t.getBoundingClientRect && t.parentNode && (e = t.getBoundingClientRect()), e ? (t = document.documentElement, n = document.body, {left: u.round(e.left + (window.pageXOffset || n.scrollLeft) - (t.clientLeft || n.clientLeft || 0)), top: u.round(e.top + (window.pageYOffset || n.scrollTop) - (t.clientTop || n.clientTop || 0))}) : {left: 0, top: 0}
            }, u.oa = {}, u.oa.Kb = function(t, e) {
                var n, i, s;
                t = u.l.copy(t);
                for (n in e)
                    e.hasOwnProperty(n) && (i = t[n], s = e[n], t[n] = u.l.Sa(i) && u.l.Sa(s) ? u.oa.Kb(i, s) : e[n]);
                return t
            }, u.a = u.pa.extend({h: function(t, e, n) {
                    if (this.c = t, this.j = u.l.copy(this.j), e = this.options(e), this.T = e.id || (e.el && e.el.id ? e.el.id : t.id() + "_component_" + u.v++), this.xd = e.name || j, this.b = e.el || this.e(), this.N = [], this.Oa = {}, this.Pa = {}, this.uc(), this.I(n), e.Ec !== l) {
                        var i, s;
                        i = u.bind(this.m(), this.m().reportUserActivity), this.d("touchstart", function() {
                            i(), clearInterval(s), s = setInterval(i, 250)
                        }), t = function() {
                            i(), clearInterval(s)
                        }, this.d("touchmove", i), this.d("touchend", t), this.d("touchcancel", t)
                    }
                }}), t = u.a.prototype, t.dispose = function() {
                if (this.k({type: "dispose", bubbles: l}), this.N)
                    for (var t = this.N.length - 1; t >= 0; t--)
                        this.N[t].dispose && this.N[t].dispose();
                this.Pa = this.Oa = this.N = j, this.p(), this.b.parentNode && this.b.parentNode.removeChild(this.b), u.Dc(this.b), this.b = j
            }, t.c = f, t.m = q("c"), t.options = function(t) {
                return t === b ? this.j : this.j = u.oa.Kb(this.j, t)
            }, t.e = function(t, e) {
                return u.e(t, e)
            }, t.w = q("b"), t.ia = function() {
                return this.u || this.b
            }, t.id = q("T"), t.name = q("xd"), t.children = q("N"), t.rd = function(t) {
                return this.Oa[t]
            }, t.ja = function(t) {
                return this.Pa[t]
            }, t.V = function(t, e) {
                var n, i;
                return"string" == typeof t ? (i = t, e = e || {}, n = e.componentClass || u.$(i), e.name = i, n = new window.videojs[n](this.c || this, e)) : n = t, this.N.push(n), "function" == typeof n.id && (this.Oa[n.id()] = n), (i = i || n.name && n.name()) && (this.Pa[i] = n), "function" == typeof n.el && n.el() && this.ia().appendChild(n.el()), n
            }, t.removeChild = function(t) {
                if ("string" == typeof t && (t = this.ja(t)), t && this.N) {
                    for (var e = l, n = this.N.length - 1; n >= 0; n--)
                        if (this.N[n] === t) {
                            e = f, this.N.splice(n, 1);
                            break
                        }
                    e && (this.Oa[t.id] = j, this.Pa[t.name] = j, (e = t.w()) && e.parentNode === this.ia() && this.ia().removeChild(t.w()))
                }
            }, t.uc = function() {
                var t, e, n, i;
                if (t = this, e = this.options().children)
                    if (e instanceof Array)
                        for (var s = 0; s < e.length; s++)
                            n = e[s], "string" == typeof n ? (i = n, n = {}) : i = n.name, t[i] = t.V(i, n);
                    else
                        u.l.wa(e, function(e, n) {
                            n !== l && (t[e] = t.V(e, n))
                        })
            }, t.S = r(""), t.d = function(t, e) {
                return u.d(this.b, t, u.bind(this, e)), this
            }, t.p = function(t, e) {
                return u.p(this.b, t, e), this
            }, t.W = function(t, e) {
                return u.W(this.b, t, u.bind(this, e)), this
            }, t.k = function(t, e) {
                return u.k(this.b, t, e), this
            }, t.I = function(t) {
                return t && (this.ca ? t.call(this) : (this.Za === b && (this.Za = []), this.Za.push(t))), this
            }, t.Ea = function() {
                this.ca = f;
                var t = this.Za;
                if (t && 0 < t.length) {
                    for (var e = 0, n = t.length; n > e; e++)
                        t[e].call(this);
                    this.Za = [], this.k("ready")
                }
            }, t.o = function(t) {
                return u.o(this.b, t), this
            }, t.r = function(t) {
                return u.r(this.b, t), this
            }, t.show = function() {
                return this.b.style.display = "block", this
            }, t.G = function() {
                return this.b.style.display = "none", this
            }, t.disable = function() {
                this.G(), this.show = m()
            }, t.width = function(t, e) {
                return H(this, "width", t, e)
            }, t.height = function(t, e) {
                return H(this, "height", t, e)
            }, t.jd = function(t, e) {
                return this.width(t, f).height(e)
            }, u.s = u.a.extend({h: function(t, e) {
                    u.a.call(this, t, e), I(this), this.d("tap", this.q), this.d("click", this.q), this.d("focus", this.Va), this.d("blur", this.Ua)
                }}), t = u.s.prototype, t.e = function(t, e) {
                var n;
                return e = u.l.B({className: this.S(), role: "button", "aria-live": "polite", tabIndex: 0}, e), n = u.a.prototype.e.call(this, t, e), e.innerHTML || (this.u = u.e("div", {className: "vjs-control-content"}), this.xb = u.e("span", {className: "vjs-control-text", innerHTML: this.sa || "Need Text"}), this.u.appendChild(this.xb), n.appendChild(this.u)), n
            }, t.S = function() {
                return"vjs-control " + u.a.prototype.S.call(this)
            }, t.q = m(), t.Va = function() {
                u.d(document, "keyup", u.bind(this, this.da))
            }, t.da = function(t) {
                (32 == t.which || 13 == t.which) && (t.preventDefault(), this.q())
            }, t.Ua = function() {
                u.p(document, "keyup", u.bind(this, this.da))
            }, u.Q = u.a.extend({h: function(t, e) {
                    u.a.call(this, t, e), this.ad = this.ja(this.j.barName), this.handle = this.ja(this.j.handleName), this.d("mousedown", this.Wa), this.d("touchstart", this.Wa), this.d("focus", this.Va), this.d("blur", this.Ua), this.d("click", this.q), this.c.d("controlsvisible", u.bind(this, this.update)), t.d(this.Ac, u.bind(this, this.update)), this.R = {}
                }}), t = u.Q.prototype, t.e = function(t, e) {
                return e = e || {}, e.className += " vjs-slider", e = u.l.B({role: "slider", "aria-valuenow": 0, "aria-valuemin": 0, "aria-valuemax": 100, tabIndex: 0}, e), u.a.prototype.e.call(this, t, e)
            }, t.Wa = function(t) {
                t.preventDefault(), u.bd(), this.R.move = u.bind(this, this.Lb), this.R.end = u.bind(this, this.Mb), u.d(document, "mousemove", this.R.move), u.d(document, "mouseup", this.R.end), u.d(document, "touchmove", this.R.move), u.d(document, "touchend", this.R.end), this.Lb(t)
            }, t.Mb = function() {
                u.Ud(), u.p(document, "mousemove", this.R.move, l), u.p(document, "mouseup", this.R.end, l), u.p(document, "touchmove", this.R.move, l), u.p(document, "touchend", this.R.end, l), this.update()
            }, t.update = function() {
                if (this.b) {
                    var t, e = this.Db(), n = this.handle, i = this.ad;
                    if (isNaN(e) && (e = 0), t = e, n) {
                        t = this.b.offsetWidth;
                        var s = n.w().offsetWidth;
                        t = s ? s / t : 0, e *= 1 - t, t = e + t / 2, n.w().style.left = u.round(100 * e, 2) + "%"
                    }
                    i.w().style.width = u.round(100 * t, 2) + "%"
                }
            }, t.Va = function() {
                u.d(document, "keyup", u.bind(this, this.da))
            }, t.da = function(t) {
                37 == t.which ? (t.preventDefault(), this.Gc()) : 39 == t.which && (t.preventDefault(), this.Hc())
            }, t.Ua = function() {
                u.p(document, "keyup", u.bind(this, this.da))
            }, t.q = function(t) {
                t.stopImmediatePropagation(), t.preventDefault()
            }, u.Y = u.a.extend(), u.Y.prototype.defaultValue = 0, u.Y.prototype.e = function(t, e) {
                return e = e || {}, e.className += " vjs-slider-handle", e = u.l.B({innerHTML: '<span class="vjs-control-text">' + this.defaultValue + "</span>"}, e), u.a.prototype.e.call(this, "div", e)
            }, u.ga = u.a.extend(), u.ga.prototype.e = function() {
                var t = this.options().lc || "ul";
                return this.u = u.e(t, {className: "vjs-menu-content"}), t = u.a.prototype.e.call(this, "div", {append: this.u, className: "vjs-menu"}), t.appendChild(this.u), u.d(t, "click", function(t) {
                    t.preventDefault(), t.stopImmediatePropagation()
                }), t
            }, u.J = u.s.extend({h: function(t, e) {
                    u.s.call(this, t, e), this.selected(e.selected)
                }}), u.J.prototype.e = function(t, e) {
                return u.s.prototype.e.call(this, "li", u.l.B({className: "vjs-menu-item", innerHTML: this.j.label}, e))
            }, u.J.prototype.q = function() {
                this.selected(f)
            }, u.J.prototype.selected = function(t) {
                t ? (this.o("vjs-selected"), this.b.setAttribute("aria-selected", f)) : (this.r("vjs-selected"), this.b.setAttribute("aria-selected", l))
            }, u.L = u.s.extend({h: function(t, e) {
                    u.s.call(this, t, e), this.za = this.va(), this.V(this.za), this.O && 0 === this.O.length && this.G(), this.d("keyup", this.da), this.b.setAttribute("aria-haspopup", f), this.b.setAttribute("role", "button")
                }}), t = u.L.prototype, t.ra = l, t.va = function() {
                var t = new u.ga(this.c);
                if (this.options().title && t.ia().appendChild(u.e("li", {className: "vjs-menu-title", innerHTML: u.$(this.options().title), Sd: -1})), this.O = this.createItems())
                    for (var e = 0; e < this.O.length; e++)
                        ca(t, this.O[e]);
                return t
            }, t.ua = m(), t.S = function() {
                return this.className + " vjs-menu-button " + u.s.prototype.S.call(this)
            }, t.Va = m(), t.Ua = m(), t.q = function() {
                this.W("mouseout", u.bind(this, function() {
                    G(this.za), this.b.blur()
                })), this.ra ? K(this) : L(this)
            }, t.da = function(t) {
                t.preventDefault(), 32 == t.which || 13 == t.which ? this.ra ? K(this) : L(this) : 27 == t.which && this.ra && K(this)
            }, u.F = function(t) {
                "number" == typeof t ? this.code = t : "string" == typeof t ? this.message = t : "object" == typeof t && u.l.B(this, t), this.message || (this.message = u.F.gd[this.code] || "")
            }, u.F.prototype.code = 0, u.F.prototype.message = "", u.F.prototype.status = j, u.F.Ra = "MEDIA_ERR_CUSTOM MEDIA_ERR_ABORTED MEDIA_ERR_NETWORK MEDIA_ERR_DECODE MEDIA_ERR_SRC_NOT_SUPPORTED MEDIA_ERR_ENCRYPTED".split(" "), u.F.gd = {1: "You aborted the video playback", 2: "A network error caused the video download to fail part-way.", 3: "The video playback was aborted due to a corruption problem or because the video used features your browser did not support.", 4: "The video could not be loaded, either because the server or network failed or because the format is not supported.", 5: "The video is encrypted and we do not have the keys to decrypt it."};
            for (var M = 0; M < u.F.Ra.length; M++)
                u.F[u.F.Ra[M]] = M, u.F.prototype[u.F.Ra[M]] = M;
            var N, O, P, Q;
            for (N = ["requestFullscreen exitFullscreen fullscreenElement fullscreenEnabled fullscreenchange fullscreenerror".split(" "), "webkitRequestFullscreen webkitExitFullscreen webkitFullscreenElement webkitFullscreenEnabled webkitfullscreenchange webkitfullscreenerror".split(" "), "webkitRequestFullScreen webkitCancelFullScreen webkitCurrentFullScreenElement webkitCancelFullScreen webkitfullscreenchange webkitfullscreenerror".split(" "), "mozRequestFullScreen mozCancelFullScreen mozFullScreenElement mozFullScreenEnabled mozfullscreenchange mozfullscreenerror".split(" "), "msRequestFullscreen msExitFullscreen msFullscreenElement msFullscreenEnabled MSFullscreenChange MSFullscreenError".split(" ")], O = N[0], Q = 0; Q < N.length; Q++)
                if (N[Q][1]in document) {
                    P = N[Q];
                    break
                }
            if (P)
                for (u.Na.Bb = {}, Q = 0; Q < P.length; Q++)
                    u.Na.Bb[O[Q]] = P[Q];
            u.Player = u.a.extend({h: function(t, e, n) {
                    this.P = t, t.id = t.id || "vjs_video_" + u.v++, e = u.l.B(da(t), e), this.z = {}, this.Bc = e.poster, this.yb = e.controls, t.controls = l, e.Ec = l, this.I(function() {
                        this.d("loadstart", this.Cd), this.d("ended", this.yd), this.d("play", this.Ob), this.d("firstplay", this.Ad), this.d("pause", this.Nb), this.d("progress", this.Dd), this.d("durationchange", this.yc), this.d("fullscreenchange", this.Bd)
                    }), u.a.call(this, this, e, n), this.controls() ? this.o("vjs-controls-enabled") : this.o("vjs-controls-disabled"), u.Aa[this.T] = this, e.plugins && u.l.wa(e.plugins, function(t, e) {
                        this[t](e)
                    }, this);
                    var i, s, o, r, a, c;
                    i = u.bind(this, this.reportUserActivity), this.d("mousedown", function() {
                        i(), clearInterval(s), s = setInterval(i, 250)
                    }), this.d("mousemove", function(t) {
                        (t.screenX != a || t.screenY != c) && (a = t.screenX, c = t.screenY, i())
                    }), this.d("mouseup", function() {
                        i(), clearInterval(s)
                    }), this.d("keydown", i), this.d("keyup", i), o = setInterval(u.bind(this, function() {
                        this.na && (this.na = l, this.userActive(f), clearTimeout(r), r = setTimeout(u.bind(this, function() {
                            this.na || this.userActive(l)
                        }), 2e3))
                    }), 250), this.d("dispose", function() {
                        clearInterval(o), clearTimeout(r)
                    })
                }}), t = u.Player.prototype, t.j = u.options, t.dispose = function() {
                this.k("dispose"), this.p("dispose"), u.Aa[this.T] = j, this.P && this.P.player && (this.P.player = j), this.b && this.b.player && (this.b.player = j), clearInterval(this.Ya), this.Ba(), this.g && this.g.dispose(), u.a.prototype.dispose.call(this)
            }, t.e = function() {
                var t = this.b = u.a.prototype.e.call(this, "div"), e = this.P;
                if (e.removeAttribute("width"), e.removeAttribute("height"), e.hasChildNodes()) {
                    var n, i, s, o, r;
                    for (n = e.childNodes, i = n.length, r = []; i--; )
                        s = n[i], o = s.nodeName.toLowerCase(), "track" === o && r.push(s);
                    for (n = 0; n < r.length; n++)
                        e.removeChild(r[n])
                }
                return t.id = e.id, t.className = e.className, e.id += "_html5_api", e.className = "vjs-tech", e.player = t.player = this, this.o("vjs-paused"), this.width(this.j.width, f), this.height(this.j.height, f), e.parentNode && e.parentNode.insertBefore(t, e), u.Eb(e, t), t
            }, t.Kc = function() {
                this.mc && this.Ba(), this.mc = setInterval(u.bind(this, function() {
                    this.k("timeupdate")
                }), 250)
            }, t.Ba = function() {
                clearInterval(this.mc), this.k("timeupdate")
            }, t.Cd = function() {
                this.p("play", T), this.W("play", T), this.error() && this.error(j), u.r(this.b, "vjs-has-started")
            }, t.Ob = function() {
                u.r(this.b, "vjs-paused"), u.o(this.b, "vjs-playing")
            }, t.Ad = function() {
                this.j.starttime && this.currentTime(this.j.starttime), this.o("vjs-has-started")
            }, t.Nb = function() {
                u.r(this.b, "vjs-playing"), u.o(this.b, "vjs-paused")
            }, t.Dd = function() {
                1 == this.bufferedPercent() && this.k("loadedalldata")
            }, t.yd = function() {
                this.j.loop && (this.currentTime(0), this.play())
            }, t.yc = function() {
                var t = U(this, "duration");
                t && (0 > t && (t = 1 / 0), this.duration(t), 1 / 0 === t ? this.o("vjs-live") : this.r("vjs-live"))
            }, t.Bd = function() {
                this.isFullscreen() ? this.o("vjs-fullscreen") : this.r("vjs-fullscreen")
            }, t.play = function() {
                return V(this, "play"), this
            }, t.pause = function() {
                return V(this, "pause"), this
            }, t.paused = function() {
                return U(this, "paused") === l ? l : f
            }, t.currentTime = function(t) {
                return t !== b ? (V(this, "setCurrentTime", t), this.Jb && this.k("timeupdate"), this) : this.z.currentTime = U(this, "currentTime") || 0
            }, t.duration = function(t) {
                return t !== b ? (this.z.duration = parseFloat(t), this) : (this.z.duration === b && this.yc(), this.z.duration || 0)
            }, t.buffered = function() {
                var t = U(this, "buffered"), e = t.length - 1, n = this.z.tb = this.z.tb || 0;
                return t && e >= 0 && t.end(e) !== n && (n = t.end(e), this.z.tb = n), u.zb(0, n)
            }, t.bufferedPercent = function() {
                return this.duration() ? this.buffered().end(0) / this.duration() : 0
            }, t.volume = function(t) {
                return t !== b ? (t = Math.max(0, Math.min(1, parseFloat(t))), this.z.volume = t, V(this, "setVolume", t), u.Ld(t), this) : (t = parseFloat(U(this, "volume")), isNaN(t) ? 1 : t)
            }, t.muted = function(t) {
                return t !== b ? (V(this, "setMuted", t), this) : U(this, "muted") || l
            }, t.ab = function() {
                return U(this, "supportsFullScreen") || l
            }, t.vc = l, t.isFullscreen = function(t) {
                return t !== b ? (this.vc = !!t, this) : this.vc
            }, t.isFullScreen = function(t) {
                return u.log.warn('player.isFullScreen() has been deprecated, use player.isFullscreen() with a lowercase "s")'), this.isFullscreen(t)
            }, t.requestFullscreen = function() {
                var t = u.Na.Bb;
                return this.isFullscreen(f), t ? (u.d(document, t.qd, u.bind(this, function() {
                    this.isFullscreen(document[t.fullscreenElement]), this.isFullscreen() === l && u.p(document, t.qd, arguments.callee), this.k("fullscreenchange")
                })), this.b[t.requestFullscreen]()) : this.g.ab() ? V(this, "enterFullScreen") : (this.td = f, this.kd = document.documentElement.style.overflow, u.d(document, "keydown", u.bind(this, this.qc)), document.documentElement.style.overflow = "hidden", u.o(document.body, "vjs-full-window"), this.k("enterFullWindow"), this.k("fullscreenchange")), this
            }, t.exitFullscreen = function() {
                var t = u.Na.Bb;
                return this.isFullscreen(l), t ? document[t.exitFullscreen]() : this.g.ab() ? V(this, "exitFullScreen") : (ea(this), this.k("fullscreenchange")), this
            }, t.qc = function(t) {
                27 === t.keyCode && (this.isFullscreen() === f ? this.exitFullscreen() : ea(this))
            }, t.src = function(t) {
                if (t === b)
                    return U(this, "src");
                if (t instanceof Array) {
                    var e;
                    t:{
                        e = t;
                        for (var n = 0, i = this.j.techOrder; n < i.length; n++) {
                            var s = u.$(i[n]), o = window.videojs[s];
                            if (o) {
                                if (o.isSupported())
                                    for (var r = 0, a = e; r < a.length; r++) {
                                        var c = a[r];
                                        if (o.canPlaySource(c)) {
                                            e = {source: c, g: s};
                                            break t
                                        }
                                    }
                            } else
                                u.log.error('The "' + s + '" tech is undefined. Skipped browser support check for that tech.')
                        }
                        e = l
                    }
                    e ? (t = e.source, e = e.g, e == this.Ca ? this.src(t) : R(this, e, t)) : (this.error({code: 4, message: this.options().notSupportedMessage}), this.Ea())
                } else
                    t instanceof Object ? window.videojs[this.Ca].canPlaySource(t) ? this.src(t.src) : this.src([t]) : (this.z.src = t, this.ca ? (V(this, "src", t), "auto" == this.j.preload && this.load(), this.j.autoplay && this.play()) : this.I(function() {
                        this.src(t)
                    }));
                return this
            }, t.load = function() {
                return V(this, "load"), this
            }, t.currentSrc = function() {
                return U(this, "currentSrc") || this.z.src || ""
            }, t.Xa = function(t) {
                return t !== b ? (V(this, "setPreload", t), this.j.preload = t, this) : U(this, "preload")
            }, t.autoplay = function(t) {
                return t !== b ? (V(this, "setAutoplay", t), this.j.autoplay = t, this) : U(this, "autoplay")
            }, t.loop = function(t) {
                return t !== b ? (V(this, "setLoop", t), this.j.loop = t, this) : U(this, "loop")
            }, t.poster = function(t) {
                return t === b ? this.Bc : (this.Bc = t, V(this, "setPoster", t), this.k("posterchange"), void 0)
            }, t.controls = function(t) {
                return t !== b ? (t = !!t, this.yb !== t && ((this.yb = t) ? (this.r("vjs-controls-disabled"), this.o("vjs-controls-enabled"), this.k("controlsenabled")) : (this.r("vjs-controls-enabled"), this.o("vjs-controls-disabled"), this.k("controlsdisabled"))), this) : this.yb
            }, u.Player.prototype.Tb, t = u.Player.prototype, t.usingNativeControls = function(t) {
                return t !== b ? (t = !!t, this.Tb !== t && ((this.Tb = t) ? (this.o("vjs-using-native-controls"), this.k("usingnativecontrols")) : (this.r("vjs-using-native-controls"), this.k("usingcustomcontrols"))), this) : this.Tb
            }, t.ba = j, t.error = function(t) {
                return t === b ? this.ba : t === j ? (this.ba = t, this.r("vjs-error"), this) : (this.ba = t instanceof u.F ? t : new u.F(t), this.k("error"), this.o("vjs-error"), u.log.error("(CODE:" + this.ba.code + " " + u.F.Ra[this.ba.code] + ")", this.ba.message, this.ba), this)
            }, t.ended = function() {
                return U(this, "ended")
            }, t.seeking = function() {
                return U(this, "seeking")
            }, t.na = f, t.reportUserActivity = function() {
                this.na = f
            }, t.Sb = f, t.userActive = function(t) {
                return t !== b ? (t = !!t, t !== this.Sb && ((this.Sb = t) ? (this.na = f, this.r("vjs-user-inactive"), this.o("vjs-user-active"), this.k("useractive")) : (this.na = l, this.g && this.g.W("mousemove", function(t) {
                    t.stopPropagation(), t.preventDefault()
                }), this.r("vjs-user-active"), this.o("vjs-user-inactive"), this.k("userinactive"))), this) : this.Sb
            }, t.playbackRate = function(t) {
                return t !== b ? (V(this, "setPlaybackRate", t), this) : this.g && this.g.n && this.g.n.playbackRate ? U(this, "playbackRate") : 1
            }, u.Ha = u.a.extend(), u.Ha.prototype.j = {fe: "play", children: {playToggle: {}, currentTimeDisplay: {}, timeDivider: {}, durationDisplay: {}, remainingTimeDisplay: {}, liveDisplay: {}, progressControl: {}, fullscreenToggle: {}, volumeControl: {}, muteToggle: {}, playbackRateMenuButton: {}}}, u.Ha.prototype.e = function() {
                return u.e("div", {className: "vjs-control-bar"})
            }, u.Zb = u.a.extend({h: function(t, e) {
                    u.a.call(this, t, e)
                }}), u.Zb.prototype.e = function() {
                var t = u.a.prototype.e.call(this, "div", {className: "vjs-live-controls vjs-control"});
                return this.u = u.e("div", {className: "vjs-live-display", innerHTML: '<span class="vjs-control-text">Stream Type </span>LIVE', "aria-live": "off"}), t.appendChild(this.u), t
            }, u.bc = u.s.extend({h: function(t, e) {
                    u.s.call(this, t, e), t.d("play", u.bind(this, this.Ob)), t.d("pause", u.bind(this, this.Nb))
                }}), t = u.bc.prototype, t.sa = "Play", t.S = function() {
                return"vjs-play-control " + u.s.prototype.S.call(this)
            }, t.q = function() {
                this.c.paused() ? this.c.play() : this.c.pause()
            }, t.Ob = function() {
                u.r(this.b, "vjs-paused"), u.o(this.b, "vjs-playing"), this.b.children[0].children[0].innerHTML = "Pause"
            }, t.Nb = function() {
                u.r(this.b, "vjs-playing"), u.o(this.b, "vjs-paused"), this.b.children[0].children[0].innerHTML = "Play"
            }, u.fb = u.a.extend({h: function(t, e) {
                    u.a.call(this, t, e), t.d("timeupdate", u.bind(this, this.fa))
                }}), u.fb.prototype.e = function() {
                var t = u.a.prototype.e.call(this, "div", {className: "vjs-current-time vjs-time-controls vjs-control"});
                return this.u = u.e("div", {className: "vjs-current-time-display", innerHTML: '<span class="vjs-control-text">Current Time </span>0:00', "aria-live": "off"}), t.appendChild(this.u), t
            }, u.fb.prototype.fa = function() {
                var t = this.c.$a ? this.c.z.currentTime : this.c.currentTime();
                this.u.innerHTML = '<span class="vjs-control-text">Current Time </span>' + u.ya(t, this.c.duration())
            }, u.gb = u.a.extend({h: function(t, e) {
                    u.a.call(this, t, e), t.d("timeupdate", u.bind(this, this.fa))
                }}), u.gb.prototype.e = function() {
                var t = u.a.prototype.e.call(this, "div", {className: "vjs-duration vjs-time-controls vjs-control"});
                return this.u = u.e("div", {className: "vjs-duration-display", innerHTML: '<span class="vjs-control-text">Duration Time </span>0:00', "aria-live": "off"}), t.appendChild(this.u), t
            }, u.gb.prototype.fa = function() {
                var t = this.c.duration();
                t && (this.u.innerHTML = '<span class="vjs-control-text">Duration Time </span>' + u.ya(t))
            }, u.hc = u.a.extend({h: function(t, e) {
                    u.a.call(this, t, e)
                }}), u.hc.prototype.e = function() {
                return u.a.prototype.e.call(this, "div", {className: "vjs-time-divider", innerHTML: "<div><span>/</span></div>"})
            }, u.nb = u.a.extend({h: function(t, e) {
                    u.a.call(this, t, e), t.d("timeupdate", u.bind(this, this.fa))
                }}), u.nb.prototype.e = function() {
                var t = u.a.prototype.e.call(this, "div", {className: "vjs-remaining-time vjs-time-controls vjs-control"});
                return this.u = u.e("div", {className: "vjs-remaining-time-display", innerHTML: '<span class="vjs-control-text">Remaining Time </span>-0:00', "aria-live": "off"}), t.appendChild(this.u), t
            }, u.nb.prototype.fa = function() {
                this.c.duration() && (this.u.innerHTML = '<span class="vjs-control-text">Remaining Time </span>-' + u.ya(this.c.duration() - this.c.currentTime()))
            }, u.Ia = u.s.extend({h: function(t, e) {
                    u.s.call(this, t, e)
                }}), u.Ia.prototype.sa = "Fullscreen", u.Ia.prototype.S = function() {
                return"vjs-fullscreen-control " + u.s.prototype.S.call(this)
            }, u.Ia.prototype.q = function() {
                this.c.isFullscreen() ? (this.c.exitFullscreen(), this.xb.innerHTML = "Fullscreen") : (this.c.requestFullscreen(), this.xb.innerHTML = "Non-Fullscreen")
            }, u.mb = u.a.extend({h: function(t, e) {
                    u.a.call(this, t, e)
                }}), u.mb.prototype.j = {children: {seekBar: {}}}, u.mb.prototype.e = function() {
                return u.a.prototype.e.call(this, "div", {className: "vjs-progress-control vjs-control"})
            }, u.dc = u.Q.extend({h: function(t, e) {
                    u.Q.call(this, t, e), t.d("timeupdate", u.bind(this, this.ma)), t.I(u.bind(this, this.ma))
                }}), t = u.dc.prototype, t.j = {children: {loadProgressBar: {}, playProgressBar: {}, seekHandle: {}}, barName: "playProgressBar", handleName: "seekHandle"}, t.Ac = "timeupdate", t.e = function() {
                return u.Q.prototype.e.call(this, "div", {className: "vjs-progress-holder", "aria-label": "video progress bar"})
            }, t.ma = function() {
                var t = this.c.$a ? this.c.z.currentTime : this.c.currentTime();
                this.b.setAttribute("aria-valuenow", u.round(100 * this.Db(), 2)), this.b.setAttribute("aria-valuetext", u.ya(t, this.c.duration()))
            }, t.Db = function() {
                return this.c.currentTime() / this.c.duration()
            }, t.Wa = function(t) {
                u.Q.prototype.Wa.call(this, t), this.c.$a = f, this.Xd = !this.c.paused(), this.c.pause()
            }, t.Lb = function(t) {
                t = J(this, t) * this.c.duration(), t == this.c.duration() && (t -= .1), this.c.currentTime(t)
            }, t.Mb = function(t) {
                u.Q.prototype.Mb.call(this, t), this.c.$a = l, this.Xd && this.c.play()
            }, t.Hc = function() {
                this.c.currentTime(this.c.currentTime() + 5)
            }, t.Gc = function() {
                this.c.currentTime(this.c.currentTime() - 5)
            }, u.jb = u.a.extend({h: function(t, e) {
                    u.a.call(this, t, e), t.d("progress", u.bind(this, this.update))
                }}), u.jb.prototype.e = function() {
                return u.a.prototype.e.call(this, "div", {className: "vjs-load-progress", innerHTML: '<span class="vjs-control-text">Loaded: 0%</span>'})
            }, u.jb.prototype.update = function() {
                this.b.style && (this.b.style.width = u.round(100 * this.c.bufferedPercent(), 2) + "%")
            }, u.ac = u.a.extend({h: function(t, e) {
                    u.a.call(this, t, e)
                }}), u.ac.prototype.e = function() {
                return u.a.prototype.e.call(this, "div", {className: "vjs-play-progress", innerHTML: '<span class="vjs-control-text">Progress: 0%</span>'})
            }, u.Ka = u.Y.extend({h: function(t, e) {
                    u.Y.call(this, t, e), t.d("timeupdate", u.bind(this, this.fa))
                }}), u.Ka.prototype.defaultValue = "00:00", u.Ka.prototype.e = function() {
                return u.Y.prototype.e.call(this, "div", {className: "vjs-seek-handle", "aria-live": "off"})
            }, u.Ka.prototype.fa = function() {
                var t = this.c.$a ? this.c.z.currentTime : this.c.currentTime();
                this.b.innerHTML = '<span class="vjs-control-text">' + u.ya(t, this.c.duration()) + "</span>"
            }, u.pb = u.a.extend({h: function(t, e) {
                    u.a.call(this, t, e), t.g && t.g.n && t.g.n.volumeControl === l && this.o("vjs-hidden"), t.d("loadstart", u.bind(this, function() {
                        t.g.n && t.g.n.volumeControl === l ? this.o("vjs-hidden") : this.r("vjs-hidden")
                    }))
                }}), u.pb.prototype.j = {children: {volumeBar: {}}}, u.pb.prototype.e = function() {
                return u.a.prototype.e.call(this, "div", {className: "vjs-volume-control vjs-control"})
            }, u.ob = u.Q.extend({h: function(t, e) {
                    u.Q.call(this, t, e), t.d("volumechange", u.bind(this, this.ma)), t.I(u.bind(this, this.ma))
                }}), t = u.ob.prototype, t.ma = function() {
                this.b.setAttribute("aria-valuenow", u.round(100 * this.c.volume(), 2)), this.b.setAttribute("aria-valuetext", u.round(100 * this.c.volume(), 2) + "%")
            }, t.j = {children: {volumeLevel: {}, volumeHandle: {}}, barName: "volumeLevel", handleName: "volumeHandle"}, t.Ac = "volumechange", t.e = function() {
                return u.Q.prototype.e.call(this, "div", {className: "vjs-volume-bar", "aria-label": "volume level"})
            }, t.Lb = function(t) {
                this.c.muted() && this.c.muted(l), this.c.volume(J(this, t))
            }, t.Db = function() {
                return this.c.muted() ? 0 : this.c.volume()
            }, t.Hc = function() {
                this.c.volume(this.c.volume() + .1)
            }, t.Gc = function() {
                this.c.volume(this.c.volume() - .1)
            }, u.ic = u.a.extend({h: function(t, e) {
                    u.a.call(this, t, e)
                }}), u.ic.prototype.e = function() {
                return u.a.prototype.e.call(this, "div", {className: "vjs-volume-level", innerHTML: '<span class="vjs-control-text"></span>'})
            }, u.qb = u.Y.extend(), u.qb.prototype.defaultValue = "00:00", u.qb.prototype.e = function() {
                return u.Y.prototype.e.call(this, "div", {className: "vjs-volume-handle"})
            }, u.ha = u.s.extend({h: function(t, e) {
                    u.s.call(this, t, e), t.d("volumechange", u.bind(this, this.update)), t.g && t.g.n && t.g.n.volumeControl === l && this.o("vjs-hidden"), t.d("loadstart", u.bind(this, function() {
                        t.g.n && t.g.n.volumeControl === l ? this.o("vjs-hidden") : this.r("vjs-hidden")
                    }))
                }}), u.ha.prototype.e = function() {
                return u.s.prototype.e.call(this, "div", {className: "vjs-mute-control vjs-control", innerHTML: '<div><span class="vjs-control-text">Mute</span></div>'})
            }, u.ha.prototype.q = function() {
                this.c.muted(this.c.muted() ? l : f)
            }, u.ha.prototype.update = function() {
                var t = this.c.volume(), e = 3;
                for (0 === t || this.c.muted()?e = 0:.33 > t?e = 1:.67 > t && (e = 2), this.c.muted()?"Unmute" != this.b.children[0].children[0].innerHTML && (this.b.children[0].children[0].innerHTML = "Unmute"):"Mute" != this.b.children[0].children[0].innerHTML && (this.b.children[0].children[0].innerHTML = "Mute"), t = 0; 4 > t; t++)
                    u.r(this.b, "vjs-vol-" + t);
                u.o(this.b, "vjs-vol-" + e)
            }, u.qa = u.L.extend({h: function(t, e) {
                    u.L.call(this, t, e), t.d("volumechange", u.bind(this, this.update)), t.g && t.g.n && t.g.n.Nc === l && this.o("vjs-hidden"), t.d("loadstart", u.bind(this, function() {
                        t.g.n && t.g.n.Nc === l ? this.o("vjs-hidden") : this.r("vjs-hidden")
                    })), this.o("vjs-menu-button")
                }}), u.qa.prototype.va = function() {
                var t = new u.ga(this.c, {lc: "div"}), e = new u.ob(this.c, u.l.B({Wd: f}, this.j.me));
                return t.V(e), t
            }, u.qa.prototype.q = function() {
                u.ha.prototype.q.call(this), u.L.prototype.q.call(this)
            }, u.qa.prototype.e = function() {
                return u.s.prototype.e.call(this, "div", {className: "vjs-volume-menu-button vjs-menu-button vjs-control", innerHTML: '<div><span class="vjs-control-text">Mute</span></div>'})
            }, u.qa.prototype.update = u.ha.prototype.update, u.cc = u.L.extend({h: function(t, e) {
                    u.L.call(this, t, e), this.Mc(), this.Lc(), t.d("loadstart", u.bind(this, this.Mc)), t.d("ratechange", u.bind(this, this.Lc))
                }}), t = u.cc.prototype, t.e = function() {
                var t = u.a.prototype.e.call(this, "div", {className: "vjs-playback-rate vjs-menu-button vjs-control", innerHTML: '<div class="vjs-control-content"><span class="vjs-control-text">Playback Rate</span></div>'});
                return this.xc = u.e("div", {className: "vjs-playback-rate-value", innerHTML: 1}), t.appendChild(this.xc), t
            }, t.va = function() {
                var t = new u.ga(this.m()), e = this.m().options().playbackRates;
                if (e)
                    for (var n = e.length - 1; n >= 0; n--)
                        t.V(new u.lb(this.m(), {rate: e[n] + "x"}));
                return t
            }, t.ma = function() {
                this.w().setAttribute("aria-valuenow", this.m().playbackRate())
            }, t.q = function() {
                for (var t = this.m().playbackRate(), e = this.m().options().playbackRates, n = e[0], i = 0; i < e.length; i++)
                    if (e[i] > t) {
                        n = e[i];
                        break
                    }
                this.m().playbackRate(n)
            }, t.Mc = function() {
                fa(this) ? this.r("vjs-hidden") : this.o("vjs-hidden")
            }, t.Lc = function() {
                fa(this) && (this.xc.innerHTML = this.m().playbackRate() + "x")
            }, u.lb = u.J.extend({lc: "button", h: function(t, e) {
                    var n = this.label = e.rate, i = this.Cc = parseFloat(n, 10);
                    e.label = n, e.selected = 1 === i, u.J.call(this, t, e), this.m().d("ratechange", u.bind(this, this.update))
                }}), u.lb.prototype.q = function() {
                u.J.prototype.q.call(this), this.m().playbackRate(this.Cc)
            }, u.lb.prototype.update = function() {
                this.selected(this.m().playbackRate() == this.Cc)
            }, u.Ja = u.s.extend({h: function(t, e) {
                    u.s.call(this, t, e), t.poster() && this.src(t.poster()), (!t.poster() || !t.controls()) && this.G(), t.d("posterchange", u.bind(this, function() {
                        this.src(t.poster())
                    })), t.d("play", u.bind(this, this.G))
                }});
            var ga = "backgroundSize"in u.A.style;
            u.Ja.prototype.e = function() {
                var t = u.e("div", {className: "vjs-poster", tabIndex: -1});
                return ga || t.appendChild(u.e("img")), t
            }, u.Ja.prototype.src = function(t) {
                var e = this.w();
                t !== b && (ga ? e.style.backgroundImage = 'url("' + t + '")' : e.firstChild.src = t)
            }, u.Ja.prototype.q = function() {
                this.m().controls() && this.c.play()
            }, u.$b = u.a.extend({h: function(t, e) {
                    u.a.call(this, t, e), t.d("canplay", u.bind(this, this.G)), t.d("canplaythrough", u.bind(this, this.G)), t.d("playing", u.bind(this, this.G)), t.d("seeking", u.bind(this, this.show)), t.d("seeked", u.bind(this, this.G)), t.d("ended", u.bind(this, this.G)), t.d("waiting", u.bind(this, this.show))
                }}), u.$b.prototype.e = function() {
                return u.a.prototype.e.call(this, "div", {className: "vjs-loading-spinner"})
            }, u.cb = u.s.extend(), u.cb.prototype.e = function() {
                return u.s.prototype.e.call(this, "div", {className: "vjs-big-play-button", innerHTML: '<span aria-hidden="true"></span>', "aria-label": "play video"})
            }, u.cb.prototype.q = function() {
                this.c.play()
            }, u.hb = u.a.extend({h: function(t, e) {
                    u.a.call(this, t, e), this.update(), t.d("error", u.bind(this, this.update))
                }}), u.hb.prototype.e = function() {
                var t = u.a.prototype.e.call(this, "div", {className: "vjs-error-display"});
                return this.u = u.e("div"), t.appendChild(this.u), t
            }, u.hb.prototype.update = function() {
                this.m().error() && (this.u.innerHTML = this.m().error().message)
            }, u.t = u.a.extend({h: function(t, e, n) {
                    e = e || {}, e.Ec = l, u.a.call(this, t, e, n);
                    var i, s;
                    s = this, i = this.m(), t = function() {
                        if (i.controls() && !i.usingNativeControls()) {
                            var t;
                            s.d("mousedown", s.q), s.d("touchstart", function(e) {
                                e.preventDefault(), t = this.c.userActive()
                            }), s.d("touchmove", function() {
                                t && this.m().reportUserActivity()
                            }), I(s), s.d("tap", s.Ed)
                        }
                    }, e = u.bind(s, s.Id), this.I(t), i.d("controlsenabled", t), i.d("controlsdisabled", e)
                }}), t = u.t.prototype, t.Id = function() {
                this.p("tap"), this.p("touchstart"), this.p("touchmove"), this.p("touchleave"), this.p("touchcancel"), this.p("touchend"), this.p("click"), this.p("mousedown")
            }, t.q = function(t) {
                0 === t.button && this.m().controls() && (this.m().paused() ? this.m().play() : this.m().pause())
            }, t.Ed = function() {
                this.m().userActive(!this.m().userActive())
            }, t.Qb = m(), t.n = {volumeControl: f, fullscreenResize: l, playbackRate: l, progressEvents: l, timeupdateEvents: l}, u.media = {}, u.media.bb = "play pause paused currentTime setCurrentTime duration buffered volume setVolume muted setMuted width height supportsFullScreen enterFullScreen src load currentSrc preload setPreload autoplay setAutoplay loop setLoop error networkState readyState seeking initialTime startOffsetTime played seekable ended videoTracks audioTracks videoWidth videoHeight textTracks defaultPlaybackRate playbackRate mediaGroup controller controls defaultMuted".split(" ");
            for (var i = u.media.bb.length - 1; i >= 0; i--)
                u.t.prototype[u.media.bb[i]] = ha();
            u.f = u.t.extend({h: function(t, e, n) {
                    for (this.n.volumeControl = u.f.dd(), this.n.playbackRate = u.f.cd(), this.n.movingMediaElementInDOM = !u.Sc, this.n.fullscreenResize = f, u.t.call(this, t, e, n), n = u.f.ib.length - 1; n >= 0; n--)
                        u.d(this.b, u.f.ib[n], u.bind(this, this.md));
                    if ((e = e.source) && this.b.currentSrc === e.src && 0 < this.b.networkState ? t.I(function() {
                        t.k("loadstart")
                    }) : e && (this.b.src = e.src), u.fc && t.options().nativeControlsForTouch !== l) {
                        var i, s, o, r;
                        i = this, s = this.m(), e = s.controls(), i.b.controls = !!e, o = function() {
                            i.b.controls = f
                        }, r = function() {
                            i.b.controls = l
                        }, s.d("controlsenabled", o), s.d("controlsdisabled", r), e = function() {
                            s.p("controlsenabled", o), s.p("controlsdisabled", r)
                        }, i.d("dispose", e), s.d("usingcustomcontrols", e), s.usingNativeControls(f)
                    }
                    t.I(function() {
                        this.P && this.j.autoplay && this.paused() && (delete this.P.poster, this.play())
                    }), this.Ea()
                }}), t = u.f.prototype, t.dispose = function() {
                u.t.prototype.dispose.call(this)
            }, t.e = function() {
                var t, e = this.c, n = e.P;
                n && this.n.movingMediaElementInDOM !== l || (n ? (t = n.cloneNode(l), u.f.nc(n), n = t, e.P = j) : n = u.e("video", {id: e.id() + "_html5_api", className: "vjs-tech"}), n.player = e, u.Eb(n, e.w())), t = ["autoplay", "preload", "loop", "muted"];
                for (var i = t.length - 1; i >= 0; i--) {
                    var s = t[i];
                    e.j[s] !== j && (n[s] = e.j[s])
                }
                return n
            }, t.md = function(t) {
                "error" == t.type ? this.m().error(this.error().code) : (t.bubbles = l, this.m().k(t))
            }, t.play = function() {
                this.b.play()
            }, t.pause = function() {
                this.b.pause()
            }, t.paused = function() {
                return this.b.paused
            }, t.currentTime = function() {
                return this.b.currentTime
            }, t.Kd = function(t) {
                try {
                    this.b.currentTime = t
                } catch (e) {
                    u.log(e, "Video is not ready. (Video.js)")
                }
            }, t.duration = function() {
                return this.b.duration || 0
            }, t.buffered = function() {
                return this.b.buffered
            }, t.volume = function() {
                return this.b.volume
            }, t.Qd = function(t) {
                this.b.volume = t
            }, t.muted = function() {
                return this.b.muted
            }, t.Nd = function(t) {
                this.b.muted = t
            }, t.width = function() {
                return this.b.offsetWidth
            }, t.height = function() {
                return this.b.offsetHeight
            }, t.ab = function() {
                return"function" != typeof this.b.webkitEnterFullScreen || !/Android/.test(u.M) && /Chrome|Mac OS X 10.5/.test(u.M) ? l : f
            }, t.oc = function() {
                var t = this.b;
                t.paused && t.networkState <= t.Zd ? (this.b.play(), setTimeout(function() {
                    t.pause(), t.webkitEnterFullScreen()
                }, 0)) : t.webkitEnterFullScreen()
            }, t.nd = function() {
                this.b.webkitExitFullScreen()
            }, t.src = function(t) {
                this.b.src = t
            }, t.load = function() {
                this.b.load()
            }, t.currentSrc = function() {
                return this.b.currentSrc
            }, t.poster = function() {
                return this.b.poster
            }, t.Qb = function(t) {
                this.b.poster = t
            }, t.Xa = function() {
                return this.b.Xa
            }, t.Pd = function(t) {
                this.b.Xa = t
            }, t.autoplay = function() {
                return this.b.autoplay
            }, t.Jd = function(t) {
                this.b.autoplay = t
            }, t.controls = function() {
                return this.b.controls
            }, t.loop = function() {
                return this.b.loop
            }, t.Md = function(t) {
                this.b.loop = t
            }, t.error = function() {
                return this.b.error
            }, t.seeking = function() {
                return this.b.seeking
            }, t.ended = function() {
                return this.b.ended
            }, t.playbackRate = function() {
                return this.b.playbackRate
            }, t.Od = function(t) {
                this.b.playbackRate = t
            }, u.f.isSupported = function() {
                try {
                    u.A.volume = .5
                } catch (t) {
                    return l
                }
                return!!u.A.canPlayType
            }, u.f.ub = function(t) {
                try {
                    return!!u.A.canPlayType(t.type)
                } catch (e) {
                    return""
                }
            }, u.f.dd = function() {
                var t = u.A.volume;
                return u.A.volume = t / 2 + .1, t !== u.A.volume
            }, u.f.cd = function() {
                var t = u.A.playbackRate;
                return u.A.playbackRate = t / 2 + .1, t !== u.A.playbackRate
            };
            var W, ia = /^application\/(?:x-|vnd\.apple\.)mpegurl/i, ja = /^video\/mp4/i;
            u.f.zc = function() {
                4 <= u.Ub && (W || (W = u.A.constructor.prototype.canPlayType), u.A.constructor.prototype.canPlayType = function(t) {
                    return t && ia.test(t) ? "maybe" : W.call(this, t)
                }), u.Wc && (W || (W = u.A.constructor.prototype.canPlayType), u.A.constructor.prototype.canPlayType = function(t) {
                    return t && ja.test(t) ? "maybe" : W.call(this, t)
                })
            }, u.f.Vd = function() {
                var t = u.A.constructor.prototype.canPlayType;
                return u.A.constructor.prototype.canPlayType = W, W = j, t
            }, u.f.zc(), u.f.ib = "loadstart suspend abort error emptied stalled loadedmetadata loadeddata canplay canplaythrough playing waiting seeking seeked ended durationchange timeupdate progress play pause ratechange volumechange".split(" "), u.f.nc = function(t) {
                if (t) {
                    for (t.player = j, t.parentNode && t.parentNode.removeChild(t); t.hasChildNodes(); )
                        t.removeChild(t.firstChild);
                    if (t.removeAttribute("src"), "function" == typeof t.load)
                        try {
                            t.load()
                        } catch (e) {
                        }
                }
            }, u.i = u.t.extend({h: function(t, e, n) {
                    u.t.call(this, t, e, n);
                    var i = e.source;
                    n = e.parentEl;
                    var s = this.b = u.e("div", {id: t.id() + "_temp_flash"}), o = t.id() + "_flash_api";
                    t = t.j;
                    var r, a = u.l.B({readyFunction: "videojs.Flash.onReady", eventProxyFunction: "videojs.Flash.onEvent", errorEventProxyFunction: "videojs.Flash.onError", autoplay: t.autoplay, preload: t.Xa, loop: t.loop, muted: t.muted}, e.flashVars), c = u.l.B({wmode: "opaque", bgcolor: "#000000"}, e.params), h = u.l.B({id: o, name: o, "class": "vjs-tech"}, e.attributes);
                    if (i && (i.type && u.i.vd(i.type) ? (t = u.i.Ic(i.src), a.rtmpConnection = encodeURIComponent(t.wb), a.rtmpStream = encodeURIComponent(t.Rb)) : a.src = encodeURIComponent(u.rc(i.src))), this.setCurrentTime = function(t) {
                        r = t, this.b.vjs_setProperty("currentTime", t)
                    }, this.currentTime = function() {
                        return this.seeking() ? r : this.b.vjs_getProperty("currentTime")
                    }, u.Eb(s, n), e.startTime && this.I(function() {
                        this.load(), this.play(), this.currentTime(e.startTime)
                    }), u.Yb && this.I(function() {
                        u.d(this.w(), "mousemove", u.bind(this, function() {
                            this.m().k({type: "mousemove", bubbles: l})
                        }))
                    }), e.iFrameMode !== f || u.Yb)
                        u.i.ld(e.swf, s, a, c, h);
                    else {
                        var d = u.e("iframe", {id: o + "_iframe", name: o + "_iframe", className: "vjs-tech", scrolling: "no", marginWidth: 0, marginHeight: 0, frameBorder: 0});
                        a.readyFunction = "ready", a.eventProxyFunction = "events", a.errorEventProxyFunction = "errors", u.d(d, "load", u.bind(this, function() {
                            var t, n = d.contentWindow;
                            t = d.contentDocument ? d.contentDocument : d.contentWindow.document, t.write(u.i.sc(e.swf, a, c, h)), n.player = this.c, n.ready = u.bind(this.c, function(e) {
                                var n = this.g;
                                n.b = t.getElementById(e), u.i.vb(n)
                            }), n.events = u.bind(this.c, function(t, e) {
                                this && "flash" === this.Ca && this.k(e)
                            }), n.errors = u.bind(this.c, function(t, e) {
                                u.log("Flash Error", e)
                            })
                        })), s.parentNode.replaceChild(d, s)
                    }
                }}), t = u.i.prototype, t.dispose = function() {
                u.t.prototype.dispose.call(this)
            }, t.play = function() {
                this.b.vjs_play()
            }, t.pause = function() {
                this.b.vjs_pause()
            }, t.src = function(t) {
                if (t === b)
                    return this.currentSrc();
                if (u.i.ud(t) ? (t = u.i.Ic(t), this.he(t.wb), this.ie(t.Rb)) : (t = u.rc(t), this.b.vjs_src(t)), this.c.autoplay()) {
                    var e = this;
                    setTimeout(function() {
                        e.play()
                    }, 0)
                }
            }, t.currentSrc = function() {
                var t = this.b.vjs_getProperty("currentSrc");
                if (t == j) {
                    var e = this.rtmpConnection(), n = this.rtmpStream();
                    e && n && (t = u.i.Rd(e, n))
                }
                return t
            }, t.load = function() {
                this.b.vjs_load()
            }, t.poster = function() {
                this.b.vjs_getProperty("poster")
            }, t.Qb = m(), t.buffered = function() {
                return u.zb(0, this.b.vjs_getProperty("buffered"))
            }, t.ab = r(l), t.oc = r(l);
            var ka = u.i.prototype, X = "rtmpConnection rtmpStream preload defaultPlaybackRate playbackRate autoplay loop mediaGroup controller controls volume muted defaultMuted".split(" "), la = "error networkState readyState seeking initialTime duration startOffsetTime paused played seekable ended videoTracks audioTracks videoWidth videoHeight textTracks".split(" "), Y;
            for (Y = 0; Y < X.length; Y++)
                na(X[Y]), ma();
            for (Y = 0; Y < la.length; Y++)
                na(la[Y]);
            if (u.i.isSupported = function() {
                return 10 <= u.i.version()[0]
            }, u.i.ub = function(t) {
                return t.type ? (t = t.type.replace(/;.*/, "").toLowerCase(), t in u.i.pd || t in u.i.Jc ? "maybe" : void 0) : ""
            }, u.i.pd = {"video/flv": "FLV", "video/x-flv": "FLV", "video/mp4": "MP4", "video/m4v": "MP4"}, u.i.Jc = {"rtmp/mp4": "MP4", "rtmp/flv": "FLV"}, u.i.onReady = function(t) {
                t = u.w(t);
                var e = t.player || t.parentNode.player, n = e.g;
                t.player = e, n.b = t, u.i.vb(n)
            }, u.i.vb = function(t) {
                t.w().vjs_getProperty ? t.Ea() : setTimeout(function() {
                    u.i.vb(t)
                }, 50)
            }, u.i.onEvent = function(t, e) {
                u.w(t).player.k(e)
            }, u.i.onError = function(t, e) {
                var n = u.w(t).player, i = "FLASH: " + e;
                "srcnotfound" == e ? n.error({code: 4, message: i}) : n.error(i)
            }, u.i.version = function() {
                var t = "0,0,0";
                try {
                    t = new window.ActiveXObject("ShockwaveFlash.ShockwaveFlash").GetVariable("$version").replace(/\D+/g, ",").match(/^,?(.+),?$/)[1]
                } catch (e) {
                    try {
                        navigator.mimeTypes["application/x-shockwave-flash"].enabledPlugin && (t = (navigator.plugins["Shockwave Flash 2.0"] || navigator.plugins["Shockwave Flash"]).description.replace(/\D+/g, ",").match(/^,?(.+),?$/)[1])
                    } catch (n) {
                    }
                }
                return t.split(",")
            }, u.i.ld = function(t, e, n, i, s) {
                t = u.i.sc(t, n, i, s), t = u.e("div", {innerHTML: t}).childNodes[0], n = e.parentNode, e.parentNode.replaceChild(t, e);
                var o = n.childNodes[0];
                setTimeout(function() {
                    o.style.display = "block"
                }, 1e3)
            }, u.i.sc = function(t, e, n, i) {
                var s = "", o = "", r = "";
                return e && u.l.wa(e, function(t, e) {
                    s += t + "=" + e + "&amp;"
                }), n = u.l.B({movie: t, flashvars: s, allowScriptAccess: "always", allowNetworking: "all"}, n), u.l.wa(n, function(t, e) {
                    o += '<param name="' + t + '" value="' + e + '" />'
                }), i = u.l.B({data: t, width: "100%", height: "100%"}, i), u.l.wa(i, function(t, e) {
                    r += t + '="' + e + '" '
                }), '<object type="application/x-shockwave-flash"' + r + ">" + o + "</object>"
            }, u.i.Rd = function(t, e) {
                return t + "&" + e
            }, u.i.Ic = function(t) {
                var e = {wb: "", Rb: ""};
                if (!t)
                    return e;
                var n, i = t.indexOf("&");
                return-1 !== i ? n = i + 1 : (i = n = t.lastIndexOf("/") + 1, 0 === i && (i = n = t.length)), e.wb = t.substring(0, i), e.Rb = t.substring(n, t.length), e
            }, u.i.vd = function(t) {
                return t in u.i.Jc
            }, u.i.Yc = /^rtmp[set]?:\/\//i, u.i.ud = function(t) {
                return u.i.Yc.test(t)
            }, u.Xc = u.a.extend({h: function(t, e, n) {
                    if (u.a.call(this, t, e, n), t.j.sources && 0 !== t.j.sources.length)
                        t.src(t.j.sources);
                    else
                        for (e = 0, n = t.j.techOrder; e < n.length; e++) {
                            var i = u.$(n[e]), s = window.videojs[i];
                            if (s && s.isSupported()) {
                                R(t, i);
                                break
                            }
                        }
                }}), u.Player.prototype.textTracks = function() {
                return this.Da = this.Da || []
            }, u.C = u.a.extend({h: function(t, e) {
                    u.a.call(this, t, e), this.T = e.id || "vjs_" + e.kind + "_" + e.language + "_" + u.v++, this.Fc = e.src, this.hd = e["default"] || e.dflt, this.Td = e.title, this.ee = e.srclang, this.wd = e.label, this.aa = [], this.rb = [], this.ka = this.la = 0, this.c.d("fullscreenchange", u.bind(this, this.$c))
                }}), t = u.C.prototype, t.K = q("H"), t.src = q("Fc"), t.Qa = q("hd"), t.title = q("Td"), t.label = q("wd"), t.ed = q("aa"), t.Zc = q("rb"), t.readyState = q("la"), t.mode = q("ka"), t.$c = function() {
                this.b.style.fontSize = this.c.isFullScreen() ? 140 * (screen.width / this.c.width()) + "%" : ""
            }, t.e = function() {
                return u.a.prototype.e.call(this, "div", {className: "vjs-" + this.H + " vjs-text-track"})
            }, t.show = function() {
                qa(this), this.ka = 2, u.a.prototype.show.call(this)
            }, t.G = function() {
                qa(this), this.ka = 1, u.a.prototype.G.call(this)
            }, t.disable = function() {
                2 == this.ka && this.G(), this.c.p("timeupdate", u.bind(this, this.update, this.T)), this.c.p("ended", u.bind(this, this.reset, this.T)), this.reset(), this.c.ja("textTrackDisplay").removeChild(this), this.ka = 0
            }, t.load = function() {
                0 === this.la && (this.la = 1, u.get(this.Fc, u.bind(this, this.Fd), u.bind(this, this.zd)))
            }, t.zd = function(t) {
                this.error = t, this.la = 3, this.k("error")
            }, t.Fd = function(t) {
                var e, n;
                t = t.split("\n");
                for (var i = "", s = 1, o = t.length; o > s; s++)
                    if (i = u.trim(t[s])) {
                        for ( - 1 == i.indexOf("-->")?(e = i, i = u.trim(t[++s])):e = this.aa.length, e = {id:e, index:this.aa.length}, n = i.split(" --> "), e.startTime = ra(n[0]), e.xa = ra(n[1]), n = []; t[++s] && (i = u.trim(t[s])); )
                            n.push(i);
                        e.text = n.join("<br/>"), this.aa.push(e)
                    }
                this.la = 2, this.k("loaded")
            }, t.update = function() {
                if (0 < this.aa.length) {
                    var t = this.c.options().trackTimeOffset || 0, t = this.c.currentTime() + t;
                    if (this.Pb === b || t < this.Pb || this.Ta <= t) {
                        var e, n, i, s, o = this.aa, r = this.c.duration(), a = 0, u = l, c = [];
                        for (t >= this.Ta || this.Ta === b ? s = this.Ab !== b ? this.Ab : 0 : (u = f, s = this.Hb !== b ? this.Hb : o.length - 1); ; ) {
                            if (i = o[s], i.xa <= t)
                                a = Math.max(a, i.xa), i.Ma && (i.Ma = l);
                            else if (t < i.startTime) {
                                if (r = Math.min(r, i.startTime), i.Ma && (i.Ma = l), !u)
                                    break
                            } else
                                u ? (c.splice(0, 0, i), n === b && (n = s), e = s) : (c.push(i), e === b && (e = s), n = s), r = Math.min(r, i.xa), a = Math.max(a, i.startTime), i.Ma = f;
                            if (u) {
                                if (0 === s)
                                    break;
                                s--
                            } else {
                                if (s === o.length - 1)
                                    break;
                                s++
                            }
                        }
                        for (this.rb = c, this.Ta = r, this.Pb = a, this.Ab = e, this.Hb = n, e = this.rb, n = "", t = 0, o = e.length; o > t; t++)
                            n += '<span class="vjs-tt-cue">' + e[t].text + "</span>";
                        this.b.innerHTML = n, this.k("cuechange")
                    }
                }
            }, t.reset = function() {
                this.Ta = 0, this.Pb = this.c.duration(), this.Hb = this.Ab = 0
            }, u.Wb = u.C.extend(), u.Wb.prototype.H = "captions", u.ec = u.C.extend(), u.ec.prototype.H = "subtitles", u.Xb = u.C.extend(), u.Xb.prototype.H = "chapters", u.gc = u.a.extend({h: function(t, e, n) {
                    if (u.a.call(this, t, e, n), t.j.tracks && 0 < t.j.tracks.length) {
                        e = this.c, t = t.j.tracks;
                        for (var i = 0; i < t.length; i++)
                            n = t[i], oa(e, n.kind, n.label, n.language, n)
                    }
                }}), u.gc.prototype.e = function() {
                return u.a.prototype.e.call(this, "div", {className: "vjs-text-track-display"})
            }, u.Z = u.J.extend({h: function(t, e) {
                    var n = this.ea = e.track;
                    e.label = n.label(), e.selected = n.Qa(), u.J.call(this, t, e), this.c.d(n.K() + "trackchange", u.bind(this, this.update))
                }}), u.Z.prototype.q = function() {
                u.J.prototype.q.call(this), pa(this.c, this.ea.T, this.ea.K())
            }, u.Z.prototype.update = function() {
                this.selected(2 == this.ea.mode())
            }, u.kb = u.Z.extend({h: function(t, e) {
                    e.track = {K: function() {
                            return e.kind
                        }, m: t, label: function() {
                            return e.kind + " off"
                        }, Qa: r(l), mode: r(l)}, u.Z.call(this, t, e), this.selected(f)
                }}), u.kb.prototype.q = function() {
                u.Z.prototype.q.call(this), pa(this.c, this.ea.T, this.ea.K())
            }, u.kb.prototype.update = function() {
                for (var t, e = this.c.textTracks(), n = 0, i = e.length, s = f; i > n; n++)
                    t = e[n], t.K() == this.ea.K() && 2 == t.mode() && (s = l);
                this.selected(s)
            }, u.U = u.L.extend({h: function(t, e) {
                    u.L.call(this, t, e), 1 >= this.O.length && this.G()
                }}), u.U.prototype.ua = function() {
                var t, e = [];
                e.push(new u.kb(this.c, {kind: this.H}));
                for (var n = 0; n < this.c.textTracks().length; n++)
                    t = this.c.textTracks()[n], t.K() === this.H && e.push(new u.Z(this.c, {track: t}));
                return e
            }, u.Fa = u.U.extend({h: function(t, e, n) {
                    u.U.call(this, t, e, n), this.b.setAttribute("aria-label", "Captions Menu")
                }}), u.Fa.prototype.H = "captions", u.Fa.prototype.sa = "Captions", u.Fa.prototype.className = "vjs-captions-button", u.La = u.U.extend({h: function(t, e, n) {
                    u.U.call(this, t, e, n), this.b.setAttribute("aria-label", "Subtitles Menu")
                }}), u.La.prototype.H = "subtitles", u.La.prototype.sa = "Subtitles", u.La.prototype.className = "vjs-subtitles-button", u.Ga = u.U.extend({h: function(t, e, n) {
                    u.U.call(this, t, e, n), this.b.setAttribute("aria-label", "Chapters Menu")
                }}), t = u.Ga.prototype, t.H = "chapters", t.sa = "Chapters", t.className = "vjs-chapters-button", t.ua = function() {
                for (var t, e = [], n = 0; n < this.c.textTracks().length; n++)
                    t = this.c.textTracks()[n], t.K() === this.H && e.push(new u.Z(this.c, {track: t}));
                return e
            }, t.va = function() {
                for (var t, e, n = this.c.textTracks(), i = 0, s = n.length, o = this.O = []; s > i; i++)
                    if (t = n[i], t.K() == this.H && t.Qa()) {
                        if (2 > t.readyState())
                            return this.be = t, t.d("loaded", u.bind(this, this.va)), void 0;
                        e = t;
                        break
                    }
                if (n = this.za = new u.ga(this.c), n.ia().appendChild(u.e("li", {className: "vjs-menu-title", innerHTML: u.$(this.H), Sd: -1})), e) {
                    t = e.aa;
                    for (var r, i = 0, s = t.length; s > i; i++)
                        r = t[i], r = new u.eb(this.c, {track: e, cue: r}), o.push(r), n.V(r)
                }
                return 0 < this.O.length && this.show(), n
            }, u.eb = u.J.extend({h: function(t, e) {
                    var n = this.ea = e.track, i = this.cue = e.cue, s = t.currentTime();
                    e.label = i.text, e.selected = i.startTime <= s && s < i.xa, u.J.call(this, t, e), n.d("cuechange", u.bind(this, this.update))
                }}), u.eb.prototype.q = function() {
                u.J.prototype.q.call(this), this.c.currentTime(this.cue.startTime), this.update(this.cue.startTime)
            }, u.eb.prototype.update = function() {
                var t = this.cue, e = this.c.currentTime();
                this.selected(t.startTime <= e && e < t.xa)
            }, u.l.B(u.Ha.prototype.j.children, {subtitlesButton: {}, captionsButton: {}, chaptersButton: {}}), "undefined" != typeof window.JSON && "function" === window.JSON.parse)
                u.JSON = window.JSON;
            else {
                u.JSON = {};
                var Z = /[\u0000\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g;
                u.JSON.parse = function(a, c) {
                    function d(t, e) {
                        var n, i, s = t[e];
                        if (s && "object" == typeof s)
                            for (n in s)
                                Object.prototype.hasOwnProperty.call(s, n) && (i = d(s, n), i !== b ? s[n] = i : delete s[n]);
                        return c.call(t, e, s)
                    }
                    var e;
                    if (a = String(a), Z.lastIndex = 0, Z.test(a) && (a = a.replace(Z, function(t) {
                        return"\\u" + ("0000" + t.charCodeAt(0).toString(16)).slice(-4)
                    })), /^[\],:{}\s]*$/.test(a.replace(/\\(?:["\\\/bfnrt]|u[0-9a-fA-F]{4})/g, "@").replace(/"[^"\\\n\r]*"|true|false|null|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?/g, "]").replace(/(?:^|:|,)(?:\s*\[)+/g, "")))
                        return e = eval("(" + a + ")"), "function" == typeof c ? d({"": e}, "") : e;
                    throw new SyntaxError("JSON.parse(): invalid or malformed JSON data")
                }
            }
            u.jc = function() {
                var t, e, n = document.getElementsByTagName("video");
                if (n && 0 < n.length)
                    for (var i = 0, s = n.length; s > i; i++) {
                        if (!(e = n[i]) || !e.getAttribute) {
                            u.sb();
                            break
                        }
                        e.player === b && (t = e.getAttribute("data-setup"), t !== j && (t = u.JSON.parse(t || "{}"), videojs(e, t)))
                    }
                else
                    u.Oc || u.sb()
            }, u.sb = function() {
                setTimeout(u.jc, 1)
            }, "complete" === document.readyState ? u.Oc = f : u.W(window, "load", function() {
                u.Oc = f
            }), u.sb(), u.Hd = function(t, e) {
                u.Player.prototype[t] = e
            };
            var sa = this;
            sa.Yd = f, $("videojs", u), $("_V_", u), $("videojs.options", u.options), $("videojs.players", u.Aa), $("videojs.TOUCH_ENABLED", u.fc), $("videojs.cache", u.ta), $("videojs.Component", u.a), u.a.prototype.player = u.a.prototype.m, u.a.prototype.options = u.a.prototype.options, u.a.prototype.init = u.a.prototype.h, u.a.prototype.dispose = u.a.prototype.dispose, u.a.prototype.createEl = u.a.prototype.e, u.a.prototype.contentEl = u.a.prototype.ia, u.a.prototype.el = u.a.prototype.w, u.a.prototype.addChild = u.a.prototype.V, u.a.prototype.getChild = u.a.prototype.ja, u.a.prototype.getChildById = u.a.prototype.rd, u.a.prototype.children = u.a.prototype.children, u.a.prototype.initChildren = u.a.prototype.uc, u.a.prototype.removeChild = u.a.prototype.removeChild, u.a.prototype.on = u.a.prototype.d, u.a.prototype.off = u.a.prototype.p, u.a.prototype.one = u.a.prototype.W, u.a.prototype.trigger = u.a.prototype.k, u.a.prototype.triggerReady = u.a.prototype.Ea, u.a.prototype.show = u.a.prototype.show, u.a.prototype.hide = u.a.prototype.G, u.a.prototype.width = u.a.prototype.width, u.a.prototype.height = u.a.prototype.height, u.a.prototype.dimensions = u.a.prototype.jd, u.a.prototype.ready = u.a.prototype.I, u.a.prototype.addClass = u.a.prototype.o, u.a.prototype.removeClass = u.a.prototype.r, u.a.prototype.buildCSSClass = u.a.prototype.S, u.Player.prototype.ended = u.Player.prototype.ended, $("videojs.MediaLoader", u.Xc), $("videojs.TextTrackDisplay", u.gc), $("videojs.ControlBar", u.Ha), $("videojs.Button", u.s), $("videojs.PlayToggle", u.bc), $("videojs.FullscreenToggle", u.Ia), $("videojs.BigPlayButton", u.cb), $("videojs.LoadingSpinner", u.$b), $("videojs.CurrentTimeDisplay", u.fb), $("videojs.DurationDisplay", u.gb), $("videojs.TimeDivider", u.hc), $("videojs.RemainingTimeDisplay", u.nb), $("videojs.LiveDisplay", u.Zb), $("videojs.ErrorDisplay", u.hb), $("videojs.Slider", u.Q), $("videojs.ProgressControl", u.mb), $("videojs.SeekBar", u.dc), $("videojs.LoadProgressBar", u.jb), $("videojs.PlayProgressBar", u.ac), $("videojs.SeekHandle", u.Ka), $("videojs.VolumeControl", u.pb), $("videojs.VolumeBar", u.ob), $("videojs.VolumeLevel", u.ic), $("videojs.VolumeMenuButton", u.qa), $("videojs.VolumeHandle", u.qb), $("videojs.MuteToggle", u.ha), $("videojs.PosterImage", u.Ja), $("videojs.Menu", u.ga), $("videojs.MenuItem", u.J), $("videojs.MenuButton", u.L), $("videojs.PlaybackRateMenuButton", u.cc), u.L.prototype.createItems = u.L.prototype.ua, u.U.prototype.createItems = u.U.prototype.ua, u.Ga.prototype.createItems = u.Ga.prototype.ua, $("videojs.SubtitlesButton", u.La), $("videojs.CaptionsButton", u.Fa), $("videojs.ChaptersButton", u.Ga), $("videojs.MediaTechController", u.t), u.t.prototype.features = u.t.prototype.n, u.t.prototype.n.volumeControl = u.t.prototype.n.Nc, u.t.prototype.n.fullscreenResize = u.t.prototype.n.ce, u.t.prototype.n.progressEvents = u.t.prototype.n.ge, u.t.prototype.n.timeupdateEvents = u.t.prototype.n.je, u.t.prototype.setPoster = u.t.prototype.Qb, $("videojs.Html5", u.f), u.f.Events = u.f.ib, u.f.isSupported = u.f.isSupported, u.f.canPlaySource = u.f.ub, u.f.patchCanPlayType = u.f.zc, u.f.unpatchCanPlayType = u.f.Vd, u.f.prototype.setCurrentTime = u.f.prototype.Kd, u.f.prototype.setVolume = u.f.prototype.Qd, u.f.prototype.setMuted = u.f.prototype.Nd, u.f.prototype.setPreload = u.f.prototype.Pd, u.f.prototype.setAutoplay = u.f.prototype.Jd, u.f.prototype.setLoop = u.f.prototype.Md, u.f.prototype.enterFullScreen = u.f.prototype.oc, u.f.prototype.exitFullScreen = u.f.prototype.nd, u.f.prototype.playbackRate = u.f.prototype.playbackRate, u.f.prototype.setPlaybackRate = u.f.prototype.Od, $("videojs.Flash", u.i), u.i.isSupported = u.i.isSupported, u.i.canPlaySource = u.i.ub, u.i.onReady = u.i.onReady, $("videojs.TextTrack", u.C), u.C.prototype.label = u.C.prototype.label, u.C.prototype.kind = u.C.prototype.K, u.C.prototype.mode = u.C.prototype.mode, u.C.prototype.cues = u.C.prototype.ed, u.C.prototype.activeCues = u.C.prototype.Zc, $("videojs.CaptionsTrack", u.Wb), $("videojs.SubtitlesTrack", u.ec), $("videojs.ChaptersTrack", u.Xb), $("videojs.autoSetup", u.jc), $("videojs.plugin", u.Hd), $("videojs.createTimeRange", u.zb), $("videojs.util", u.oa), u.oa.mergeOptions = u.oa.Kb
        }();/*!
         * Impress Core Support Utilities
         * 
         * Copyright 2012 digital-telepathy
         */
var Impress = {computedPrefixes: ["Moz", "ms", "O", "Webkit", "Khtml"], elements: {}, namespace: "impress", prefixes: ["moz", "ms", "o", "webkit"], support: {}};
!function(t, e, n) {
    Impress.supports = function(i) {
        var s = (this.elements.html = this.elements.html || t("html"), !1);
        if (!this.support[i]) {
            switch (i) {
                case"cssanimations":
                    var o = this.elements.body[0].style, r = "transition";
                    "string" == typeof o[r] && (s = !0), r = r.charAt(0).toUpperCase() + r.substr(1);
                    for (var a = 0; a < this.computedPrefixes.length; a++)
                        "string" == typeof o[this.computedPrefixes[a] + r] && (s = !0);
                    break;
                case"csstransforms3d":
                    var l, u = document.createElement("p"), c = {webkitTransform: "-webkit-transform", OTransform: "-o-transform", msTransform: "-ms-transform", MozTransform: "-moz-transform", transform: "transform"};
                    document.body.appendChild(u);
                    for (var h in c)
                        u.style[h] !== n && (u.style[h] = "translate3d(1px,1px,1px)", l = l || e.getComputedStyle(u).getPropertyValue(c[h]));
                    document.body.removeChild(u), s = l !== n && l.length > 0 && "none" !== l
            }
            this.support[i] = s
        }
        return this.support[i]
    }, Impress.getElements = function(n, i, s) {
        var s = s || !1, o = {}, r = this, a = t(i || "html");
        return t.each(n, function(n, i) {
            -1 != t.inArray(n, [e, "body", "html"]) && (o[n] = r.elements[n]), t.isPlainObject(i) ? (o[n] = o[n] || {}, t.each(i, function(e, i) {
                o[n][e] = s && r.elements[i] ? r.elements[i] : r.elements[i] = t(i, a)
            })) : o[n] = s && r.elements[i] ? r.elements[i] : r.elements[i] = t(i, a)
        }), o
    }, Impress.getTransition = function(n, i) {
        if (!e.getComputedStyle)
            return{};
        var s = t(n), i = i || !1, o = e.getComputedStyle(s[0]), r = {transitionProperty: "transition-property", transitionDuration: "transition-duration", transitionDelay: "transition-delay", transitionTimingFunction: "transition-timing-function"}, a = {};
        for (property in r)
            if (a[r[property]] = o[property] || "", i)
                for (var l in this.computedPrefixes)
                    if (this.prefixes[l]) {
                        var u = this.computedPrefixes[l] + property.charAt(0).toUpperCase() + property.substr(1);
                        a["-" + this.prefixes[l] + "-" + r[property]] = o[u]
                    }
        return a
    }, Impress.prefixCSS = function(t, e) {
        var e = e || !1;
        if (ie && 9 > ie)
            return t;
        for (var n in t) {
            var i = t[n];
            for (var s in this.prefixes)
                valuePrefix = e ? "-" + this.prefixes[s] + "-" : "", t["-" + this.prefixes[s] + "-" + n] = valuePrefix + i
        }
        return t
    }, Impress.scrollTo = function(n, i, s, o) {
        i = i || 500, o = o || 0;
        var r = n;
        isNaN(n) && (r = t(n).offset().top), r += parseInt(s, 10) || 0, t("html, body").delay(o).animate({scrollTop: r}, {duration: i, easing: "swing", complete: function() {
                e.scrollTo(0, r)
            }})
    }, Impress.resize = function() {
        this.previousMode = this.currentMode, this.currentMode = Modernizr.mq("only screen and (max-width: 767px)") ? "mobile" : "desktop", this.previousMode != this.currentMode && this.elements.window.triggerHandler(this.namespace + ":changed-responsive-mode", [this.currentMode])
    }, t(function() {
        Impress.elements.window = t(e), Impress.elements.body = t("body"), Impress.elements.html = t("html"), Impress.elements.window.on("resize", t.throttle(50, function() {
            Impress.resize()
        }))
    })
}(jQuery, window, null);
var ie = function() {
    for (var t, e = 3, n = document.createElement("div"), i = n.getElementsByTagName("i"); n.innerHTML = "<!--[if gt IE " + ++e + "]><i></i><![endif]-->", i[0]; )
        ;
    return Function("/*@cc_on return document.documentMode===10@*/")() && (e = 10), e > 4 ? e : t
}(), FormValidator = function(t, e) {
    this.elements = {}, this.namespace = "formvalidator", this.options = {errorPosition: "after", errorShow: "slideDown", errorHide: "slideUp", errorSpeed: 500, scrollToError: !0, validateFields: !1, html5Validation: !0}, this.options.required = {}, this.initialize(t, e)
};
!function(t, e, n) {
    FormValidator.prototype._bindEvents = function() {
        var t = this;
        this.elements.form.on("submit", function(e) {
            t.validate() || e.preventDefault()
        }), this.elements.form.on("reset", function() {
            t.reset()
        }), this.elements.form.on("blur", "input, textarea, select", function() {
            t.options.required[this.name] && t.validateField(this.name, t.options.validateFields)
        })
    }, FormValidator.prototype._build = function() {
        var e = this;
        this.options.html5Validation || this.elements.form.attr("novalidate", !0), this.elements.fields = t("input[required], textarea[required], select[required]", this.elements.form), this.elements.fields.each(function(n) {
            var i = e.elements.fields.eq(n);
            e.options.required[this.name] = e.options.required[this.name] || {}, "email" == this.type && (e.options.required[this.name].algorithm = "[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?.)+[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?", e.options.required[this.name].message = {empty: "Please enter an email address", algorithm: "Please enter a valid email address"}), e.options.required[this.name] = t.extend(e.options.required[this.name] || {}, i.data())
        })
    }, FormValidator.prototype._run = function() {
        for (var t = [], e = 0; e < arguments.length; e++)
            t.push(arguments[e]);
        var n = t[0], i = t.length > 1 ? t.slice(1) : [];
        return"function" == typeof this[n] ? this[n].apply(this, i) : "undefined" != typeof this[n] ? this[n] : void 0
    }, FormValidator.prototype.initialize = function(e) {
        this.options = t.extend(this.options, arguments[1][0] || {}, t(e).data()), this.elements.form = t(e), this._build(), this._bindEvents()
    }, FormValidator.prototype.errorShow = function(e, n) {
        var i = t.data(e[0], "$error");
        i || (e[this.options.errorPosition]('<span class="validation-error" style="display:none;"></span>'), i = e["after" == this.options.errorPosition ? "next" : "prev"](".validation-error"), t.data(e[0], "$error", i)), i.html(n), e.addClass("error"), i[this.options.errorShow](this.options.errorSpeed)
    }, FormValidator.prototype.errorHide = function(e) {
        var n = t.data(e[0], "$error");
        n && (e.removeClass("error"), n[this.options.errorHide](this.options.errorSpeed))
    }, FormValidator.prototype.option = function(t, e) {
        return e != n && (this.options[t] = e), this.options[t]
    }, FormValidator.prototype.reset = function() {
        for (var e in this.options.required) {
            var n = this.elements.form.find('[name="' + e + '"]');
            if (n.length) {
                var i = t.extend({type: "text", message: "Please fill in this required field"}, this.options.required[e]);
                n.add(n.parent()).removeClass("invalid valid"), i.errorHide ? i.errorHide(n) : this.errorHide(n)
            }
        }
    }, FormValidator.prototype.shouldHideError = function(t, e) {
        var n = !0;
        if (!e.errorHide) {
            var i = [];
            for (var s in this.options.required)
                this.options.required[s].pseudo && this.options.required[s].pseudo == t && i.push(s);
            for (var n = !0, o = 0; o < i.length; o++)
                this.validateField(i[o], !1) || (n = !1);
            this.options.required[t].pseudo && (n = !1)
        }
        return n
    }, FormValidator.prototype.validate = function() {
        var e = !0;
        for (var n in this.options.required) {
            var i = this.options.required[n], s = !1;
            if (i.when) {
                var o = 0, r = 0;
                for (var a in i.when)
                    i.when.hasOwnProperty(a) && r++;
                for (var l in i.when) {
                    var u = new RegExp(i.when[l]), c = t(l);
                    c.length && c.val().match(u) && o++
                }
                s = o == r ? this.validateField(n, !0) : !0
            } else
                s = this.validateField(n, !0);
            s === !1 && (e = !1)
        }
        if (this.elements.form.data("valid", e), this.elements.form.trigger("validate", [this.elements.form]), 0 == this.elements.form.data("valid")) {
            var h = t(".validation-error");
            if (h.length) {
                var d = h.filter(":visible");
                d.length && 1 == this.options.scrollToError && t("html, body").animate({scrollTop: d.eq(0).offset().top - 50}, {duration: 1e3})
            }
        }
        return this.elements.form.data("valid")
    }, FormValidator.prototype.validateField = function(e, i) {
        i == n && (i = !1);
        var s = !1, o = "empty", r = this.elements.form.find('[name="' + e + '"]');
        if (!this.options.required[e])
            return!0;
        if (r.length) {
            var a = t.extend({type: "text", message: "Please fill in this required field"}, this.options.required[e]);
            switch (a.type) {
                case"checkbox":
                    r.is(":checked") && (s = !0);
                    break;
                case"radio":
                    var l = "", u = r.filter(":checked");
                    u.length && "" != u.val().trim() && (s = !0);
                    break;
                default:
                case"text":
                    var l = t.trim(r.val());
                    if ("" != l)
                        if (a.algorithm) {
                            if ("object" == typeof a.algorithm)
                                var c = new RegExp(a.algorithm.pass), h = new RegExp(a.algorithm.fail);
                            else
                                var c = new RegExp(a.algorithm);
                            l.match(c) ? (s = !0, h && l.match(h) && (o = "algorithm", s = !1)) : o = "algorithm"
                        } else
                            s = !0
            }
            if (i === !0) {
                if (a.pseudo)
                    var d = a.pseudo, a = this.options.required[d], r = this.elements.form.find('[name="' + d + '"]');
                if ("object" == typeof a.message)
                    switch (o) {
                        default:
                        case"empty":
                            var p = a.message.empty;
                            break;
                        case"algorithm":
                            var p = a.message.algorithm
                    }
                else
                    p = a.message;
                s === !1 ? (r.add(r.parent()).removeClass("valid").addClass("invalid"), a.errorShow ? a.errorShow(r, p) : this.errorShow(r, p)) : (r.add(r.parent()).removeClass("invalid").addClass("valid"), a.errorHide ? a.errorHide(r) : this.shouldHideError(e, a) && this.errorHide(r))
            }
        }
        return s
    }, t.extend(t.fn, {validator: function() {
            var e = action = arguments, n = this;
            return this.each(function() {
                var i = t.data(this, "FormValidator");
                if (i || (i = new FormValidator(this, e), t.data(this, "FormValidator", i)), action.length > 0) {
                    var s = i._run.apply(i, action);
                    "undefined" != typeof s && (n = s)
                }
            }), n
        }})
}(jQuery, window, null);
var FlipForm = function(t, e) {
    this.current = 0, this.height = -1, this.elements = {}, this.namespace = "flipform", this.options = {method: "css", responseFill: !1, speed: 500, impatient: !1, formSelector: "> form", sidesSelector: ".side"}, this.previous = -1, this.selectors = {body: document.body, prev: "." + this.namespace + "-prev", next: "." + this.namespace + "-next", window: window}, this.initialize(t, e)
};
!function(t, e, n, i) {
    FlipForm.prototype._adjustDimensions = function(t) {
        var t = t || this.options.speed;
        this.height = this.elements.sides.eq(this.current).outerHeight(!0), "css" == this.options.method ? this.elements.container.height(this.height) : "animate" == this.options.method && this.elements.container.animate({height: this.height}, this.options.speed), this.elements.container.add(this.elements.form).css(i.prefixCSS({perspective: 4 * this.elements.form.width()}))
    }, FlipForm.prototype._bindEvents = function() {
        var t = this;
        this.elements.form.on("submit", function(e) {
            e.preventDefault(), t.submit()
        }), this.elements.form.on("click", this.selectors.next, function(e) {
            e.preventDefault(), t.next()
        }), this.elements.form.on("click", this.selectors.prev, function(e) {
            e.preventDefault(), t.prev()
        })
    }, FlipForm.prototype._build = function() {
        var e = this;
        this.elements.container.css(t.extend({position: "static" == this.elements.container.css("position") ? "relative" : this.elements.container.css("position")}, i.prefixCSS({perspective: 4 * this.elements.form.width(), transition: "height " + this.options.speed + "ms"}))), this.elements.form.css(i.prefixCSS({perspective: 4 * this.elements.form.width()})), this._adjustDimensions(), this.elements.sides.each(function(n) {
            var s = e.elements.sides.eq(n), o = {position: "absolute", top: 0, left: 0, zIndex: 10 * (e.elements.sides.length - n)};
            if (s.addClass(e.namespace + "-side-" + (n + 1)), "css" == e.options.method) {
                var r = n == e.current ? 0 : 180;
                s.data("rotation", r), s.css(t.extend(o, i.prefixCSS({"transform-style": "preserve-3d", "backface-visibility": "hidden", transform: "rotateY(" + r + "deg)"}))), setTimeout(function() {
                    s.css(i.prefixCSS({transition: "transform " + e.options.speed + "ms, visibility 0ms " + e.options.speed / 2 + "ms"}, !0))
                }, 1)
            } else
                "animate" == e.options.method && (s.css(o), n == e.current ? s.show() : s.hide())
        })
    }, FlipForm.prototype._run = function() {
        for (var t = [], e = 0; e < arguments.length; e++)
            t.push(arguments[e]);
        var n = t[0], i = t.length > 1 ? t.slice(1) : [];
        return"function" == typeof this[n] ? this[n].apply(this, i) : "undefined" != typeof this[n] ? this[n] : void 0
    }, FlipForm.prototype.flip = function(e) {
        var n = this;
        if (e == this.current)
            return!1;
        this.previous = this.current, this.current = e;
        var s = this.previous < this.current;
        this.elements.sides.each(function(e) {
            var o = n.elements.sides.eq(e), r = rotation = o.data("rotation");
            if (e == n.current || e == n.previous)
                var r = s ? rotation - 180 : rotation + 180;
            if ("css" == n.options.method) {
                var a = t.extend(i.prefixCSS({transform: "rotateY(" + r + "deg)"}), {zIndex: e == n.current ? 1337 : 10 * e});
                i.elements.html.hasClass("ie-10") && (a.visibility = e == n.current ? "visible" : "hidden"), o.css(a).data("rotation", r)
            } else
                "animate" == n.options.method && (e == n.current ? o.fadeIn(n.options.speed) : o.fadeOut(n.options.speed))
        }), this._adjustDimensions()
    }, FlipForm.prototype.initialize = function(e) {
        this.options = t.extend(this.options, arguments[1][0] || {}, t(e).data()), i.supports("cssanimations") || (this.options.method = "animate"), this.options.responseFill && (this.options.impatient = !1), this.selectors.form = this.options.formSelector, this.selectors.sides = this.options.sidesSelector, this.elements = i.getElements(this.selectors, e), this.elements.container = t(e), this._build(), this._bindEvents()
    }, FlipForm.prototype.next = function() {
        var t = Math.min(this.elements.sides.length - 1, this.current + 1);
        this.flip(t)
    }, FlipForm.prototype.prev = function() {
        var t = Math.max(0, this.current - 1);
        this.flip(t)
    }, FlipForm.prototype.option = function(t, e) {
        return e != n && (this.options[t] = e), this.options[t]
    }, FlipForm.prototype.submit = function() {
        var e = !0, n = this;
        return this.elements.form.data("FormValidator") && (e = this.elements.form.validator("validate")), e ? (this.options.impatient && n.next(), this.elements.container.triggerHandler(this.namespace + ":submit", [this]), t.ajax({url: this.elements.form.attr("action"), data: this.elements.form.serialize(), type: this.elements.form.attr("method"), success: function(t) {
                n.options.responseFill && n.elements.sides.eq(n.current + 1).html(t), n.next(), n.elements.container.triggerHandler(n.namespace + ":submitsuccess", [t, n])
            }}), void 0) : !1
    }, t.extend(t.fn, {flipform: function() {
            var e = action = arguments, n = this;
            return this.each(function() {
                var i = t.data(this, "FlipForm");
                if (i || (i = new FlipForm(this, e), t.data(this, "FlipForm", i)), action.length > 0) {
                    var s = i._run.apply(i, action);
                    "undefined" != typeof s && (n = s)
                }
            }), n
        }})
}(jQuery, window, null, Impress), 
        function(t, e, n, i) {
            var s = function(t, e) {
                this._dropdownNavVisible = !1, this._eventTrigger = {}, this._interacting = !1, this._interactingOffset = {top: -1, left: -1}, this.elements = {}, this.disabled = !1, this.current = 0, this.previous = -1, this.namespace = "slider", this.sliderWidth = -1, this.sliderHeight = -1, this.navigationGutter = 0, this.autoPlay = !1, this.preventDefaultSwipeX = !1, this.preventDefaultSwipeY = !0, this.childNav = !1, this.options = {easing: "ease-in-out", loop: !0, method: "css", navigation: ">.slider-navigation", navigationWidth: "24%", next: ">.next", orientation: "horizontal", prev: ">.prev", slides: ">.slide", speed: 500, start: 0, touch: !1, autoPlay: !1, autoPlayDelay: 1e4, dropdownNav: !0, touchThreshold: 4, dynamicHeight: "none", disabledMediaQuery: !1}, this.initialize(t, e)
            };
            s.prototype._addNavigationElements = function(t) {
                var e = this;
                this.elements.slides.each(function(n) {
                    var i = e.elements.slides.eq(n), s = i.data("label") || "Slide " + (n + 1);
                    t.append('<a href="#" data-slide="' + n + '"><span>' + s + "</span></a>")
                })
            }, s.prototype._adjustDimensions = function() {
                var e = this;
                if (this.sliderWidth = this.elements.slider.width(), this.sliderHeight = this._getSliderHeight(), this.elements.navigation.length && "vertical" == this.options.orientation && (Modernizr.mq("only screen and (max-width: 767px)") ? this.showDropdownNav() : this.hideDropdownNav()), this._getNavigationDimensions(), 0 == this.disabled)
                    this.elements.slides.each(function(n) {
                        var s = {position: "absolute", left: (n - e.current) * (e.sliderWidth + e.navigationGutter), width: e.sliderWidth - e.navigationGutter, height: e.sliderHeight};
                        "vertical" == e.options.orientation && (s.left = e.navigationGutter, s.top = (n - e.current) * e.sliderHeight), e.elements.slides.eq(n).css(s, t.extend(i.prefixCSS({transition: "left 0ms, top 0ms"})))
                    }), e._setDynamicSliderHeight(), this.elements.navigation.length && "vertical" == this.options.orientation && (this.elements.navigation.width(this.navigationGutter), this.elements.navigation.height(this.sliderHeight), this.elements.navigationLinks.css({height: Math.round(this.sliderHeight / this.elements.navigationLinks.length)}));
                else {
                    var n;
                    "none" !== this.options.dynamicHeight && (this.elements.slider.css({height: "auto"}), n = {height: "auto", width: "auto"}), this.elements.slides.each(function(s) {
                        n = t.extend(n, {position: "relative", left: "auto"}), e.elements.slides.eq(s).css(n, t.extend(i.prefixCSS({transition: "left 0ms, top 0ms"})))
                    })
                }
            }, s.prototype._autoPlay = function() {
                var t = this;
                setTimeout(function() {
                    t.autoPlay && t.next(), t._autoPlay()
                }, this.options.autoPlayDelay)
            }, s.prototype._bindEvents = function() {
                var n = this;
                this.elements.prev.on("click", function(t) {
                    t.preventDefault(), n.autoPlay = !1, n.prev()
                }), this.elements.next.on("click", function(t) {
                    t.preventDefault(), n.autoPlay = !1, n.next()
                }), this.elements.navigation.on("click", "a", function(e) {
                    e.preventDefault();
                    var i = t.data(this, "$this") || t.data(this, "$this", t(this));
                    n.autoPlay = !1, n.goTo(i.data("slide"))
                }), this.elements.body.on("transitionend webkitTransitionEnd oTransitionEnd MSTransitionEnd", function(t) {
                    n.elements.slides.filter(t.target).length && n._eventComplete()
                }), this.elements.slider.on("mousedown", function(t) {
                    t.button <= 1 && n._touchStart(t)
                }).on("mousemove", function(t) {
                    t.button <= 1 && n._touchMove(t)
                }).on("mouseup", function(t) {
                    t.button <= 1 && n._touchEnd(t)
                }), e.addEventListener && this.elements.slider[0].addEventListener("touchstart", n._touchStart, !1), t(e).resize(function() {
                    n._adjustDimensions()
                }).resize(t.throttle(250, function() {
                    n._setTransition(), n._checkDisable()
                })), this.elements.slider.on(this.namespace + ":before", function(t, e, i) {
                    var s = n.elements.slides.eq(i).data("label") || "Slide " + (i + 1);
                    n._adjustDimensions(), "undefined" != typeof n.elements.dropdownNav && (n.elements.dropdownNavSelected.text(s), n.elements.dropdownNavLinks.removeClass("active").eq(i).addClass("active")), n.elements.navigation.length && n.elements.navigationLinks.removeClass("active").eq(i).addClass("active")
                })
            }, s.prototype._build = function() {
                var e = this;
                "none" != this.options.dynamicHeight && (this.elements.slides.wrapInner('<div class="slide-inner"></div>'), "tallest" == this.options.dynamicHeight && this.elements.slides.wrapInner('<div class="slide-tallest-inner"></div>')), this.sliderHeight = this.elements.slider.height(), this.sliderWidth = this.elements.slider.width(), this.elements.navigation.length && this._buildNavigation(), this.elements.slider.find(this.options.navigation).length > 0 && (this.childNav = !0), "css" == this.options.method && this._setTransition(), this.elements.slides.each(function(n) {
                    var i = {left: (n - e.current) * e.sliderWidth};
                    "vertical" == e.options.orientation && (e.preventDefaultSwipeX = !0, e.preventDefaultSwipeY = !1, i = t.extend(i, {left: this.navigationGutter, top: (n - e.current) * e.sliderHeight, width: e.sliderWidth, height: e.sliderHeight})), e.elements.slides.eq(n).css(i)
                }), this.elements.slider.triggerHandler(this.namespace + ":built", [this, this.current])
            }, s.prototype._buildDropdownNav = function() {
                var e = this;
                this.elements.dropdownNav = t('<div class="' + this.namespace + '-dropdown-nav" />').insertBefore(this.elements.slider), this.elements.dropdownNavSelected = t('<div class="' + this.namespace + '-dropdown-nav-selected" />').appendTo(this.elements.dropdownNav), this.elements.dropdownNavOptions = t('<div class="' + this.namespace + '-dropdown-nav-options" />').appendTo(this.elements.dropdownNav), this.elements.dropdownNavList = t('<div class="' + this.namespace + '-dropdown-nav-list" />').appendTo(this.elements.dropdownNavOptions), this._addNavigationElements(this.elements.dropdownNavList), this.elements.dropdownNavLinks = this.elements.dropdownNavOptions.find("a"), this.elements.dropdownNavSelected.text(this.elements.dropdownNavLinks.eq(this.current).addClass("active").text()), this.elements.dropdownNavSelected.on("click", function(n) {
                    n.preventDefault(), e.elements.dropdownNavOptions.show(), e.elements.body.on("click." + e.namespace, function(n) {
                        t(n.target).closest("." + e.namespace + "-dropdown-nav-list, ." + e.namespace + "-dropdown-nav-selected").length < 1 && (e.elements.dropdownNavOptions.hide(), e.elements.body.off("click." + e.namespace))
                    })
                }), this.elements.dropdownNavOptions.on("click", "a", function(n) {
                    n.preventDefault();
                    var i = t.data(this, "$this") || t.data(this, "$this", t(this));
                    e.elements.dropdownNavOptions.hide(), e.elements.body.off("click." + e.namespace), e.autoPlay = !1, e.goTo(i.data("slide"))
                })
            }, s.prototype._buildNavigation = function() {
                if (this._addNavigationElements(this.elements.navigation), this.elements.navigationLinks = this.elements.navigation.find("a"), this.elements.navigationLinks.eq(this.current).addClass("active"), "vertical" == this.options.orientation) {
                    var t = this.sliderHeight / this.elements.slides.length;
                    this._getNavigationDimensions(), this.elements.navigation.css({position: "absolute", top: 0, left: 0, width: this.navigationGutter, height: this.sliderHeight}), this.elements.navigationLinks.css({position: "relative", height: t + "px"}).eq(this.current).addClass("active")
                }
            }, s.prototype._checkDisable = function() {
                this.disabled = Modernizr.mq(this.options.disabledMediaQuery) ? !0 : !1
            }, s.prototype._eventComplete = function() {
                this._triggerEvent("complete") && (this.animating = !1, this.elements.slider.triggerHandler(this.namespace + ":complete", [this]), this._eventTrigger.complete = 0)
            }, s.prototype._getElements = function() {
                this.elements.body = t(document.body), this.elements.slides = this.elements.slider.find(this.options.slides), this.elements.prev = this.elements.slider.find(this.options.prev), this.elements.prev.length || (this.elements.prev = t(this.options.prev)), this.elements.next = this.elements.slider.find(this.options.next), this.elements.next.length || (this.elements.next = t(this.options.next)), this.elements.navigation = this.elements.slider.find(this.options.navigation), this.elements.navigation.length || (this.elements.navigation = t(this.options.navigation))
            }, s.prototype._getNavigationDimensions = function() {
                return 0 == this.elements.navigation.length ? !1 : "vertical" != this.options.orientation ? !1 : (this.navigationGutter = this.options.navigationWidth, -1 != this.options.navigationWidth.indexOf("%") && (this.navigationGutter = parseFloat(this.options.navigationWidth.replace("%", "")) / 100 * this.sliderWidth), this._dropdownNavVisible && (this.navigationGutter = 0), void 0)
            }, s.prototype._getSliderHeight = function() {
                var e = this, n = 0;
                if ("none" != this.options.dynamicHeight)
                    if ("tallest" == this.options.dynamicHeight) {
                        var i = new Array;
                        t(this.elements.slides).each(function(n) {
                            i.push(t(e.elements.slides.get(n)).find(".slide-inner").get(0).scrollHeight)
                        }), n = Math.max.apply(Math, i)
                    } else
                        "pogo" == this.options.dynamicHeight && (n = t(this.elements.slides.get(e.current)).find(".slide-inner").get(0).scrollHeight);
                else
                    n = this.elements.slider.height();
                return n
            }, s.prototype._setDynamicSliderHeight = function() {
                if ("none" != this.options.dynamicHeight) {
                    var e = 0;
                    this.childNav && (e = this.elements.navigation.get(0).scrollHeight);
                    var n = {height: this.sliderHeight + e};
                    this.elements.slider.css(n, t.extend(n, i.prefixCSS({transition: "height 250ms"})))
                }
            }, s.prototype._setTransition = function(t) {
                var e = i.prefixCSS({transition: "left " + this.options.speed + "ms " + this.options.easing + ", top " + this.options.speed + "ms " + this.options.easing});
                t != n ? t.css(e) : this.elements.slides.css(e)
            }, s.prototype._touchEnd = function(s) {
                var o = t(this).data("CSSSlider") == n ? this : t(this).data("CSSSlider");
                if (o.options.touch === !1 || o.disabled === !0)
                    return!1;
                e.addEventListener && o.elements.slider[0].removeEventListener("touchmove", o._touchMove, !1), o._interacting = !1, o._interactingOffset.end = o._touchOffset(s), o.elements.slides.css(i.prefixCSS({"transition-duration": o.options.speed + "ms"}));
                var r = !1, a = "next";
                "horizontal" == o.options.orientation ? (r = Math.abs(o._interactingOffset.left / o.sliderWidth) >= .5, o._interactingOffset.left > 0 && (a = "prev")) : (r = Math.abs(o._interactingOffset.top / o.sliderHeight) >= .5, o._interactingOffset.top > 0 && (a = "prev")), o._interactionMoving && r ? (o.autoPlay = !1, o[a]()) : o.elements.slides.each(function(t) {
                    var e = {};
                    "horizontal" == o.options.orientation ? e.left = (t - o.current) * o.sliderWidth : e.top = (t - o.current) * o.sliderHeight, o.elements.slides.eq(t).css(e)
                }), e.addEventListener && o.elements.slider[0].removeEventListener("touchend", o._touchEnd, !1)
            }, s.prototype._touchMove = function(e) {
                var i = t(this).data("CSSSlider") == n ? this : t(this).data("CSSSlider");
                if (i.options.touch === !1 || i.disabled === !0)
                    return!1;
                if (i._interacting) {
                    i._interactionMoving = !0;
                    var s = i._touchOffset(e);
                    if (i._interactingOffset.left = s.left - i._interactingOffset.start.left, i._interactingOffset.top = s.top - i._interactingOffset.start.top, Math.abs(i._interactingOffset.left) > Math.abs(i._interactingOffset.top)) {
                        if ("horizontal" == i.options.orientation && e.preventDefault(), i.preventDefaultSwipeX)
                            return!1
                    } else if (Math.abs(i._interactingOffset.top) > Math.abs(i._interactingOffset.left))
                        return"vertical" == i.options.orientation && e.preventDefault(), !1;
                    i.elements.slides.each(function(t) {
                        var e = {};
                        "horizontal" == i.options.orientation ? e.left = (t - i.current) * i.sliderWidth + i._interactingOffset.left : e.top = (t - i.current) * i.sliderHeight + i._interactingOffset.top, i.elements.slides.eq(t).css(e)
                    })
                }
            }, s.prototype._touchOffset = function(t) {
                var e = {top: -1, left: -1};
                return t.touches ? t.touches.length && (t.touches[0].pageY && (e.top = t.touches[0].pageY), t.touches[0].pageX && (e.left = t.touches[0].pageX)) : (e.top = t.screenY, e.left = t.screenX), e
            }, s.prototype._touchStart = function(s) {
                var o = t(this).data("CSSSlider") == n ? this : t(this).data("CSSSlider");
                if (o.options.touch === !1 || o.disabled === !0)
                    return!1;
                o._interacting = !0, o._interactionMoving = !1;
                var r = o._touchOffset(s);
                o.autoPlay = !1, o._interactingOffset = {top: r.top, left: r.left, start: {top: r.top, left: r.left}}, o.elements.slides.css(i.prefixCSS({"transition-duration": "0ms"})), e.addEventListener && (o.elements.slider[0].addEventListener("touchmove", o._touchMove, !1), o.elements.slider[0].addEventListener("touchend", o._touchEnd, !1))
            }, s.prototype._triggerEvent = function(t) {
                return this._eventTrigger[t] == n && (this._eventTrigger[t] = 0), this._eventTrigger[t]++, this._eventTrigger[t] == this.elements.slides.length ? (this._eventTrigger[t] = 0, !0) : !1
            }, s.prototype.goTo = function(e) {
                if (0 == this.disabled) {
                    var n = -1;
                    switch (typeof e) {
                        case"number":
                            n = e;
                            break;
                        case"string":
                        case"object":
                            n = this.elements.slides.index(t(e))
                    }
                    n > -1 && this.slide(n)
                }
            }, s.prototype.hideDropdownNav = function() {
                this._dropdownNavVisible = !1, "undefined" != typeof this.elements.dropdownNav && this.elements.dropdownNav.hide(), this.elements.navigation.show()
            }, s.prototype.initialize = function(e) {
                var n = this;
                this.options = t.extend(this.options, arguments[1][0] || {}, t(e).data()), this.autoPlay = this.options.autoPlay, i.supports("cssanimations") || (this.options.method = "animate"), this.current = this.options.start, this.elements.slider = t(e), this._getElements(), this._bindEvents(), this._checkDisable(), this._build(), this._autoPlay(), this._adjustDimensions(), setTimeout(function() {
                    n._setTransition()
                }, 100)
            }, s.prototype.next = function() {
                if (0 == this.disabled) {
                    var t = Math.min(this.elements.slides.length - 1, this.current + 1);
                    this.options.loop !== !1 && this.current == this.elements.slides.length - 1 && (t = 0), this.goTo(t)
                }
            }, s.prototype.option = function(t, e) {
                return e != n && (this.options[t] = e), this.options[t]
            }, s.prototype.prev = function() {
                if (0 == this.disabled) {
                    var t = Math.max(0, this.current - 1);
                    this.options.loop !== !1 && 0 == this.current && (t = this.elements.slides.length - 1), this.goTo(t)
                }
            }, s.prototype.run = function() {
                for (var t = [], e = 0; e < arguments.length; e++)
                    t.push(arguments[e]);
                var n = t[0], i = t.length > 1 ? t.slice(1) : [];
                return"function" == typeof this[n] ? this[n].apply(this, i) : "undefined" != typeof this[n] ? (i.length > 0 && (this[n] = i[0]), this[n]) : void 0
            }, s.prototype.slide = function(t) {
                var e = this;
                this.animating = !0, this.previous = this.current, this.current = t, this.elements.slider.triggerHandler(this.namespace + ":before", [this, t]), this.elements.navigation.length && this.elements.navigationLinks.removeClass("active").eq(this.current).addClass("active"), this.elements.slides.each(function(t) {
                    var n = e.elements.slides.eq(t);
                    "animate" == e.options.method && n.stop();
                    var i = {};
                    "horizontal" == e.options.orientation ? i.left = (t - e.current) * e.sliderWidth : i.top = (t - e.current) * e.sliderHeight, n[e.options.method](i, e.options.speed, function() {
                        "css" != e.options.method && e._eventComplete.apply(e)
                    })
                })
            }, s.prototype.showDropdownNav = function() {
                return this.options.dropdownNav ? (this.elements.dropdownNav || this._buildDropdownNav(), this._dropdownNavVisible = !0, this.elements.dropdownNav.show(), this.elements.navigation.hide(), void 0) : !1
            }, t.extend(t.fn, {cssSlider: function() {
                    var e = action = arguments, n = this;
                    return this.each(function() {
                        var i = t.data(this, "CSSSlider");
                        if (i || (i = new s(this, e), t.data(this, "CSSSlider", i)), action.length > 0) {
                            var o = i.run.apply(i, action);
                            "undefined" != typeof o && (n = o)
                        }
                    }), n
                }})
        }(jQuery, window, null, Impress);
var YAxis = function(t, e) {
    this.animations = [], this.direction = "down", this.elements = {}, this.height = 0, this.isWindow = !0, this.namespace = "yaxis", this.options = {method: "css", counter: "." + this.namespace + "-counter"}, this.scrollTop = 0, this.selectors = {body: "body", window: window}, this.initialize(t, e)
};
!function(t, e, n, i) {
    YAxis.prototype._bindEvents = function() {
        var t = this;
        this.isWindow ? this.context.on("scroll", function(e) {
            t.update(e)
        }) : this.context.on("mousewheel", function(e, n, i, s) {
            t.update(e, n, i, s)
        }), this.elements.window.on("resize", function() {
            t.height = t.context.outerHeight()
        })
    }, YAxis.prototype._build = function() {
        this.context[0] != e && (this.isWindow = !1), this.isWindow && (this.options.preventScroll = !1), this.height = this.context.outerHeight(), this.elements.counter = t(this.options.counter, this.context)
    }, YAxis.prototype._run = function() {
        for (var t = [], e = 0; e < arguments.length; e++)
            t.push(arguments[e]);
        var n = t[0], i = t.length > 1 ? t.slice(1) : [];
        return"function" == typeof this[n] ? this[n].apply(this, i) : "undefined" != typeof this[n] ? this[n] : void 0
    }, YAxis.prototype.attach = function(t, e, n) {
        if (!e)
            return console.error("A callback is required for YAxis attachments", e), !1;
        var i = function() {
        }, s = !1;
        "function" == typeof e ? i = e : (i = e.on, e.off && (s = e.off)), this.animations.push({offset: t, on: i, off: s, alwaysRun: n || !1})
    }, YAxis.prototype.initialize = function(e) {
        this.options = t.extend(this.options, arguments[1][0] || {}, t(e).data()), i.supports("cssanimations") || (this.options.method = "animate"), this.elements = i.getElements(this.selectors), this.context = this.elements.context = t(e), this._build(), this._bindEvents()
    }, YAxis.prototype.option = function(t, e) {
        return e != n && (this.options[t] = e), this.options[t]
    }, YAxis.prototype.update = function(t, e) {
        this.isWindow ? (this.previousPosition = this.scrollTop, this.scrollTop = this.elements.window.scrollTop(), this.direction = this.previousPosition < this.scrollTop ? "down" : "up") : (this.scrollTop = this.scrollTop - e, this.direction = 0 > e ? "down" : "up"), this.scrollTop = Math.round(Math.max(0, this.scrollTop));
        for (var n in this.animations) {
            var i = this.animations[n], s = "function" == typeof i.offset ? i.offset() : i.offset;
            this.scrollTop >= s && (i.alwaysRun || !i.hasRun) && (this.animations[n].hasRun = !0, i.on()), this.scrollTop < s && i.hasRun && i.off !== !1 && (this.animations[n].hasRun = !1, i.off())
        }
        this.elements.counter.text(this.scrollTop)
    }, t.extend(t.fn, {yAxis: function() {
            var e = action = arguments, n = this;
            return this.each(function() {
                var i = t.data(this, "YAxis");
                if (i || (i = new YAxis(this, e), t.data(this, "YAxis", i)), action.length > 0) {
                    var s = i._run.apply(i, action);
                    "undefined" != typeof s && (n = s)
                }
            }), n
        }})
}(jQuery, window, null, Impress), function(t, e, n, i) {
    var s = {elements: {}, selectors: {body: "body", contactForm: "#contact-form", contactThanks: ".contact-thanks", cssSliderElem: ".slide", fullHeight: ".full-height", fullHeightNoInner: ".dt-height", fullHeightSpecial: ".dt-height-special", hero: "#hero", heroInner: "#hero .inner", iframeMap: "#map-iframe", level1Nav: "ul.level-1-nav", manifesto: "#manifesto-audio", manifestoControls: "#audio-controls", mapClose: ".map-close", mapContent: ".map-content", mapOpen: ".map-open", main: "#main", navBar: "#navbar", navIdentity: ".nav-bar .identity", navOverlay: "#nav-overlay", overlayCloseLink: ".close-overlay", overlayOpenLink: ".open-overlay", overlays: ".full-screen-overlay", photoHolder: ".photo", playbookLink: "#dt-playbook-link", smoothScroll: ".smooth-scroll", testimonialsNav: ".testimonials-navigation", testimonialsSlider: ".testimonials-slider"}, lastScroll: 0, scrollSensitivity: 8, _bindVendors: function() {
            var e;
            t(".css-slider").cssSlider(), t(".css-slider").imagesLoaded().done(function(e) {
                t(e.elements).each(function() {
                    t.data(this, "CSSSlider")._adjustDimensions()
                })
            }), t("textarea").autosize(), t(".tile img.lazy").lazyload({threshold: 250, effect: "fadeIn"}), e = setInterval(function() {
                t("#manifesto-audio").length && (t("#manifesto-audio").jPlayer({ready: function() {
                        t(this).jPlayer("setMedia", {mp3: ""})
                    }, swfPath: "/", supplied: "mp3", volume: 1, ended: function() {
                        t(this).jPlayer("pause")
                    }}), clearInterval(e))
            }, 200)
        }, _bindEvents: function() {
            var e = this;
            t("body").on("click", ".audio-play", function(e) {
                e.preventDefault(), t("#audio-controls").addClass("playing"), t("#jp_container_1").addClass("playing"), t(".jp-current-time").toggleClass("dt-blue"), t("body").find("#manifesto-audio").jPlayer("play")
            }).on("click", ".audio-pause", function(e) {
                e.preventDefault(), t("#audio-controls").removeClass("playing"), t("#jp_container_1").removeClass("playing"), t(".jp-current-time").toggleClass("dt-blue"), t("body").find("#manifesto-audio").jPlayer("pause")
            }), t("body").on("click", ".jp-play", function(e) {
                e.preventDefault(), t("#audio-controls").addClass("playing"), t("#jp_container_1").addClass("playing"), t(".jp-current-time").toggleClass("dt-blue")
            }), t("body").on("click", ".jp-pause", function(e) {
                e.preventDefault(), t("#audio-controls").removeClass("playing"), t("#jp_container_1").removeClass("playing"), t(".jp-current-time").toggleClass("dt-blue")
            }), e.elements.window.on("resize", t.throttle(50, function() {
                e.windowResizeAdjust(), e.modernizerMQ(), t(".contact-wrap").length && t(".contact-wrap").data("FlipForm")._adjustDimensions()
            })), e.elements.body.on("click", e.selectors.overlayOpenLink, function(n) {
                n.preventDefault(), e._openOverlay(t(this).data())
            }).on("click", e.selectors.overlayCloseLink, function(t) {
                t.preventDefault(), e._closeOverlay()
            }).on("mouseenter", [this.selectors.level1Nav, "a"].join(" "), function() {
                var e = Math.round(9 * Math.random() + 1);
                t(this).attr("data-pulse", e)
            }).keyup(function(t) {
                27 === t.keyCode && e._closeOverlay()
            }), this.elements.window.on("scroll", function() {
                e.navBarScrollEffect(e.elements.window.scrollTop()), t("html").hasClass("unity-touch") || e.heroMaskAdjust()
            }), this.elements.body.on("click", this.selectors.smoothScroll, function(e) {
                e.preventDefault(), i.scrollTo(t(this).attr("href"))
            }), this.elements.testimonialsNav.on("click", "a", function(n) {
                n.preventDefault();
                var i = e.elements.testimonialsSlider.data("CSSSlider");
                i.goTo(t(this).data("slide")), t(i.elements.slides).add(e.elements.testimonialsNav.find("a")).removeClass("active"), t(i.elements.slides[i.current]).add(t(this)).addClass("active")
            }), this.elements.testimonialsSlider.on("slider:before", function(n, i, s) {
                e.elements.testimonialsNav.find("a").removeClass("active"), t(e.elements.testimonialsNav.find("a")[s]).addClass("active")
            }), t(".css-slider").on("slider:before", function(e, n, i) {
                t(n.elements.slides).removeClass("active"), t(n.elements.slides[i]).addClass("active")
            }), this.elements.overlays.on("transitionend webkitTransitionEnd oTransitionEnd", function(e) {
                "contact-overlay" === t(e.target).attr("id") && t("textarea").autosize()
            }), t(document).on("keyup blur", "#contact-form textarea", function() {
                t(".contact-wrap").data("FlipForm")._adjustDimensions()
            })
        }, _getElements: function() {
            this.elements = i.getElements(this.selectors), this.elements.window = t(e)
        }, _openOverlay: function(e) {
            var n = this, e = e;
            return e ? ("true" === n.elements.body.attr("data-overlay-open") ? (n.elements.body.attr("data-overlay-id", e.overlayTarget), n.elements.body.data("overlay-id", e.overlayTarget)) : (n.elements.body.attr("data-overlay-open", "true"), n.elements.body.data("overlay-open", "true"), n.elements.body.attr("data-overlay-id", e.overlayTarget), n.elements.body.data("overlay-id", e.overlayTarget)), "#contact-overlay" == e.overlayTarget && t("textarea").autosize().show().trigger("autosize.resize"), void 0) : !1
        }, _closeOverlay: function() {
            this.elements.body.attr("data-overlay-open", "false"), this.elements.body.data("overlay-open", "false")
        }, heroMaskAdjust: function() {
            if (t(".hero-mask").length > 0 && this.elements.window.scrollTop() <= this.elements.window[0].innerHeight) {
                var e = this.elements.window.scrollTop() / this.elements.window[0].innerHeight, n = .2 * e;
                scrollOpacity = e + n, scrollTransform = 150 * e + 50, scrollAgency = 150 * e, t(".hero-mask").css({opacity: scrollOpacity}), t("#title-fade").css({transform: "translate(0, -" + scrollTransform + "%)", opacity: 1.3 - scrollOpacity}), t(".slide-content").css({transform: "translate(0, -" + scrollAgency + "%)", opacity: 1 - scrollOpacity})
            }
        }, modernizerMQ: function() {
            Modernizr.mq("only screen and (max-width: 768px)") ? t("html").addClass("unity-xs-screen") : Modernizr.mq("only screen and (max-width: 992px)") ? t("html").addClass("unity-sm-screen") : Modernizr.mq("only screen and (max-width: 1024px)") && t("html").addClass("unity-md-screen")
        }, navBarScrollEffect: function(t) {
            var e = this, n = t - this.lastScroll;
            t > this.elements.window.height() / 4 && (e.elements.navBar.hasClass("hide-nav") || (e.elements.navBar.find(".nav-bar-inner").css({transitionDuration: "0s"}), e.elements.navBar.addClass("hide-nav"), setTimeout(function() {
                e.elements.navBar.find(".nav-bar-inner").attr("style", "")
            }, 1))), 0 >= t ? e.elements.navBar.removeClass("hide-nav scroll-up") : Math.abs(n) > this.scrollSensitivity && e.elements.navBar.hasClass("hide-nav") && (n > 0 ? e.elements.navBar.removeClass("scroll-up") : e.elements.navBar.addClass("scroll-up")), this.lastScroll = t
        }, windowResizeAdjust: function() {
            this.elements.width = this.elements.window.width(), this.elements.height = this.elements.window.height();
            var t = this.elements.height;
            this.elements.hero && this.adjustHeroSize(), this.elements.photoHolder && this.elements.photoHolder.css({height: t + "px"}), this.elements.iframeMap && this.elements.iframeMap.css({height: t + "px", "margin-top": -t + "px"}), this.elements.cssSliderElem && this.elements.cssSliderElem.css({height: t + "px !important"})
        }, adjustHeroSize: function() {
            this.makeFullHeightElement(this.elements.hero), this.elements.body.css({paddingTop: this.elements.hero.height()})
        }, makeFullHeight: function() {
            var e = this;
            e.elements.fullHeight.each(function() {
                var n = this;
                e.elements.window.on("resize", t.throttle(50, function() {
                    e.makeFullHeightElement(n)
                })), e.makeFullHeightElement(n)
            })
        }, makeFullHeightElement: function(e) {
            var n = t(e);
            0 === n.find(".inner").length && n.html('<div class="inner">' + n.html() + "</div>");
            var i = this.elements.height, s = i, o = n.find(".inner");
            if (o.length) {
                var r = o.get(0).scrollHeight;
                n.hasClass("no-overflow") && (s = i > r ? i : r)
            }
            n.css({height: s})
        }, makeFullHeightNoInner: function() {
            var e = this;
            e.elements.fullHeightNoInner.each(function() {
                var n = this;
                e.elements.window.on("resize", t.throttle(50, function() {
                    e.makeFullHeightNoInnerElement(n)
                })), e.makeFullHeightNoInnerElement(n)
            })
        }, makeFullHeightNoInnerElement: function(e) {
            var n = t(e), i = this.elements.height, s = i;
            n.css({height: s})
        }, makeFullHeightNoInnerSpecial: function() {
            var e = this;
            e.elements.fullHeightSpecial.each(function() {
                var n = this;
                e.elements.window.on("resize", t.throttle(50, function() {
                    e.makefullHeightSpecialElement(n)
                })), e.makefullHeightSpecialElement(n)
            })
        }, makefullHeightSpecialElement: function(e) {
            var n = t(e), i = this.elements.height, s = i;
            n.css({height: s - 30})
        }, setupContactForm: function() {
            var e = this;
            e.elements.contactForm.validator(), t(".contact-wrap").flipform({impatient: !0, formSelector: "form#contact-form"}), t(".contact-wrap").on("flipform:submitsuccess", function() {
                t(document).trigger("contactform:submitsuccess", [t(this).parents("body"), t(this)])
            })
        }, map: function() {
            var e = this;
            this.elements.width = this.elements.window.width(), this.elements.height = this.elements.window.height();
            this.elements.height;
            if ("contact" == t("body").data("page")) {
                this.elements.mapOpen.on("click", function() {
                    t("#map-iframe").css({"z-index": 10}), e.elements.navBar.css("z-index", "1"), e.elements.navIdentity.hide(), e.elements.mapContent.hide(), e.elements.mapClose.show()
                }), this.elements.mapClose.on("click", function() {
                    e.elements.mapClose.hide(), t("#map-iframe").css({"z-index": -1}), e.elements.navBar.css("z-index", "5"), e.elements.navIdentity.show(), e.elements.mapContent.show()
                }), this.elements.overlayOpenLink.on("click", function() {
                    e.elements.navIdentity.show()
                }), this.elements.overlayCloseLink.on("click", function() {
                    e.elements.navIdentity.hide()
                });
                var n = t(".contact-form-wrapper");
                n.each(function() {
                    var e = t(this), n = e.find("ul.form > li"), i = e.find("header"), s = e.find(".dt-height-special"), o = e.find("ul.buttons li.cta");
                    o.on("click", function(e) {
                        e.preventDefault();
                        var r = o.index(t(this));
                        o.add(n).removeClass("active"), t(this).add(n[r]).addClass("active"), i.addClass("slide-up"), s.css({overflow: "visible"})
                    })
                })
            }
        }, hamburger: function() {
            var e = function() {
                var e = t(".hamburger"), n = t("body").find(".nav-bar");
                t(".nav-overlay").toggleClass("show"), t("body").toggleClass("nav-open"), t("#menu-text").delay(400).queue(function() {
//                    t(this).fadeToggle(400),
                    t(".menu-text").fadeToggle(400),
                    t(this).dequeue()
                }), e.hasClass("active") ? (e.addClass("active-end"), e.one("transitionend", function() {
                    e.removeClass("active active-end")
                })) : e.addClass("active"), t(".nav-overlay").hasClass("show") ? n.toggleClass("no-bg") : n.delay(400).queue(function() {
                    t(this).toggleClass("no-bg", 500), t(this).dequeue()
                })
            };
            t(".hamburger").click(e), t("#menu-text").click(e)
        }, initialize: function() {
            this._getElements(), this._bindVendors(), this._bindEvents(), this.windowResizeAdjust(), this.makeFullHeight(), this.makeFullHeightNoInner(), this.makeFullHeightNoInnerSpecial(), this.setupContactForm(), this.map(), this.modernizerMQ(), this.hamburger(), this.hasTouch = t("html").hasClass("unity-touch") ? !0 : !1
        }};
    e.Unity = s, t(function() {
        s.initialize()
    })
}(jQuery, window, null, Impress), function(t, e, n, i) {
    var s = {elements: {}, selectors: {body: "body", position: "#apply-now-form-wrap h5"}, resumeFileTypes: ["application/pdf", "application/msword", "application/vnd.openxmlformats-officedocument.wordprocessingml.document", "application/vnd.oasis.opendocument.text", "text/rtf"], _bindVendors: function() {
        }, _bindEvents: function() {
            var e = this;
            t("#fileselect").on("change", function(n) {
                for (var i, s = n.target.files || n.dataTransfer.files, o = 0; i = s[o]; o++)
                    e.resumeFileTypes.indexOf(i.type) > -1 ? (t("#apply-now-form-wrap").data("status", "filling").attr("data-status", "filling"), t("#submitbutton").find("button").removeAttr("disabled", "disabled")) : (t("#apply-now-form-wrap").data("status", "resume-error").attr("data-status", "resume-error"), t("#submitbutton").find("button").attr("disabled", "disabled"))
            }), t("#apply-now-form").on("submit", function(e) {
                var n = t(this);
                e.preventDefault(), t("#apply-now-form-wrap").data("status", "processing").attr("data-status", "processing");
                var i = new FormData(t(this)[0]);
                t.ajax({url: "", data: i, dataType: "json", type: "POST", complete: function(e) {
                        "200" == e.status && (t("#apply-now-form-wrap").data("status", "success").attr("data-status", "success"), t(document).trigger("applyform:submitsuccess", [n.parents("body"), n]))
                    }, cache: !1, contentType: !1, processData: !1})
            }), this.elements.window.on("resize", t.throttle(50, function() {
            }))
        }, _getElements: function() {
            this.elements = i.getElements(this.selectors), this.elements.window = t(e)
        }, initializeForm: function() {
            t("#apply-now-form").validator()
        }, initialize: function() {
            this._getElements(), this._bindVendors(), this._bindEvents(), this.initializeForm()
        }};
    e.Apply = s, t(function() {
        ("ux-designer" == t("body").data("page") || "career-template" == t("body").data("page") || "lp-ux-designer" == t("body").data("page")) && s.initialize()
    })
}(jQuery, window, null, Impress), function(t, e, n, i) {
    var s = {dimensions: {}, elements: {}, selectors: {body: "body", perksSection: "#perks", perksFixedWrap: "#perks-video-fix-wrap", perksVideoWrap: "#perks-video-wrap"}, perksVideo: {aspectRatio: 2100 / 1080, width: 2100, height: 1080}, _bindVendors: function() {
            var t = this;
            this.perksVideo.video = videojs("perks-bg-video", {autoplay: !1, controls: !1, loop: !0, preload: "auto", width: t.perksVideo.width, height: t.perksVideo.height})
        }, _bindEvents: function() {
            var e = this;
            this.elements.window.on("resize", t.throttle(50, function() {
                e._getDimensions(), e.updateVideoSize()
            })), this.elements.window.on("scroll", function() {
                e.dimensions.scrollPosition = e.elements.window.scrollTop(), e.watchPerksScroll(e.dimensions.scrollPosition)
            })
        }, _getDimensions: function() {
            this.dimensions.windowHeight = this.elements.window[0].innerHeight, this.dimensions.windowWidth = this.elements.window.width(), this.dimensions.perksHeight = t("#perks")[0].scrollHeight, this.dimensions.perksTop = t("#perks").offset().top, this.dimensions.perksBottom = this.dimensions.perksTop + this.dimensions.perksHeight, this.dimensions.perksBottomOffset = this.dimensions.perksBottom - this.dimensions.windowHeight
        }, _getElements: function() {
            this.elements = i.getElements(this.selectors), this.elements.window = t(e)
        }, updateVideoSize: function() {
            var t = this.dimensions.windowWidth / this.dimensions.windowHeight, n = this.dimensions.windowWidth, i = this.dimensions.windowHeight;
            t < this.perksVideo.aspectRatio ? (this.perksVideo.width = this.dimensions.windowHeight * this.perksVideo.aspectRatio, this.perksVideo.height = this.dimensions.windowHeight) : (this.perksVideo.width = this.dimensions.windowWidth, this.perksVideo.height = this.dimensions.windowWidth / this.perksVideo.aspectRatio), e.Unity.hasTouch && (n = "auto", i = "auto"), this.elements.perksFixedWrap.css({width: n, height: i}), this.elements.perksVideoWrap.css({width: this.perksVideo.width, height: this.perksVideo.height, "margin-left": -(this.perksVideo.width / 2), "margin-top": -(this.perksVideo.height / 2)}), this.perksVideo.video.width(this.perksVideo.width), this.perksVideo.video.height(this.perksVideo.height)
        }, watchPerksScroll: function(t) {
            this._getDimensions(), t >= this.dimensions.perksTop && t <= this.dimensions.perksBottomOffset ? this.elements.perksFixedWrap.css({position: "fixed", top: 0}) : t > this.dimensions.perksBottomOffset ? this.elements.perksFixedWrap.css({position: "absolute", top: this.dimensions.perksHeight - this.dimensions.windowHeight}) : this.elements.perksFixedWrap.css({position: "absolute", top: 0}), e.Unity.hasTouch && this.elements.perksFixedWrap.css({position: "absolute", top: 0, left: 0, right: 0, bottom: 0}), t < this.dimensions.perksTop - this.dimensions.windowHeight || t > this.dimensions.perksBottom ? this.perksVideo.video.paused() || this.perksVideo.video.pause() : this.perksVideo.video.paused() && this.perksVideo.video.play()
        }, initialize: function() {
            this._getElements(), this._getDimensions(), this._bindVendors(), this._bindEvents(), this.updateVideoSize()
        }};
    e.CareersCommon = s, t(function() {
        "careers" == t("body").data("section") && s.initialize()
    })
}(jQuery, window, null, Impress), function(t) {
    var e = {initialize: function() {
            t('body[data-section="landing-page"] form').on("submit", function() {
                _gaq.push(["_trackEvent", "landingPage", "submit", t("body").attr("data-page")])
            }), t('body[data-section="landing-page"] #oauth-linkedin-btn').on("click", function() {
                _gaq.push(["_trackEvent", "landingPage", "click", "Autofill"])
            }), t('body[data-page="contact"] form').on("submit", function() {
                _gaq.push(["_trackEvent", "contact", "submit", t(this).attr("data-form")])
            }), t('body[data-page="contact"] .cta.default').on("click", function() {
                _gaq.push(["_trackEvent", "contact", "click", t(this).attr("id")])
            }), t("#related-tiles .prev-project-tile").on("click", function() {
                _gaq.push(["_trackEvent", "workAdditionalProject_Left", "click", t(this).attr("id")])
            }), t("#related-tiles .next-project-tile").on("click", function() {
                _gaq.push(["_trackEvent", "workAdditionalProject_Right", "click", t(this).attr("id")])
            }), t("#dt-way-wrapper").on("click", ".btn-our-work", function() {
                _gaq.push(["_trackEvent", "dtWayCTA", "click", t(this).text()])
            }), t("#dt-way-wrapper").on("click", ".btn-careers", function() {
                _gaq.push(["_trackEvent", "dtWayCTA", "click", t(this).text()])
            }), t("#home").on("click", ".btn-see-how", function() {
                _gaq.push(["_trackEvent", "HomeCTA", "click", t(this).text()])
            }), t("#home").on("click", ".btn-see-results", function() {
                _gaq.push(["_trackEvent", "HomeCTA", "click", t(this).text()])
            }), t(".hero.agency").on("click", ".btn-view-project", function() {
                _gaq.push(["_trackEvent", "workHeader", "click", t(this).attr("data-slug")])
            }), t("#header-dot-nav").on("click", "a", function() {
                _gaq.push(["_trackEvent", "workHeaderDot", "click", "navDot"])
            }), t("#work-with-us").on("click", "a", function() {
                _gaq.push(["_trackEvent", "workHeaderDot", "click", "navDot"])
            }), t(document).on("contactform:submitsuccess", function(t, e, n) {
                var i;
                "agency" == e.attr("data-page") && (i = n.find('[type="submit"]').text(), _gaq.push(["_trackEvent", "workForm", "submit", i])), "work-detail" == e.attr("data-page") && (i = n.find('[type="submit"]').text(), _gaq.push(["_trackEvent", "workDetailForm", "submit", i])), "process" == e.attr("data-page") && (i = n.find('[type="submit"]').text(), _gaq.push(["_trackEvent", "workContact", "submit", i])), "contact" == e.attr("data-page") && (i = n.find('[type="submit"]').text(), _gaq.push(["_trackEvent", "contact", "submit", i]))
            }), t(document).on("applyform:submitsuccess", function(t, e, n) {
                var i;
                "careers" == e.attr("data-section") && (i = n.find('[type="submit"]').text(), _gaq.push(["_trackEvent", "application", "submit", i]))
            }), t('[data-page="work-detail"]').on("click", '#fixed-tab-thing[data-stage="design"]', function() {
                _gaq.push(["_trackEvent", "workTab", "click", t(this).find("a.design").text()])
            }), t('[data-page="work-detail"]').on("click", '#fixed-tab-thing[data-stage="next"]', function() {
                _gaq.push(["_trackEvent", "workTab", "click", t(this).find("a.next").text()])
            }), t('[data-page="process"] #pre-footer').on("click", ".cta", function() {
                var e = t(this).attr("data-slug") + "-" + t(this).text().toLowerCase().replace(/[^a-zA-Z]+/g, "-").replace(/-{2,}/, "-");
                _gaq.push(["_trackEvent", "processProject", "click", e])
            }), t('[data-page="contact"]').on("click", '[href="mailto:hello@digital-telepathy.com"]', function() {
                _gaq.push(["_trackEvent", "workTab", "click", t(this).text()])
            })
        }};
    t(function() {
        e.initialize()
    })
}(jQuery, window, null);