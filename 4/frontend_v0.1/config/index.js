'use strict'

const path = require('path')

module.exports = {
    dev: {
        assetsSubDirectory: 'static',
        assetsPublicPath: '/',
        proxyTable: {},
        host: '0.0.0.0',
        port: 8080,
        autoOpenBrowser: false,
        errorOverlay: true,
        notifyOnErrors: true,
        poll: false,
        devtool: 'cheap-module-eval-source-map',
        cacheBusting: true,
        cssSourceMap: true
    },

    build: {
        testEnv: require('./test.env'),
        preEnv: require('./pre.env'),
        prodEnv: require('./prod.env'),
        index: path.resolve(__dirname, '../dist/index.html'),
        assetsRoot: path.resolve(__dirname, '../dist'),
        assetsSubDirectory: 'static',
        // assetsPublicPath: './',
        assetsPublicPath: '/', // history 模式
        // 对于hash模式，根路径是固定的，就是项目的根目录，但是history模式下，以 / 开头的嵌套路径会被当作根路径，所以使用“./”引入文件，就会找不到文件了，因为文件本身就是在项目根目录下的，并不在嵌套路径这个目录下。
        productionSourceMap: true,
        devtool: '#source-map',
        productionGzip: false,
        productionGzipExtensions: ['js', 'css'],
        bundleAnalyzerReport: process.env.npm_config_report
    }
}
