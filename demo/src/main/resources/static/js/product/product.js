var prefix = "/product"
$(function () {
    load();
});

function load() {
    $('#exampleTable')
        .bootstrapTable(
            {
                method: 'get', // 服务器数据的请求方式 get or post
                url: prefix + "/list", // 服务器数据的加载地址
                //	showRefresh : true,
                //	showToggle : true,
                //	showColumns : true,
                iconSize: 'outline',
                toolbar: '#exampleToolbar',
                striped: true, // 设置为true会有隔行变色效果
                dataType: "json", // 服务器返回的数据类型
                pagination: true, // 设置为true会在底部显示分页条
                // queryParamsType : "limit",
                // //设置为limit则会发送符合RESTFull格式的参数
                singleSelect: false, // 设置为true将禁止多选
                clickToSelect: true, //点击行选中，绑定复选框
                // contentType : "application/x-www-form-urlencoded",
                // //发送到服务器的数据编码类型
                showRefresh: true,
                pageSize: 10, // 如果设置了分页，每页数据条数
                pageNumber: 1, // 如果设置了分布，首页页码
                //search : true, // 是否显示搜索框
                showColumns: false, // 是否显示内容下拉框（选择显示的列）
                sidePagination: "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
                queryParams: function (params) {
                    return {
                        //说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                        limit: params.limit,
                        offset: params.offset,
                        name: $('#name').val(),
                    };
                },
                // //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
                // queryParamsType = 'limit' ,返回参数必须包含
                // limit, offset, search, sort, order 否则, 需要包含:
                // pageSize, pageNumber, searchText, sortName,
                // sortOrder.
                // 返回false将会终止请求
                columns:

                    [
                        {
                            checkbox: true,
                            align: 'center',
                            valign: 'middle',

                        }, {
                        title: '操作',
                        field: 'remark',
                        align: 'center',
                        valign: 'middle',
                        formatter: function (value, row, index) {
                            var e = '<a class="btn btn-primary btn-sm ' + '" href="#" mce_href="#" title="编辑" onclick="edit(\''
                                + row.id
                                + '\')"><i class="fa fa-edit"></i></a> ';
                            var d = '<a class="btn btn-warning btn-sm " href="#" title="删除"  mce_href="#" onclick="remove(\''
                                + row.id
                                + '\')"><i class="fa fa-remove"></i></a> ';
                            var f = '<a class="btn btn-success btn-sm" href="#" title="备用"  mce_href="#" onclick="resetPwd(\''
                                + row.id
                                + '\')"><i class="fa fa-key"></i></a> ';
                            return e + d;
                        }
                    }, {
                        title: '序号',
                        field: 'no',
                        formatter: function (value, row, index) {
                            return index + 1;
                        }
                    },

                        {
                            field: 'name',
                            title: '名称',
                            align: 'center',
                            valign: 'middle',

                        },
                        {
                            field: 'number',
                            title: '编号',
                            align: 'center',
                            valign: 'middle',
                        }, {
                        title: '创建时间',
                        field: 'createTime',
                        align: 'center',
                        valign: 'middle',
                        formatter:function (v) {
                            return dateFtt("yyyy-MM-dd hh:mm:ss",new Date(v));
                        }
                    }, {
                        title: '修改时间',
                        field: 'updateTime',
                        align: 'center',
                        valign: 'middle',
                        formatter:function (v) {
                            return dateFtt("yyyy-MM-dd hh:mm:ss",new Date(v));
                        }
                    },
                        {
                            title: 'id',
                            field: 'id',
                            visible: false
                        }
                    ]
            });
}

function reLoad() {
    $('#exampleTable').bootstrapTable('refresh');
}

function add() {
    layer.open({
        type: 2,
        title: '产品新增',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['60%', '40%'],
        content: prefix + '/add' // iframe的url
    });
}

function edit(id) {
    layer.open({
        type: 2,
        title: '产品编辑',
        maxmin: true,
        offset: ['100px', '80px'],
        shadeClose: false, // 点击遮罩关闭层
        area: ['80%', '80%'],
        content: prefix + '/edit/' + id // iframe的url
    });
}

function remove(id) {
    layer.confirm('确定要删除选中的记录？', {
        btn: ['确定', '取消']
    }, function () {
        $.ajax({
            url: prefix + "/remove",
            type: "post",
            data: {
                'id': id
            },
            success: function (r) {
                if (r.code == 0) {
                    layer.msg(r.msg);
                    reLoad();
                } else {
                    layer.msg(r.msg);
                }
            }
        });
    })
}

function resetPwd(id) {
}

function batchRemove() {
    var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
    if (rows.length == 0) {
        layer.msg("请选择要删除的数据");
        return;
    }
    layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
        btn: ['确定', '取消']
        // 按钮
    }, function () {
        var ids = new Array();
        // 遍历所有选择的行数据，取每条数据对应的ID
        $.each(rows, function (i, row) {
            ids[i] = row['id'];
        });
        $.ajax({
            type: 'POST',
            data: {
                "ids": ids
            },
            url: prefix + '/batchRemove',
            success: function (r) {
                if (r.code == 0) {
                    layer.msg(r.msg);
                    reLoad();
                } else {
                    layer.msg(r.msg);
                }
            }
        });
    }, function () {

    });
}
function wareLoad() {
    window.location.href = "/warehouse";
}
/**************************************时间格式化处理************************************/
function dateFtt(fmt,date) {
    var o = {
        "M+" : date.getMonth()+1,                 //月份
        "d+" : date.getDate(),                    //日
        "h+" : date.getHours(),                   //小时
        "m+" : date.getMinutes(),                 //分
        "s+" : date.getSeconds(),                 //秒
        "q+" : Math.floor((date.getMonth()+3)/3), //季度
        "S"  : date.getMilliseconds()             //毫秒
    };
    if(/(y+)/.test(fmt)) {
        fmt=fmt.replace(RegExp.$1, (date.getFullYear()+"").substr(4 - RegExp.$1.length));
    }
    for(var k in o) {
        if(new RegExp("("+ k +")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
    }
    return fmt;
}
