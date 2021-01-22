package com.jimy.foodmemo

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

//MEMO_TABLEには連番で振られていくidとランダム文字列のuuid、最後にメモ本体のbodyを持たせる
class MemoOpenHelper
// コンストラクタ　以下のように呼ぶこと
    (context: Context) : SQLiteOpenHelper(context, DBName, null, VERSION) {

    // データベースが作成された時に実行される処理
    // データベースはアプリを開いた時に存在しなかったら作成され、すでに存在していれば何もしない
    override fun onCreate(db: SQLiteDatabase) {
        /**
         * テーブルを作成する
         * execSQLメソッドにCREATET TABLE命令を文字列として渡すことで実行される
         * 引数で指定されているものの意味は以下の通り
         * 引数1 ・・・ id：購入食材id , INTEGER：数値型 , PRIMATY KEY：テーブル内の行で重複無し , AUTOINCREMENT：1から順番に振っていく
         * 引数2 ・・・ uuid：列名 , TEXT：文字列型
         * 引数3 ・・・ body：購入食材名 , TEXT：文字列型
         */
        db.execSQL(
            "CREATE TABLE FOOD_TABLE (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "uuid TEXT, " +
                    "food_name TEXT)"
        )

    }

    // データベースをバージョンアップした時に実行される処理
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        /**
         * テーブルを削除する
         */
        db.execSQL("DROP TABLE IF EXISTS FOOD_TABLE")

        // 新しくテーブルを作成する
        onCreate(db)
    }

    companion object {

        // データベース名
        private val DBName = "FOOD_DB"

        // データベースのバージョン(2,3と挙げていくとonUpgradeメソッドが実行される)
        private val VERSION = 1
    }
}