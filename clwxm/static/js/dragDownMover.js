let dragDownMover = {

};
/**拖动类 */
dragDownMover.Drag = function (options) {
    let disX, disY;
    const init = function (options) {
        const {
            id,
            limit,
            showLimit
        } = options;
        const node = document.getElementById(id);
        convertStaticNodeToAbsolute(node);
        bindEvents(node, limit);
        if (showLimit) {
            showLimitBox(limit);
        }
        //console.info(options);
    }
    const bindEvents = function (node, limit = [0, window.innerWidth, 0, window.innerHeight]) {
        node.onmousedown = function (event) {
            // console.log("onmousedown", event);
            disX = event.pageX - node.offsetLeft;
            disY = event.pageY - node.offsetTop;
            document.onmousemove = function (event) {
                // console.log("onmousemove", event);
                let x = event.pageX - disX,
                    y = event.pageY - disY,
                    w = node.offsetWidth,
                    h = node.offsetHeight;
                x = x <= limit[0] ? limit[0] : ((x >= limit[1] - w) ? limit[1] - w : x);
                y = y <= limit[2] ? limit[2] : ((y >= limit[3] - h) ? limit[3] - h : y);
                node.style.cssText = `position:absolute;display:block;left:${x}px;top:${y}px;margin:0;cursor: move`;
            }
            document.onmouseup = function () {
                // console.log("onmouseup");
                document.onmousemove = null;
                document.onmouseup = null;
            }
        }
    }
    const showLimitBox = function (limit = [0, window.innerWidth, 0, window.innerHeight]) {
        const node = document.createElement("div");
        const width = limit[1] - limit[0];
        const height = limit[3] - limit[2];
        node.style.cssText = `position:absolute;width:${width}px;height:${height}px;top:${limit[2]}px;left:${limit[0]}px;border:1px dashed #0f0;z-index:-1;`;
        document.body.appendChild(node);
    }
    const convertStaticNodeToAbsolute = function (node) {
        // const top = node.offsetTop;
        // const left = node.offsetLeft;
        // if (node.style.position === 'absolute') {
        //     return;
        // } else {
        //     node.style.cssText = `position:absolute;margin:0;cursor: move`;
        //     //node.style.cssText = `position:absolute;top:${top}px;left:${left}px;margin:0;cursor: move`;
        // }
    }
    init(options);
};


export default dragDownMover;