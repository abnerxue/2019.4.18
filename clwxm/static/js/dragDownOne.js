let dragDown = {

};

let startx;
let starty;
let startLeft;
let startTop;
let isDown;

dragDown.dragMover = /**拖动类 */
    function dragMover(divId) {
        this.dom = document.getElementById(divId);
        this.objDrag = this.dom.parentNode;
        this.startx = 0;
        this.starty;
        var that = this;
        this.isDown = false;
        this.movedown = function (e) {
            e = e ? e : window.event;
            e.preventDefault();
            if (!window.captureEvents) {
                this.setCapture();
            }
            /* 事件捕获仅支持ie
               函数功能：该函数在属于当前线程的指定窗口里设置鼠标捕获。一旦窗口捕获了鼠标，
               所有鼠标输入都针对该窗口，无论光标是否在窗口的边界内。同一时刻只能有一个窗口捕获鼠标。
               如果鼠标光标在另一个线程创建的窗口上，只有当鼠标键按下时系统才将鼠标输入指向指定的窗口。
               非ie浏览器 需要在document上设置事件 */
            that.isDown = true;
            that.startx = e.clientX - that.objDrag.offsetLeft;
            that.starty = e.clientY - that.objDrag.offsetTop;
        };
        this.move = function (e) {
            e = e ? e : window.event;
            if (that.isDown) {
                that.objDrag.style.left = e.clientX - that.startx + "px";
                that.objDrag.style.top = e.clientY - that.starty + "px";
                //边界检测
                // var cW =
                //     document.body.scrollWidth || document.documentElement.scrollWidth;

                // var cH =
                //     document.body.scrollHeight || document.documentElement.scrollHeight;


                var cH = window.innerHeight
                // var cW = document.getElementById("mapAndCharts").offsetWidth;
                var cW =
                    document.body.scrollWidth || document.documentElement.scrollWidth;

                if (that.objDrag.offsetLeft <= 0) {
                    that.objDrag.style.left = 0 + "px";
                }

                if (that.objDrag.offsetTop <= 0 * window.innerHeight) {
                    that.objDrag.style.top = 0 * window.innerHeight + "px";
                }
                if (that.objDrag.offsetTop >= cH - that.objDrag.offsetHeight) {
                    that.objDrag.style.top = cH - that.objDrag.offsetHeight + "px";
                }

                if (that.objDrag.offsetLeft >= cW - that.objDrag.offsetWidth) {
                    that.objDrag.style.left = cW - that.objDrag.offsetWidth + "px";
                }
                //console.log('W,H:',cW,cH);
            }
        };
        this.moveup = function () {
            that.isDown = false;
            if (!window.captureEvents) {
                this.releaseCapture();
            } //事件捕获仅支持ie
        };
        this.dom.onmousedown = this.movedown;
        //this.objDrag.onmousemove = this.move;
        this.dom.onmouseup = this.moveup;

        //非ie浏览器
        document.addEventListener("mousemove", this.move, true);
    }


export default dragDown;