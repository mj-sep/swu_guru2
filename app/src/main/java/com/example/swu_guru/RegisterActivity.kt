package com.example.swu_guru


import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteStatement
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_join.*
import java.io.ByteArrayOutputStream


class RegisterActivity : AppCompatActivity() {

    lateinit var myDBHelper: GroupBuyingDBManager
    lateinit var sqlitedb: SQLiteDatabase
    lateinit var nickname: String
    val TAG: String = "Register"
    var isExistBlank = false
    var isPWSame = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)
        myDBHelper = GroupBuyingDBManager(this)

        btn_register.setOnClickListener {
            Log.d(TAG, "회원가입 버튼 클릭")

            val id = edit_id.text.toString()
            val pw = edit_pw.text.toString()
            val pw_re = edit_pw_re.text.toString()

            // 유저가 항목을 다 채우지 않았을 경우
            if(id.isEmpty() || pw.isEmpty() || pw_re.isEmpty()){
                isExistBlank = true
            }
            else{
                if(pw == pw_re){
                    isPWSame = true
                }
            }

            if(!isExistBlank && isPWSame){

                // 회원가입 성공 토스트 메세지 띄우기
                Toast.makeText(this, "회원가입 성공", Toast.LENGTH_LONG).show()

                // 유저가 입력한 id, pw를 쉐어드에 저장한다.
                val sharedPreference = getSharedPreferences("file name", Context.MODE_PRIVATE)
                val editor = sharedPreference.edit()
                editor.putString("id", id)
                editor.putString("pw", pw)
                editor.apply()

                // 회원정보 DB에 저장
                nickname = "슈니"
                sqlitedb = myDBHelper.writableDatabase
                sqlitedb = myDBHelper.readableDatabase

                // 기본 프로필 이미지 파일 -> bitmap -> byteArray -> BLOB
                val bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.user_icon)
                val user = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, user)
                val img = user.toByteArray()
                sqlitedb.execSQL("INSERT INTO user VALUES (null, '$id', '$pw', null, '$nickname')")

                // 프로필 이미지 DB에 저장
                var insQuery: String = "UPDATE user SET " + "image = ? WHERE id = '$id'"
                var stmt: SQLiteStatement = sqlitedb.compileStatement(insQuery)
                stmt.bindBlob(1, img)
                stmt.execute()


                // 로그인 화면으로 이동
                val intent = Intent(this, RealMainActivity::class.java)
                intent.putExtra("id", id)
                startActivity(intent)

            }
            else{

                // 상태에 따라 다른 다이얼로그 띄워주기
                if(isExistBlank){   // 작성 안한 항목이 있을 경우
                    dialog("blank")
                }
                else if(!isPWSame){ // 입력한 비밀번호가 다를 경우
                    dialog("not same")
                }
            }

        }
    } // onCreate 끝

    // 회원가입 실패시 다이얼로그를 띄워주는 메소드
    fun dialog(type: String){
        val dialog = AlertDialog.Builder(this)

        // 작성 안한 항목이 있을 경우
        if(type.equals("blank")){
            dialog.setTitle("회원가입 실패")
            dialog.setMessage("입력란을 모두 작성해주세요")
            isExistBlank = false
        }
        // 입력한 비밀번호가 다를 경우
        else if(type.equals("not same")){
            dialog.setTitle("회원가입 실패")
            dialog.setMessage("비밀번호가 다릅니다")
            isPWSame = false
        }

        val dialog_listener = object: DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, which: Int) {
                when(which){
                    DialogInterface.BUTTON_POSITIVE ->
                        Log.d(TAG, "다이얼로그")
                }
            }
        }

        dialog.setPositiveButton("확인", dialog_listener)
        dialog.show()
    }





}
