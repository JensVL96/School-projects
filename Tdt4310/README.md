# TDT4310 — Intelligent Text Analytics and Language Understanding

Coursework for NTNU's applied natural-language-processing course: NLP
pipelines, tokenization and text normalization, and modern language models,
culminating in a transformer-based project. Work is in Python (NLTK, spaCy,
scikit-learn, Hugging Face Transformers) using Jupyter notebooks.

## Labs

### Lab 1 — LLM Foundations

**Task:** Answer a set of conceptual questions establishing the theoretical
groundwork for language models.

**What I did:** Worked through short-answer questions on language models vs.
large language models, pre-training and fine-tuning (including the role of the
model "head"), knowledge cutoffs, hallucinations, context length and why it's
costly to extend, model size/parameters and interpretability, quantization
(LoRA, GPTQ, AWQ), tokens and out-of-vocabulary handling, training vs.
inference, and why GPUs suit transformer training.

**Key concepts:** LLM fundamentals, fine-tuning, quantization, tokenization,
transformer training
**Tools:** Jupyter

### Lab 2 — Tokenization

**Task:** Explore why tokenization is necessary and compare how different
tokenizers handle awkward input.

**What I did:** Implemented a rule-based whitespace tokenizer, then ran a tricky
sentence ("That U.S.A. poster-print costs $12.40...") through several NLTK
tokenizers (WordPunct, Tweet, Toktok), comparing token counts and discussing
how each handles abbreviations, hyphenation, and currency.

**Key concepts:** tokenization, NLP preprocessing, edge cases in text splitting
**Tools:** Python, NLTK

### Lab 3 — Stemming and Lemmatization

**Task:** Build a simple stemmer, observe where it breaks, and contrast stemming
with lemmatization.

**What I did:** Wrote a regex/rule-based stemmer for stripping common endings,
showed its failure cases on new words, replaced it with NLTK's Snowball stemmer,
and used a spaCy lemmatizer to resolve cases where stemming produces poor results.

**Key concepts:** stemming, lemmatization, rule-based vs. library approaches
**Tools:** Python, NLTK, spaCy

## Final Project — Paraphrase Detection (with V. Spinello)

**Task:** Build and evaluate models that decide whether two sentences convey the
same meaning, comparing a fine-tuned transformer against a classical baseline.

**What we built:** A paraphrase-detection pipeline fine-tuning the ALBERT
transformer (freezing most layers, training only the final ones for transfer
learning) and, as a baseline, a Support Vector Machine trained from scratch.
We compared the two approaches and analyzed their strengths and limitations,
reaching around 72% validation accuracy and reflecting on why the task remains
hard and what could improve performance.

**Key concepts:** paraphrase detection, transformers, transfer learning,
fine-tuning, SVM baselines, model evaluation
**Tools:** Python, Hugging Face Transformers (ALBERT), scikit-learn
**Deliverables:** `Leynse_Spinello_Code.ipynb`, `Leynse_Spinello_Report.pdf`
