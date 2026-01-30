# Java 学習ログ（Week1〜Week2）

このリポジトリは、Java基礎学習のアウトプット（ミニ作品＋練習コード）をまとめたものです。  
コンソール（ターミナル）上で動く小さなアプリを作り、GitHubに学習履歴として残しています。

---

## 実行環境
- Java（JDK）
- IntelliJ IDEA

---

## 実行方法（IntelliJ）
1. `src` 配下の実行したい `.java` ファイルを開く
2. `main` メソッド左の ▶︎（Run）を押す
3. 入力が求められた場合は、下部の「Run（コンソール）」に数字を入力して Enter

---

## Week1：ミニ作品（入力式レジ / app.MiniCashier）

### これは何？
商品金額と支払額を入力すると、お釣りまたは不足金額を表示するコンソールアプリです。

### 実行ファイル
- `src/app.MiniCashier.java`

### 入力例と出力例（1つ）
**入力例**
- 商品金額: 680
- 支払額: 1000

**出力例**
- 商品金額: 680円
- 支払額: 1000円
- お釣り: 320円

---

## Week2：メソッド化（リファクタリング）＆配列集計（app.ScoreAnalyzer）

### これは何？
Week1のMiniCashierを「読みやすく・直しやすく」するために、処理をメソッド（関数）に分割しました。  
あわせて、配列を使って複数データを集計する点数集計アプリ（app.ScoreAnalyzer）を作成しました。

### 作品1：app.MiniCashier（メソッド化版）
#### 実行ファイル
- `src/app.MiniCashier.java`

#### できること（要点）
- 入力（商品金額・支払額）
- 入力チェック（不正値の判定）
- お釣り / 不足の計算と表示
- 数字以外の入力に対して落ちにくい処理（例外対応）

#### 入力例と出力例（1つ）
**入力例**
- 商品金額: 680
- 支払額: 500

**出力例**
- 商品金額: 680円
- 支払額: 500円
- あと 180円 足りません

---

### 作品2：点数集計（app.ScoreAnalyzer）
#### 実行ファイル
- `src/app.ScoreAnalyzer.java`

#### できること（要点）
- 5人分の点数を入力して配列に保存
- 合計 / 平均 / 最高 / 最低 / 合格人数（60点以上）を表示
- 処理をメソッド化して整理（入力 / 集計 / 表示）

#### 入力例と出力例（1つ）
**入力例（5人分）**
- 100
- 50
- 80
- 60
- 0

**出力例**
- 合計: 290
- 平均: 58.0
- 最高: 100
- 最低: 0
- 合格(60以上)人数: 3

---

## 練習ファイル（学習ログ）
- `src/LoopPractice.java`：for文の練習
- `src/MethodPractice.java`：メソッド（引数・戻り値）の練習
- `src/Main.java`：初期の基礎練習（変数・ifなど）

---

## 学んだこと（Week2の要点）
- メソッド（関数）で「入力 / 計算 / 表示」を分けて整理する
- 引数・戻り値（`static void` / `static int`）
- 例外処理（try/catch/finally）で入力ミスに強くする
- 配列（`int[]`）とループで複数データをまとめて処理する
- 平均計算で `double` キャストを使い、整数割り算を避ける
- 複数の結果をまとめるために Result（箱）を使う（app.ScoreAnalyzer）

---

## よくあるつまずき
- `fatal: not a git repository`  
  → `.git` があるフォルダ（例：`/Users/.../IdeaProjects/Java`）でGitコマンドを実行する
- `public class ○○` とファイル名 `○○.java` は一致させる（Javaのルール）

## Week3：メニューアプリ（app.MenuApp）

### これは何？
app.MiniCashier と app.ScoreAnalyzer をメニューから選んで起動できる統合アプリです。

### 実行ファイル
- `src/app.MenuApp.java`

### 使い方（例）
- 1 → app.MiniCashier（入力式レジ）
- 2 → app.ScoreAnalyzer（点数集計）
- 3 → ヘルプ表示
- 0 → 終了

## Week4：List / Map（集計の実務っぽい基礎）

### できるようになったこと
- 配列ではなく List（ArrayList）で「人数可変」の点数集計ができるようになった（app.ScoreAnalyzerV2）
- Map（HashMap）で「分類して数える」集計ができるようになった（app.ScoreDistribution）

### 実行ファイル
- `src/app.ScoreAnalyzerV2.java`：人数可変の点数集計（List）
- `src/app.ScoreDistribution.java`：成績分布（A/B/C/D/F）を集計（Map）

### 入力例と出力例（app.ScoreDistribution）
**入力例**
- 人数: 5
- 点数: 100, 85, 75, 60, 10

**出力例（例）**
- A(90-100): 1
- B(80-89):  1
- C(70-79):  1
- D(60-69):  1
- F(0-59):   1

## Week5：OOP（クラス）+ List<model.Student> + 検索/分布

### できるようになったこと
- Studentクラス（name/score）を作り、データをオブジェクトとして扱えるようになった
- List<model.Student> で複数人を管理し、一覧/集計/合格者表示ができるようになった
- 検索（部分一致）を追加し、アプリとしての実用性を上げた
- Mapで成績分布（A/B/C/D/F）を集計できるようになった

### 実行ファイル
- `src/model.Student.java`：学生（名前＋点数）を表すクラス
- `src/app.StudentApp.java`：学生点数管理（一覧/集計/合格/検索/分布）

### 入力例と出力例（成績分布）
**入力例**
- 人数: 3
- Alice 90 / Bob 50 / Aki 80

**出力例**
- A(90-100): 1
- B(80-89):  1
- F(0-59):   1

## Week6：パッケージ分割（app/model/service/util）で実務っぽく整理

### できるようになったこと
- `model`：データ（Student）をクラスとして分離して管理できる
- `app`：画面/入力/メニュー（実行起点）をまとめて整理できる
- `service`：集計・検索・分布などのロジックを抽出して再利用できる
- `util`：入力処理（数値/空文字/点数範囲/Enter待ち）を共通化し、バグを減らせる

### ディレクトリ構成
- `src/app`：MenuApp / MiniCashier / ScoreAnalyzer / ScoreAnalyzerV2 / StudentApp など（起動・表示・入力）
- `src/model`：Student（データ構造）
- `src/service`：StudentService（集計・検索・分布などの処理）
- `src/util`：InputUtil（入力共通）

### 3分で説明できるポイント
- 役割で分ける（UI / ロジック / データ / 共通処理）
- 入力の「改行問題（nextInt→nextLine）」を InputUtil に集約して事故を防いだ
- ロジックを service に切り出して、拡張しやすい構造にした

### Week7：CSV保存/読み込み（永続化 + 自動ロード）
- `StudentCsvService` を追加し、学生データを `data/students.csv` に保存・復元できるようにした（`data/` は自動作成）
- 読み込み時は「成功件数 / スキップ行数」を表示し、壊れた行が混ざっても落ちずに原因が追えるようにした
- `StudentApp` 起動時にCSVが存在すれば自動で読み込み、使用するかどうかを `y/n` で確認して開始できるようにした

#### CSVについて（保存先とサンプル）
- StudentApp の「CSVに保存」は `data/students.csv` に出力します（※実行データなので Git 管理から除外しています）
- 初見の人が試せるように、サンプルとして `data/students_sample.csv` を用意しています
  - 例：`data/students_sample.csv` を `data/students.csv` にコピーすると、起動時の自動ロードを確認できます

**サンプルをコピーして試す（Mac / ターミナル）**
```bash
cp data/students_sample.csv data/students.csv

- サンプルCSVは `sample/students_sample.csv`（Git管理）
