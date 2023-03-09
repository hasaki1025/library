webpackJsonp([1], {
    "/ulc": function (e, t) {
    }, "1KKD": function (e, t) {
    }, "1WXS": function (e, t) {
    }, "8f3P": function (e, t) {
    }, "8gYh": function (e, t) {
    }, BAM7: function (e, t) {
    }, NHnr: function (e, t, o) {
        "use strict";
        Object.defineProperty(t, "__esModule", {value: !0});
        var n = o("7+uW"), r = o("Xxa5"), s = o.n(r), a = o("exGp"), i = o.n(a), c = o("mtWM"), l = o.n(c), u = {
            name: "searchBox", components: {}, data: function () {
                return {keyword: ""}
            }, methods: {
                getAllKb: function () {
                    var e = this;
                    return i()(s.a.mark(function t() {
                        var o;
                        return s.a.wrap(function (t) {
                            for (; ;) switch (t.prev = t.next) {
                                case 0:
                                    return t.next = 2, l.a.get("/search", {params: {keyword: e.keyword}});
                                case 2:
                                    (o = t.sent).status >= 200 && o.status < 400 && (e.$message({
                                        message: "success",
                                        type: "success"
                                    }), e.getData(o.data)), e.keyword = "";
                                case 5:
                                case"end":
                                    return t.stop()
                            }
                        }, t, e)
                    }))()
                }
            }, props: {getData: {type: Function}}
        }, d = {
            render: function () {
                var e = this, t = e.$createElement, o = e._self._c || t;
                return o("el-row", [o("el-col", {
                    attrs: {
                        span: 6,
                        offset: 9
                    }
                }, [o("el-input", {
                    staticStyle: {cursor: "pointer"},
                    attrs: {
                        type: "text",
                        "prefix-icon": "el-icon-search",
                        placeholder: "支持“喜欢的用户名”、“ISBN”索书号搜索"
                    },
                    nativeOn: {
                        keyup: function (t) {
                            return !t.type.indexOf("key") && e._k(t.keyCode, "enter", 13, t.key, "Enter") ? null : e.getAllKb.apply(null, arguments)
                        }
                    },
                    model: {
                        value: e.keyword, callback: function (t) {
                            e.keyword = t
                        }, expression: "keyword"
                    }
                })], 1)], 1)
            }, staticRenderFns: []
        };
        var m = o("VU/8")(u, d, !1, function (e) {
            o("Z0U3")
        }, null, null).exports, p = {
            name: "bookInfoTable", props: {bookInfoGroups: Array}, methods: {
                openDetailPage: function (e) {
                    this.$parent.$router.push({
                        path: "/detailsOfBook",
                        query: {bId: e.bId, token: this.$parent.$parent.token}
                    })
                }
            }
        }, f = {
            render: function () {
                var e = this, t = e.$createElement, o = e._self._c || t;
                return o("el-table", {attrs: {data: e.bookInfoGroups}}, [o("el-table-column", {
                    attrs: {
                        prop: "bName",
                        label: "书籍名"
                    }, scopedSlots: e._u([{
                        key: "default", fn: function (t) {
                            return [o("a", {
                                staticStyle: {color: "blue", cursor: "pointer"}, on: {
                                    click: function (o) {
                                        return e.openDetailPage(t.row)
                                    }
                                }
                            }, [e._v(e._s(t.row.bName))])]
                        }
                    }])
                }), e._v(" "), o("el-table-column", {
                    attrs: {
                        prop: "publishingHouse",
                        label: "出版社"
                    }
                }), e._v(" "), o("el-table-column", {
                    attrs: {
                        prop: "author",
                        label: "图书作者"
                    }
                }), e._v(" "), o("el-table-column", {
                    attrs: {
                        prop: "yearOfPublication",
                        label: "年份"
                    }
                }), e._v(" "), o("el-table-column", {attrs: {prop: "language", label: "Chinese"}})], 1)
            }, staticRenderFns: []
        };
        var h = o("VU/8")(p, f, !1, function (e) {
            o("Wmn4")
        }, "data-v-4c99e949", null).exports, g = {
            name: "App", components: {searchBox: m, bookInfoTable: h}, methods: {
                getData: function (e) {
                    this.bookInfoGroups = e
                }, output: function () {
                    console.log(this.bookInfoGroups)
                }, backToFirst: function () {
                    this.$router.push({path: "/FirstPage"})
                }, handleSelect: function (e, t) {
                    console.log(e, t)
                }, toLogIn: function () {
                    this.$router.push({path: "/LogInForAccount"})
                }, toRegister: function () {
                    this.$router.push({path: "/RegisterForAccount"})
                }, toLogOff: function () {
                    this.$router.push({path: "/logOff", query: {token: this.token}})
                }, toLogOut: function () {
                    this.$router.push({path: "/logOut", query: {token: this.token}})
                }, setKindleEmail: function () {
                    this.$router.push({path: "/setKindleEmail", query: {token: this.token}})
                }, registerAsManagement: function () {
                    this.$router.push({path: "/registerAsManagement"})
                }, getToken: function () {
                    console.log(this.token)
                }, getCollections: function () {
                    console.log(this.collections)
                }, toMainPage: function () {
                    this.$router.push({path: "/mainPage", query: {token: this.token}})
                }, toCategory: function () {
                    this.$router.push({path: "/categoryList"})
                }, toChangePassword: function () {
                    this.$router.push({path: "/ChangePassword", query: {token: this.token}})
                }
            }, data: function () {
                return {bookInfoGroups: [], token: "", collections: []}
            }
        }, k = {
            render: function () {
                var e = this, t = e.$createElement, o = e._self._c || t;
                return o("div", [o("el-row", [o("el-col", [o("div", {staticStyle: {height: "40px"}}, [o("el-button", {
                    attrs: {type: "primary"},
                    on: {click: e.backToFirst}
                }, [e._v("图书馆主页")]), e._v(" "), o("el-menu", {
                    attrs: {mode: "horizontal"},
                    on: {select: e.handleSelect}
                }, [o("el-submenu", {attrs: {index: "1"}}, [o("template", {slot: "title"}, [e._v("个人")]), e._v(" "), this.token ? e._e() : o("el-menu-item", {
                    attrs: {index: "1-1"},
                    on: {click: e.toLogIn}
                }, [e._v("登录")]), e._v(" "), this.token ? o("el-menu-item", {
                    attrs: {index: "1-7"},
                    on: {click: e.toMainPage}
                }, [e._v("个人主页")]) : e._e(), e._v(" "), o("el-menu-item", {
                    attrs: {index: "1-2"},
                    on: {click: e.toRegister}
                }, [e._v("注册")]), e._v(" "), this.token ? o("el-menu-item", {
                    attrs: {index: "1-3"},
                    on: {click: e.toLogOut}
                }, [e._v("登出")]) : e._e(), e._v(" "), this.token ? o("el-menu-item", {
                    attrs: {index: "1-4"},
                    on: {click: e.toLogOff}
                }, [e._v("注销")]) : e._e(), e._v(" "), this.token ? o("el-menu-item", {
                    attrs: {index: "1-5"},
                    on: {click: e.setKindleEmail}
                }, [e._v("设置kindle邮箱")]) : e._e(), e._v(" "), o("el-menu-item", {
                    attrs: {index: "1-6"},
                    on: {click: e.registerAsManagement}
                }, [e._v("管理员注册")]), e._v(" "), this.token ? o("el-menu-item", {
                    attrs: {index: "1-8"},
                    on: {click: e.toChangePassword}
                }, [e._v("改密码")]) : e._e()], 2), e._v(" "), o("el-submenu", {attrs: {index: "2"}}, [o("template", {slot: "title"}, [e._v("书籍")]), e._v(" "), o("el-menu-item", {
                    attrs: {index: "2-1"},
                    on: {click: e.toCategory}
                }, [e._v("分类")])], 2)], 1)], 1)])], 1), e._v(" "), o("el-button", {on: {click: e.getToken}}, [e._v("token")]), e._v(" "), o("el-button", {on: {click: e.getCollections}}, [e._v("collections")]), e._v(" "), o("router-view")], 1)
            }, staticRenderFns: []
        };
        var v = o("VU/8")(g, k, !1, function (e) {
            o("nsLx")
        }, "data-v-59453658", null).exports, b = o("/ocq"), _ = {
            name: "PostVerificationCode", methods: {
                PostVerificationCode: function () {
                    var e = this;
                    -1 !== this.email.search("@") && -1 !== this.email.search(".com") ? l.a.get("/mail/sendMail", {params: {mail: this.email}}).then(function (t) {
                        t.status >= 200 && t.status < 400 ? e.$message({
                            message: "请检查邮箱",
                            type: "success"
                        }) : e.$message({message: "error", type: "error"})
                    }) : this.$message({message: "请检查email格式", type: "error"})
                }
            }, props: {email: String}
        }, C = {
            render: function () {
                var e = this.$createElement;
                return (this._self._c || e)("el-button", {on: {click: this.PostVerificationCode}}, [this._v("获取验证码")])
            }, staticRenderFns: []
        };
        var F = o("VU/8")(_, C, !1, function (e) {
            o("SiGE")
        }, "data-v-9cab7276", null).exports, y = {
            name: "ChangePassword", methods: {
                ChangePassword: function () {
                    var e = this, t = new FormData;
                    t.append("mail", this.ChangePasswordForm.mail), t.append("code", this.ChangePasswordForm.VerificationCode), t.append("newPassword", this.ChangePasswordForm.AccountPassWord), l.a.post("/changePassword", t, {headers: {"Content-Type": "multipart/form-data"}}).then(function (t) {
                        t.status >= 200 && t.status < 400 && e.$message({message: "success", type: "success"})
                    })
                }
            }, data: function () {
                var e = this;
                return {
                    ChangePasswordForm: {Email: "", AccountPassWord: "", DoublePassWord: "", VerificationCode: ""},
                    rules: {
                        ChangePassword: {
                            Email: [{required: !0, trigger: "blur"}],
                            AccountPassWord: [{required: !0, trigger: "blur"}],
                            DoublePassWord: [{
                                validator: function (t, o, n) {
                                    o === e.ChangePasswordForm.AccountPassWord ? n() : n(new Error("两次密码不一致"))
                                }, required: !0, trigger: "blur"
                            }],
                            VerificationCode: [{required: !0, trigger: "blur"}]
                        }
                    }
                }
            }, components: {PostVerificationCode: F}
        }, $ = {
            render: function () {
                var e = this, t = e.$createElement, o = e._self._c || t;
                return o("el-form", {
                    ref: "ChangePasswordForm",
                    attrs: {model: e.ChangePasswordForm, rules: e.rules.ChangePassword, "status-icon": ""}
                }, [o("el-form-item", {
                    attrs: {
                        label: "邮箱",
                        prop: "Email"
                    }
                }, [o("el-input", {
                    model: {
                        value: e.ChangePasswordForm.Email, callback: function (t) {
                            e.$set(e.ChangePasswordForm, "Email", t)
                        }, expression: "ChangePasswordForm.Email"
                    }
                })], 1), e._v(" "), o("el-form-item", {
                    attrs: {
                        label: "密码",
                        prop: "AccountPassWord"
                    }
                }, [o("el-input", {
                    attrs: {type: "password"},
                    model: {
                        value: e.ChangePasswordForm.AccountPassWord, callback: function (t) {
                            e.$set(e.ChangePasswordForm, "AccountPassWord", t)
                        }, expression: "ChangePasswordForm.AccountPassWord"
                    }
                })], 1), e._v(" "), o("el-form-item", {
                    attrs: {
                        label: "确认密码",
                        prop: "DoublePassWord"
                    }
                }, [o("el-input", {
                    attrs: {type: "password"},
                    model: {
                        value: e.ChangePasswordForm.DoublePassWord, callback: function (t) {
                            e.$set(e.ChangePasswordForm, "DoublePassWord", t)
                        }, expression: "ChangePasswordForm.DoublePassWord"
                    }
                })], 1), e._v(" "), o("el-form-item", {
                    attrs: {
                        label: "验证码",
                        prop: "VerificationCode"
                    }
                }, [o("el-input", {
                    model: {
                        value: e.ChangePasswordForm.VerificationCode, callback: function (t) {
                            e.$set(e.ChangePasswordForm, "VerificationCode", t)
                        }, expression: "ChangePasswordForm.VerificationCode"
                    }
                })], 1), e._v(" "), o("PostVerificationCode", {attrs: {email: e.ChangePasswordForm.Email}}), e._v(" "), o("el-form-item", [o("el-button", {on: {click: e.ChangePassword}}, [e._v("确认")])], 1)], 1)
            }, staticRenderFns: []
        };
        var P = o("VU/8")(y, $, !1, function (e) {
            o("1KKD")
        }, "data-v-56080ac4", null).exports, R = {
            name: "collection", props: {bookCollectionList: Array, token: String}, methods: {
                checkCollection: function (e) {
                    this.$parent.$router.push({path: "/FirstPage", query: {cId: e}})
                }, createNewCollection: function () {
                    var e = this;
                    this.$prompt("请输入收藏夹名称", "提示", {
                        confirmButtonText: "确定",
                        cancelButtonText: "取消"
                    }).then(function (t) {
                        var o = t.value, n = new FormData;
                        n.append("cName", o), l.a.post("/collection/addCollection", n, {
                            headers: {
                                "Content-Type": "multipart/form-data",
                                Token: e.token
                            }
                        }).then(function (t) {
                            t.status >= 200 && t.status < 400 ? e.$message({
                                message: "success",
                                type: "success"
                            }) : e.$message({message: "fault", type: "error"})
                        })
                    })
                }, collectionDelete: function (e, t) {
                    var o = this;
                    l.a.get("/collection/deleteCollection", {
                        params: {cId: e},
                        headers: {Token: this.token}
                    }).then(function (e) {
                        e.status >= 200 && e.status < 400 && (o.$message({
                            message: "success",
                            type: "success"
                        }), o.$refs.collectionList[t].style.display = "none")
                    })
                }, changeCollectionName: function (e) {
                    var t = this;
                    this.$prompt("请输入收藏夹名称", "提示", {
                        confirmButtonText: "确定",
                        cancelButtonText: "取消"
                    }).then(function (o) {
                        var n = o.value;
                        l.a.post("/collection/updateCollectionName", {
                            cName: n,
                            cId: e
                        }, {headers: {Token: t.token}}).then(function (e) {
                            e.status >= 200 && e.status < 400 ? t.$message({
                                message: "success",
                                type: "success"
                            }) : t.$message({message: "fault", type: "error"})
                        })
                    })
                }, cancelCollect: function (e, t) {
                    var o = this;
                    l.a.get("/collection/deleteCollectionOfUser", {
                        params: {cId: e},
                        headers: {Token: this.token}
                    }).then(function (e) {
                        e.status >= 200 && e.status < 400 && (o.$message({
                            message: "success",
                            type: "success"
                        }), o.$refs.collectionList[t].style.display = "none")
                    })
                }
            }
        }, w = {
            render: function () {
                var e = this, t = e.$createElement, o = e._self._c || t;
                return o("div", [o("a", {
                    staticStyle: {"text-decoration": "underline", cursor: "pointer"},
                    on: {click: e.createNewCollection}
                }, [e._v("新建收藏夹")]), e._v(" "), e._l(e.bookCollectionList, function (t, n) {
                    return o("div", {
                        ref: "collectionList",
                        refInFor: !0
                    }, [o("p", [e._v(e._s(t.cName))]), e._v(" "), o("el-button", {
                        on: {
                            click: function (o) {
                                return e.checkCollection(t.cId)
                            }
                        }
                    }, [e._v("查看")]), e._v(" "), o("el-button", {
                        on: {
                            click: function (o) {
                                return e.collectionDelete(t.cId, n)
                            }
                        }
                    }, [e._v("删除")]), e._v(" "), o("el-button", {
                        on: {
                            click: function (o) {
                                return e.changeCollectionName(t.cId)
                            }
                        }
                    }, [e._v("改名")]), e._v(" "), o("el-button", {
                        on: {
                            click: function (o) {
                                return e.cancelCollect(t.cId, n)
                            }
                        }
                    }, [e._v("取消收藏")])], 1)
                })], 2)
            }, staticRenderFns: []
        };
        var I = {
            name: "mainPage", components: {
                Collection: o("VU/8")(R, w, !1, function (e) {
                    o("rlel")
                }, "data-v-2935459e", null).exports, BookInfoTable: h
            }, methods: {
                getMainPage: function () {
                    var e = this;
                    l.a.get("/MyMainPage", {headers: {Token: this.$route.query.token.toString()}}).then(function (t) {
                        e.user = t.data.user, e.bookList = t.data.bookList, e.bookCollectionList = t.data.bookcollectionList
                    })
                }, returnBeforePage: function () {
                    this.$parent.$router.push("/")
                }
            }, mounted: function () {
                this.getMainPage()
            }, data: function () {
                return {user: {}, bookList: [], bookCollectionList: []}
            }
        }, x = {
            render: function () {
                var e = this, t = e.$createElement, o = e._self._c || t;
                return o("div", [o("div", [o("h1", [e._v("个人信息")]), e._v(" "), o("p", [e._v("email:" + e._s(e.user.email))]), e._v(" "), o("p", [e._v("用户名:" + e._s(e.user.nickName))]), e._v(" "), o("p", [e._v("kindleEmail:" + e._s(e.user.kindleEmail))]), e._v(" "), o("p", [e._v("uid:" + e._s(e.user.uid))])]), e._v(" "), e.bookList ? o("h2", [e._v("上传的书籍")]) : e._e(), e._v(" "), e.bookList ? o("book-info-table", {attrs: {"book-info-groups": e.bookList}}) : e._e(), e._v(" "), e.bookCollectionList ? o("h2", [e._v("收藏夹")]) : e._e(), e._v(" "), o("collection", {
                    attrs: {
                        "book-collection-list": e.bookCollectionList,
                        token: this.$route.query.token
                    }
                })], 1)
            }, staticRenderFns: []
        };
        var E = o("VU/8")(I, x, !1, function (e) {
            o("dtrC")
        }, "data-v-d0fea4de", null).exports, A = {
            name: "bookDownload", props: {uri: String}, methods: {
                download: function () {

                    let url = "http://library.com/download" + this.uri;
                    //TODO
                   /* l.a.get(url).then(function (e) {
                    })*/
                    window.location.href = url;
                }
            }
        }, L = {
            render: function () {
                var e = this.$createElement;
                return (this._self._c || e)("el-button", {on: {click: this.download}}, [this._v("下载")])
            }, staticRenderFns: []
        };
        var B = o("VU/8")(A, L, !1, function (e) {
            o("1WXS")
        }, "data-v-75ba1812", null).exports, q = {
            name: "sendToKindle", props: {bId: Number, token: String}, methods: {
                sendToKindle: function () {
                    l.a.get("/mail/sendToKindle", {params: {bId: this.bId, Token: this.token}})
                }
            }
        }, V = {
            render: function () {
                var e = this.$createElement;
                return (this._self._c || e)("el-button", {on: {click: this.sendToKindle}}, [this._v("send to Kindle")])
            }, staticRenderFns: []
        };
        var T = o("VU/8")(q, V, !1, function (e) {
            o("BAM7")
        }, "data-v-f807c5e8", null).exports, W = {
            name: "addToAdoration",
            props: {collections: Array, bId: Number, token: String},
            methods: {
                addToAdoration: function (e) {
                    l.a.get("/collection/addBook", {headers: {Token: this.token}, params: {bId: this.bId, cId: e}})
                }
            }
        }, S = {
            render: function () {
                var e = this, t = e.$createElement, o = e._self._c || t;
                return o("el-popover", {
                    attrs: {
                        trigger: "click",
                        placement: "right"
                    }
                }, [e._l(e.collections, function (t, n) {
                    return o("a", {
                        staticStyle: {cursor: "pointer", display: "block", "text-decoration": "underline"},
                        on: {
                            click: function (o) {
                                return e.addToAdoration(t.cId)
                            }
                        }
                    }, [e._v(e._s(t.cName))])
                }), e._v(" "), o("el-button", {
                    attrs: {slot: "reference"},
                    slot: "reference"
                }, [e._v("添加至收藏")])], 2)
            }, staticRenderFns: []
        };
        var N = o("VU/8")(W, S, !1, function (e) {
            o("vbcF")
        }, "data-v-7c0934b6", null).exports, D = {
            name: "commentList", props: {commentList: Array, token: String}, methods: {
                like: function (e, t) {
                    var o = this;
                    l.a.post("/comment/like", {
                        userLike: {comId: t, action: 1},
                        isCancel: this.isCancelOfLike[e]
                    }, {headers: {Token: this.token}}).then(function (t) {
                        t.status >= 200 && t.status < 400 && (o.isCancelOfLike[e] = !o.isCancelOfLike[e], o.$message({message: "success"}))
                    })
                }, dislike: function (e, t) {
                    var o = this;
                    l.a.post("/comment/dislike", {
                        userLike: {comId: t, action: -1},
                        isCancel: this.isCancelOfDislike[e]
                    }, {headers: {Token: this.token}}).then(function (t) {
                        t.status >= 200 && t.status < 400 && (o.isCancelOfDislike[e] = !o.isCancelOfDislike[e], o.$message({message: "success"}))
                    })
                }, deleteComment: function (e, t) {
                    var o = this;
                    l.a.get("/comment/delete", {
                        params: {
                            comId: e,
                            uId: this.commentList[t.uId],
                            bId: this.commentList[t.bId]
                        }, headers: {Token: this.token}
                    }).then(function (e) {
                        e.status >= 200 && e.status < 400 && (o.$message({message: "success"}), o.$refs.commentList[t].style.display = "none")
                    })
                }
            }, data: function () {
                return {isCancelOfLike: [], isCancelOfDislike: []}
            }, mounted: function () {
                var e = this;
                this.commentList.forEach(function () {
                    e.isCancelOfLike.push(!1), e.isCancelOfDislike.push(!1)
                })
            }
        }, O = {
            render: function () {
                var e = this, t = e.$createElement, o = e._self._c || t;
                return o("div", {staticClass: "comments"}, [e._v("\n  评论区:\n  "), e._l(e.commentList, function (t, n) {
                    return o("div", {
                        ref: "commentList",
                        refInFor: !0,
                        staticClass: "oneInComments"
                    }, [o("h2", [e._v(e._s(t.comContent))]), e._v(" "), o("p", [e._v("作者:" + e._s(t.uId))]), e._v(" "), o("p", [e._v("like:" + e._s(t.comLike))]), e._v(" "), o("p", [e._v("dislike:" + e._s(t.comDislike))]), e._v(" "), o("i", {
                        staticClass: "el-icon-success",
                        staticStyle: {cursor: "pointer"},
                        on: {
                            click: function (o) {
                                return e.like(n, t.comId)
                            }
                        }
                    }, [e._v("赞")]), e._v(" "), o("i", {
                        staticClass: "el-icon-error",
                        staticStyle: {cursor: "pointer"},
                        on: {
                            click: function (o) {
                                return e.dislike(n, t.comId)
                            }
                        }
                    }, [e._v("踩")]), e._v(" "), o("el-button", {
                        on: {
                            click: function (o) {
                                return e.deleteComment(t.comId, n)
                            }
                        }
                    }, [e._v("删除")])], 1)
                })], 2)
            }, staticRenderFns: []
        };
        var K = o("VU/8")(D, O, !1, function (e) {
            o("/ulc")
        }, "data-v-1d6c6708", null).exports, U = {
            name: "postComment", props: {bId: Number, token: String}, data: function () {
                return {content: ""}
            }, methods: {
                postComment: function () {
                    var e = this;
                    l.a.post("/comment/do", {
                        bId: this.bId,
                        comContent: this.content
                    }, {headers: {Token: this.token}}).then(function (t) {
                        t.status >= 200 && t.status < 400 ? e.$message({
                            message: "success",
                            type: "success"
                        }) : e.$message({message: "fault", type: "error"})
                    })
                }
            }
        }, M = {
            render: function () {
                var e = this, t = e.$createElement, o = e._self._c || t;
                return o("div", [o("el-input", {
                    attrs: {type: "textarea", rows: 3},
                    model: {
                        value: e.content, callback: function (t) {
                            e.content = t
                        }, expression: "content"
                    }
                }), e._v(" "), o("el-button", {on: {click: e.postComment}}, [e._v("发表")])], 1)
            }, staticRenderFns: []
        };
        var G = {
            name: "detailsOfBook", components: {
                PostComment: o("VU/8")(U, M, !1, function (e) {
                    o("8f3P")
                }, "data-v-1fb96196", null).exports, CommentList: K, AddToAdoration: N, SendToKindle: T, BookDownload: B
            }, methods: {
                getDetailsOfBook: function () {
                    var e = this;
                    l.a.get("/getbook", {params: {bId: this.$route.query.bId}}).then(function (t) {
                        e.book = t.data
                    })
                }, getComments: function () {
                    var e = this;
                    l.a.get("/comment/get", {params: {bId: this.$route.query.bId}}).then(function (t) {
                        e.commentList = t.data
                    })
                }, getBookByAuthor: function (e) {
                    this.$parent.$router.push({path: "/FirstPage", query: {author: e}})
                }, getBookByCate: function (e) {
                    this.$parent.$router.push({path: "/FirstPage", query: {cateId: e}})
                }
            }, mounted: function () {
                this.getDetailsOfBook(), this.getComments()
            }, data: function () {
                return {book: {}, commentList: []}
            }
        }, X = {
            render: function () {
                var e = this, t = e.$createElement, o = e._self._c || t;
                return o("div", [o("h1", [e._v(e._s(e.book.book.bName))]), e._v(" "), o("p", [e._v("简介：" + e._s(e.book.book.description))]), e._v(" "), o("a", [e._v("作者："), o("a", {
                    staticStyle: {
                        "text-decoration": "underline",
                        cursor: "pointer"
                    }, on: {
                        click: function (t) {
                            return e.getBookByAuthor(e.book.book.author)
                        }
                    }
                }, [e._v(e._s(e.book.book.author))])]), e._v(" "), e._l(e.book.categoryList, function (t, n) {
                    return o("p", [e._v("分类："), o("a", {
                        staticStyle: {
                            "text-decoration": "underline",
                            cursor: "pointer"
                        }, on: {
                            click: function (o) {
                                return e.getBookByCate(t.cateId)
                            }
                        }
                    }, [e._v(e._s(t.cateName))])])
                }), e._v(" "), o("p", [e._v("language:" + e._s(e.book.book.language))]), e._v(" "), o("p", [e._v("ISBN:" + e._s(e.book.book.ISBN))]), e._v(" "), o("p", [e._v("出版年份:" + e._s(e.book.book.yearOfPublication))]), e._v(" "), o("p", [e._v("出版社:" + e._s(e.book.book.publishingHouse))]), e._v(" "), o("book-download", {attrs: {uri: e.book.book.uri}}), e._v(" "), o("send-to-kindle", {
                    attrs: {
                        "b-id": this.$route.query.bId,
                        token: this.$route.query.token
                    }
                }), e._v(" "), o("add-to-adoration", {
                    attrs: {
                        collections: this.$parent.collections,
                        "b-id": this.$route.query.bId,
                        token: this.$route.query.token
                    }
                }), e._v(" "), o("comment-list", {
                    attrs: {
                        "comment-list": e.commentList,
                        token: this.$route.query.token
                    }
                }), e._v(" "), o("post-comment", {
                    attrs: {
                        "b-id": this.$route.query.bId,
                        token: this.$route.query.token
                    }
                })], 2)
            }, staticRenderFns: []
        };
        var Y = o("VU/8")(G, X, !1, function (e) {
            o("pdla")
        }, "data-v-12756e00", null).exports, H = {
            name: "FirstPage", components: {BookInfoTable: h, SearchBox: m}, data: function () {
                return {bookInfoGroups: []}
            }, methods: {
                getData: function (e) {
                    this.bookInfoGroups = e
                }, getBookByAuthor: function (e) {
                    var t = this;
                    l.a.get("/getBookByAuthor", {params: {author: e}}).then(function (e) {
                        t.bookInfoGroups = e.data
                    })
                }, getBookByCate: function (e) {
                    var t = this;
                    l.a.get("/getBookByCate", {params: {cateId: e}}).then(function (e) {
                        t.bookInfoGroups = e.data
                    })
                }, getBookByCollection: function (e) {
                    var t = this;
                    l.a.get("/collection/getBook", {params: {cId: e}}).then(function (e) {
                        t.bookInfoGroups = e.data
                    })
                }
            }, mounted: function () {
                this.$route.query.author && this.getBookByAuthor(this.$route.query.author), this.$route.query.cateId && this.getBookByCate(this.$route.query.cateId), this.$route.query.cId && this.getBookByCollection(this.$route.query.cId)
            }
        }, Z = {
            render: function () {
                var e = this.$createElement, t = this._self._c || e;
                return t("div", [t("searchBox", {attrs: {"get-data": this.getData}}), this._v(" "), t("book-info-table", {attrs: {"book-info-groups": this.bookInfoGroups}})], 1)
            }, staticRenderFns: []
        };
        var z = o("VU/8")(H, Z, !1, function (e) {
            o("c9RE")
        }, "data-v-228a7995", null).exports, j = {
            name: "LogInForAccount", data: function () {
                return {
                    LogInForm: {email: "", AccountPassWord: ""},
                    rules: {
                        LogInRules: {
                            email: [{required: !0, trigger: "blur"}],
                            AccountPassWord: [{required: !0, trigger: "blur"}]
                        }
                    }
                }
            }, methods: {
                LogIn: function () {
                    var e = this;
                    return i()(s.a.mark(function t() {
                        var o, n;
                        return s.a.wrap(function (t) {
                            for (; ;) switch (t.prev = t.next) {
                                case 0:
                                    return o = new FormData, n = void 0, t.next = 4, e.$refs.LogInForm.validate(function (e) {
                                        n = !!e
                                    });
                                case 4:
                                    n && (o.append("email", e.LogInForm.email), o.append("password", e.LogInForm.AccountPassWord), l.a.post("/login", o, {headers: {"Content-Type": "multipart/form-data"}}).then(function (t) {
                                        t.status >= 200 && t.status < 400 && (e.$parent.token = t.data, e.$message({
                                            message: "success",
                                            type: "success"
                                        }), e.getCollections())
                                    }));
                                case 5:
                                case"end":
                                    return t.stop()
                            }
                        }, t, e)
                    }))()
                }, getCollections: function () {
                    var e = this;
                    l.a.get("/collection/getAllCollection", {headers: {Token: this.$parent.token}}).then(function (t) {
                        e.$parent.collections = t.data, console.log(t.data)
                    })
                }
            }
        }, J = {
            render: function () {
                var e = this, t = e.$createElement, o = e._self._c || t;
                return o("el-form", {
                    ref: "LogInForm",
                    attrs: {model: e.LogInForm, rules: e.rules.LogInRules, "status-icon": ""}
                }, [o("el-form-item", {
                    attrs: {
                        label: "邮箱",
                        prop: "email"
                    }
                }, [o("el-input", {
                    model: {
                        value: e.LogInForm.email, callback: function (t) {
                            e.$set(e.LogInForm, "email", t)
                        }, expression: "LogInForm.email"
                    }
                })], 1), e._v(" "), o("el-form-item", {
                    attrs: {
                        label: "密码",
                        prop: "AccountPassWord"
                    }
                }, [o("el-input", {
                    model: {
                        value: e.LogInForm.AccountPassWord, callback: function (t) {
                            e.$set(e.LogInForm, "AccountPassWord", t)
                        }, expression: "LogInForm.AccountPassWord"
                    }
                })], 1), e._v(" "), o("el-form-item", [o("el-button", {on: {click: e.LogIn}}, [e._v("登录")])], 1)], 1)
            }, staticRenderFns: []
        };
        var Q = o("VU/8")(j, J, !1, function (e) {
            o("d8YR")
        }, "data-v-5180966c", null).exports, ee = {
            name: "RegisterForAccount", data: function () {
                return {
                    RegisterForm: {AccountName: "", AccountPassWord: "", Email: "", VerificationCode: ""},
                    rules: {
                        RegisterRules: {
                            AccountName: [{required: !0, trigger: "blur"}],
                            AccountPassWord: [{required: !0, trigger: "blur"}],
                            Email: [{required: !0, trigger: "blur"}],
                            VerificationCode: [{required: !0, trigger: "blur"}]
                        }
                    }
                }
            }, methods: {
                Register: function () {
                    var e = this;
                    return i()(s.a.mark(function t() {
                        var o, n;
                        return s.a.wrap(function (t) {
                            for (; ;) switch (t.prev = t.next) {
                                case 0:
                                    return o = new FormData, n = void 0, t.next = 4, e.$refs.RegisterForm.validate(function (e) {
                                        n = !!e
                                    });
                                case 4:
                                    if (!n) {
                                        t.next = 12;
                                        break
                                    }
                                    return o.append("nickName", e.RegisterForm.AccountName), o.append("password", e.RegisterForm.AccountPassWord), o.append("email", e.RegisterForm.Email), o.append("code", e.RegisterForm.VerificationCode), t.next = 11, l.a.post("/register", o, {headers: {"Content-Type": "multipart/form-data"}});
                                case 11:
                                    t.sent;
                                case 12:
                                case"end":
                                    return t.stop()
                            }
                        }, t, e)
                    }))()
                }
            }, components: {PostVerificationCode: F}
        }, te = {
            render: function () {
                var e = this, t = e.$createElement, o = e._self._c || t;
                return o("el-form", {
                    ref: "RegisterForm",
                    attrs: {model: e.RegisterForm, rules: e.rules.RegisterRules, "status-icon": ""}
                }, [o("el-form-item", {
                    attrs: {
                        label: "用户名",
                        prop: "AccountName"
                    }
                }, [o("el-input", {
                    model: {
                        value: e.RegisterForm.AccountName, callback: function (t) {
                            e.$set(e.RegisterForm, "AccountName", t)
                        }, expression: "RegisterForm.AccountName"
                    }
                })], 1), e._v(" "), o("el-form-item", {
                    attrs: {
                        label: "邮箱",
                        prop: "Email"
                    }
                }, [o("el-input", {
                    model: {
                        value: e.RegisterForm.Email, callback: function (t) {
                            e.$set(e.RegisterForm, "Email", t)
                        }, expression: "RegisterForm.Email"
                    }
                })], 1), e._v(" "), o("el-form-item", {
                    attrs: {
                        label: "密码",
                        prop: "AccountPassWord"
                    }
                }, [o("el-input", {
                    model: {
                        value: e.RegisterForm.AccountPassWord, callback: function (t) {
                            e.$set(e.RegisterForm, "AccountPassWord", t)
                        }, expression: "RegisterForm.AccountPassWord"
                    }
                })], 1), e._v(" "), o("el-form-item", {
                    attrs: {
                        label: "验证码",
                        prop: "VerificationCode"
                    }
                }, [o("el-input", {
                    model: {
                        value: e.RegisterForm.VerificationCode, callback: function (t) {
                            e.$set(e.RegisterForm, "VerificationCode", t)
                        }, expression: "RegisterForm.VerificationCode"
                    }
                })], 1), e._v(" "), o("PostVerificationCode", {attrs: {email: e.RegisterForm.Email}}), e._v(" "), o("el-form-item", [o("el-button", {on: {click: e.Register}}, [e._v("注册")])], 1)], 1)
            }, staticRenderFns: []
        };
        var oe = o("VU/8")(ee, te, !1, function (e) {
            o("YWrd")
        }, "data-v-315617a8", null).exports, ne = {
            name: "logOff", data: function () {
                return {
                    offForm: {password: "", doubleCheckPassword: ""},
                    rules: {
                        password: [{required: !0, trigger: "blur"}],
                        doubleCheckPassword: [{required: !0, trigger: "blur"}]
                    }
                }
            }, methods: {
                LogOff: function (e, t) {
                    var o = new FormData;
                    o.append("password", this.offForm.password), l.a.post("/logoff", o, {
                        headers: {
                            "Content-Type": "multipart/form-data",
                            Token: this.$route.query.token
                        }
                    })
                }
            }
        }, re = {
            render: function () {
                var e = this, t = e.$createElement, o = e._self._c || t;
                return o("el-form", {
                    attrs: {
                        rules: e.rules,
                        model: e.offForm
                    }
                }, [o("el-form-item", {
                    attrs: {
                        label: "密码",
                        prop: "password"
                    }
                }, [o("el-input", {
                    model: {
                        value: e.offForm.password, callback: function (t) {
                            e.$set(e.offForm, "password", t)
                        }, expression: "offForm.password"
                    }
                })], 1), e._v(" "), o("el-form-item", {
                    attrs: {
                        label: "确认密码",
                        prop: "doubleCheckPassword"
                    }
                }, [o("el-input", {
                    model: {
                        value: e.offForm.doubleCheckPassword, callback: function (t) {
                            e.$set(e.offForm, "doubleCheckPassword", t)
                        }, expression: "offForm.doubleCheckPassword"
                    }
                })], 1), e._v(" "), o("el-button", {on: {click: e.LogOff}}, [e._v("注销")])], 1)
            }, staticRenderFns: []
        };
        var se = o("VU/8")(ne, re, !1, function (e) {
            o("8gYh")
        }, "data-v-264c2c00", null).exports, ae = {
            name: "setKindleEmail", data: function () {
                return {
                    SetForm: {kindleEmail: "", doubleCheckKindleEmail: ""},
                    rules: {
                        kindleEmail: [{required: !0, trigger: "blur"}],
                        doubleCheckKindleEmail: [{required: !0, trigger: "blur"}]
                    }
                }
            }, methods: {
                SetEmail: function (e, t) {
                    var o = new FormData;
                    o.append("KindleEmail", this.SetForm.kindleEmail), l.a.post("/mail/setKindleEmail", o, {
                        headers: {
                            "Content-Type": "multipart/form-data",
                            Token: this.$route.query.token
                        }
                    })
                }
            }
        }, ie = {
            render: function () {
                var e = this, t = e.$createElement, o = e._self._c || t;
                return o("el-form", {
                    attrs: {
                        rules: e.rules,
                        model: e.SetForm
                    }
                }, [o("el-form-item", {
                    attrs: {
                        label: "kindleEmail",
                        prop: "kindleEmail"
                    }
                }, [o("el-input", {
                    model: {
                        value: e.SetForm.kindleEmail, callback: function (t) {
                            e.$set(e.SetForm, "kindleEmail", t)
                        }, expression: "SetForm.kindleEmail"
                    }
                })], 1), e._v(" "), o("el-form-item", {
                    attrs: {
                        label: "确认kindleEmail",
                        prop: "doubleCheckKindleEmail"
                    }
                }, [o("el-input", {
                    model: {
                        value: e.SetForm.doubleCheckKindleEmail, callback: function (t) {
                            e.$set(e.SetForm, "doubleCheckKindleEmail", t)
                        }, expression: "SetForm.doubleCheckKindleEmail"
                    }
                })], 1), e._v(" "), o("el-button", {on: {click: e.SetEmail}}, [e._v("确定")])], 1)
            }, staticRenderFns: []
        };
        var ce = o("VU/8")(ae, ie, !1, function (e) {
            o("aIA3")
        }, "data-v-99f23da2", null).exports, le = {
            name: "logOut", methods: {
                LogOut: function () {
                    l.a.get("/logout", {headers: {Token: this.$route.query.token}})
                }
            }
        }, ue = {
            render: function () {
                var e = this.$createElement;
                return (this._self._c || e)("el-button", {on: {click: this.LogOut}}, [this._v("下线")])
            }, staticRenderFns: []
        };
        var de = o("VU/8")(le, ue, !1, function (e) {
            o("XAwK")
        }, "data-v-31fcc2eb", null).exports, me = {
            name: "registerAsManagement", data: function () {
                return {
                    RegisterForm: {
                        AccountName: "",
                        AccountPassWord: "",
                        Email: "",
                        VerificationCode: "",
                        kindleEmail: "",
                        registrationCode: ""
                    },
                    rules: {
                        RegisterRules: {
                            AccountName: [{required: !0, trigger: "blur"}],
                            AccountPassWord: [{required: !0, trigger: "blur"}],
                            Email: [{required: !0, trigger: "blur"}],
                            VerificationCode: [{required: !0, trigger: "blur"}],
                            kindleEmail: [{trigger: "blur"}],
                            registrationCode: [{required: !0, trigger: "blur"}]
                        }
                    }
                }
            }, methods: {
                Register: function () {
                    var e = this;
                    return i()(s.a.mark(function t() {
                        var o, n;
                        return s.a.wrap(function (t) {
                            for (; ;) switch (t.prev = t.next) {
                                case 0:
                                    return o = new FormData, n = void 0, t.next = 4, e.$refs.RegisterForm.validate(function (e) {
                                        n = !!e
                                    });
                                case 4:
                                    if (!n) {
                                        t.next = 14;
                                        break
                                    }
                                    return o.append("nickName", e.RegisterForm.AccountName), o.append("password", e.RegisterForm.AccountPassWord), o.append("email", e.RegisterForm.Email), o.append("code", e.RegisterForm.VerificationCode), o.append("AdminPassword", e.RegisterForm.registrationCode), o.append("KindleEmail", e.RegisterForm.kindleEmail), t.next = 13, l.a.post("/admin/register", o, {headers: {"Content-Type": "multipart/form-data"}});
                                case 13:
                                    t.sent;
                                case 14:
                                case"end":
                                    return t.stop()
                            }
                        }, t, e)
                    }))()
                }
            }, components: {PostVerificationCode: F}
        }, pe = {
            render: function () {
                var e = this, t = e.$createElement, o = e._self._c || t;
                return o("el-form", {
                    ref: "RegisterForm",
                    attrs: {model: e.RegisterForm, rules: e.rules.RegisterRules, "status-icon": ""}
                }, [o("el-form-item", {
                    attrs: {
                        label: "用户名",
                        prop: "AccountName"
                    }
                }, [o("el-input", {
                    model: {
                        value: e.RegisterForm.AccountName, callback: function (t) {
                            e.$set(e.RegisterForm, "AccountName", t)
                        }, expression: "RegisterForm.AccountName"
                    }
                })], 1), e._v(" "), o("el-form-item", {
                    attrs: {
                        label: "邮箱",
                        prop: "Email"
                    }
                }, [o("el-input", {
                    model: {
                        value: e.RegisterForm.Email, callback: function (t) {
                            e.$set(e.RegisterForm, "Email", t)
                        }, expression: "RegisterForm.Email"
                    }
                })], 1), e._v(" "), o("el-form-item", {
                    attrs: {
                        label: "密码",
                        prop: "AccountPassWord"
                    }
                }, [o("el-input", {
                    model: {
                        value: e.RegisterForm.AccountPassWord, callback: function (t) {
                            e.$set(e.RegisterForm, "AccountPassWord", t)
                        }, expression: "RegisterForm.AccountPassWord"
                    }
                })], 1), e._v(" "), o("el-form-item", {
                    attrs: {
                        label: "验证码",
                        prop: "VerificationCode"
                    }
                }, [o("el-input", {
                    model: {
                        value: e.RegisterForm.VerificationCode, callback: function (t) {
                            e.$set(e.RegisterForm, "VerificationCode", t)
                        }, expression: "RegisterForm.VerificationCode"
                    }
                })], 1), e._v(" "), o("PostVerificationCode", {attrs: {email: e.RegisterForm.Email}}), e._v(" "), o("el-form-item", {
                    attrs: {
                        label: "kindleEmail",
                        prop: "kindleEmail"
                    }
                }, [o("el-input", {
                    model: {
                        value: e.RegisterForm.kindleEmail, callback: function (t) {
                            e.$set(e.RegisterForm, "kindleEmail", t)
                        }, expression: "RegisterForm.kindleEmail"
                    }
                })], 1), e._v(" "), o("el-form-item", {
                    attrs: {
                        label: "registrationCode",
                        prop: "registrationCode"
                    }
                }, [o("el-input", {
                    model: {
                        value: e.RegisterForm.registrationCode, callback: function (t) {
                            e.$set(e.RegisterForm, "registrationCode", t)
                        }, expression: "RegisterForm.registrationCode"
                    }
                })], 1), e._v(" "), o("el-form-item", [o("el-button", {on: {click: e.Register}}, [e._v("注册")])], 1)], 1)
            }, staticRenderFns: []
        };
        var fe = o("VU/8")(me, pe, !1, function (e) {
            o("opXc")
        }, "data-v-a3624294", null).exports, he = {
            name: "categoryList", data: function () {
                return {category: []}
            }, methods: {
                getBookByCategory: function (e) {
                    this.$parent.$router.push({path: "/FirstPage", query: {cateId: e.cateId}})
                }
            }, mounted: function () {
                var e = this;
                l.a.get("/cate").then(function (t) {
                    e.category = t.data
                })
            }
        }, ge = {
            render: function () {
                var e = this, t = e.$createElement, o = e._self._c || t;
                return o("el-table", {attrs: {data: e.category}}, [o("el-table-column", {
                    attrs: {
                        prop: "cateName",
                        label: "分类名"
                    }, scopedSlots: e._u([{
                        key: "default", fn: function (t) {
                            return [o("a", {
                                staticStyle: {color: "blue", cursor: "pointer"}, on: {
                                    click: function (o) {
                                        return e.getBookByCategory(t.row)
                                    }
                                }
                            }, [e._v(e._s(t.row.cateName))])]
                        }
                    }])
                }), e._v(" "), o("el-table-column", {attrs: {prop: "cateId", label: "分类ID"}})], 1)
            }, staticRenderFns: []
        };
        var ke = o("VU/8")(he, ge, !1, function (e) {
            o("yZcd")
        }, "data-v-1b947a2d", null).exports;
        n.default.use(b.a);
        var ve = [{path: "/", component: z, hidden: !0}, {
            path: "/FirstPage",
            component: z,
            hidden: !0
        }, {path: "/ChangePassword", component: P, hidden: !0}, {
            path: "/mainPage",
            component: E,
            hidden: !0
        }, {path: "/detailsOfBook", component: Y, hidden: !0}, {
            path: "/LogInForAccount",
            component: Q,
            hidden: !0
        }, {path: "/RegisterForAccount", component: oe, hidden: !0}, {
            path: "/logOff",
            component: se,
            hidden: !0
        }, {path: "/setKindleEmail", component: ce, hidden: !0}, {
            path: "/logOut",
            component: de,
            hidden: !0
        }, {path: "/registerAsManagement", component: fe, hidden: !0}, {
            path: "/categoryList",
            component: ke,
            hidden: !0
        }], be = new b.a({routes: ve}), _e = o("zL8q"), Ce = o.n(_e);
        o("tvR6");//TODO
        n.default.config.productionTip = !1, n.default.prototype.$http = l.a, l.a.defaults.baseURL = "/", n.default.use(Ce.a), new n.default({
            render: function (e) {
                return e(v)
            }, axios: l.a, router: be
        }).$mount("#app")
    }, SiGE: function (e, t) {
    }, Wmn4: function (e, t) {
    }, XAwK: function (e, t) {
    }, YWrd: function (e, t) {
    }, Z0U3: function (e, t) {
    }, aIA3: function (e, t) {
    }, c9RE: function (e, t) {
    }, d8YR: function (e, t) {
    }, dtrC: function (e, t) {
    }, nsLx: function (e, t) {
    }, opXc: function (e, t) {
    }, pdla: function (e, t) {
    }, rlel: function (e, t) {
    }, tvR6: function (e, t) {
    }, vbcF: function (e, t) {
    }, yZcd: function (e, t) {
    }
}, ["NHnr"]);
//# sourceMappingURL=app.7d5ca3a086995f17e74e.js.map